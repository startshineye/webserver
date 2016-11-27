package com.yxm.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端
 * 
 * @author yxm
 * @date 2016-11-26
 */
public class Client {
	public static void main(String[] args) {
		Socket clientSocket = null;
		String address = "127.0.0.1";
		int port = 9090;

		try {
			// 创建客户端套接字对象,指向某台计算机上的某个服务
			clientSocket = new Socket(address, port);
			//从该套接字对象上获取输出流,该输出流指向服务器上的某个服务
			OutputStream out = clientSocket.getOutputStream();
			
			//向服务器传输数据
			out.write("hello server!".getBytes());
			//刷新
			out.flush();
			
			//接收服务器端响应的消息
			InputStream in = clientSocket.getInputStream();
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			
			String msgFromServer = new String(bytes);
			System.out.println(msgFromServer);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(clientSocket!=null && !clientSocket.isClosed()){
				try {
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
