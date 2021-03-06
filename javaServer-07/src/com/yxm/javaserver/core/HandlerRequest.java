package com.yxm.javaserver.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.yxm.javaserver.util.Logger;

public class HandlerRequest implements Runnable{
   private Socket clientSocket;
   public HandlerRequest(Socket clientSocket){
	   this.clientSocket = clientSocket;
   }
	@Override
	public void run() {
		 Logger.log(Thread.currentThread().getName()+" handler request!");
		 BufferedReader br = null;
		 //处理请求
		 try {
			Thread.sleep(1000*60);
			//字节流转化成字符流
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    String readLine = br.readLine();
		   String requestURI = readLine.split(" ")[1];
		   System.out.println("readLine: "+"["+readLine+"]"+"requestURI "+"["+requestURI+"]");
		 } catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(clientSocket != null && !clientSocket.isClosed()){
				try {
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
