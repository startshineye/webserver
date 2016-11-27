package com.yxm.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * @author yxm
 * @date 2016-11-27
 */
public class Server {
   public static void main(String[] args) {
	ServerSocket serverSocket = null;
	try {
		//創建服務器套接字對象，表示創建一個對象，並且將該服務綁定到9090端口上
		serverSocket = new ServerSocket(9090);
		//开始监听网络,接受客户的请求，程序在此等待,当客户端发送请求后,接受客户端套接字
		Socket clientSocket = serverSocket.accept();
		
		//通过客户端套接字获取输入流,从该输入流中读取客户端发送的消息
		InputStream in = clientSocket.getInputStream();
		byte[] bytes = new byte[in.available()];
		in.read(bytes);//以bytes个字节读取字节流
		String msgFromClient = new String(bytes);
		System.out.println(msgFromClient);
		
		//通过套接字对象获取输出流,通过该输出流向客户端响应消息
	    OutputStream out = clientSocket.getOutputStream();
	    out.write("HelloWorld!".getBytes());//字节输出
	    out.flush();
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		if(serverSocket != null && !serverSocket.isClosed()){
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
   }
}

