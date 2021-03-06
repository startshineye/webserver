package com.yxm.javaserver.core;

import java.io.IOException;
import java.net.ServerSocket;

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
