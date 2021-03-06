package com.yxm.javaserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.yxm.javaserver.util.Logger;

/**
 * javaserver 启动入口
 * @author yxm
 *@version 1.0
 *@since 1.0
 */
public class BootStrap {
	/**
	 * 主方法
	 * @param args
	 */
  public static void main(String[] args) {
	start();
  }
  /*
//   * 启动入口
   */
  public static void start(){
	  long begin = System.currentTimeMillis();
	  Logger.log("server start");
	  ServerSocket serverSocket = null;
	  try{
		  int port = ServerParse.getPort();//获取配置端口号
		  Logger.log("server-port-"+port);
		  serverSocket = new ServerSocket(port);
		  long end =  System.currentTimeMillis();
		  Logger.log("server started, "+(end-begin)+"ms");
		  
		  //服务器启动成功,执行到以下代码进入接收等待状态,等待浏览器客户端请求
		  while(true){
		  Socket clientServer = serverSocket.accept();
		  InputStream in = clientServer.getInputStream();
		  byte[] bytes = new byte[in.available()];
		  int readNum = in.read(bytes);
		  System.out.println("readNum:"+readNum);
		  String requestMsg = new String(bytes);
		  System.out.println("requestMsg:"+requestMsg);
		  }
		  
	  }catch(IOException e){
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
