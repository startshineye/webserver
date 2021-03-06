package com.yxm.javaserver.core;

import java.io.IOException;
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
		 //处理请求
		 try {
			Thread.sleep(1000*60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			if(clientSocket != null && !clientSocket.isClosed()){
				try {
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
