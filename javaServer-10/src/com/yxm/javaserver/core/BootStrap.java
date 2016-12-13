package com.yxm.javaserver.core;

import java.io.IOException;
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
		  WebParse.parse(new String[]{"oa"});
		  long end =  System.currentTimeMillis();
		  Logger.log("server started, "+(end-begin)+"ms");
		  
		  //服务器启动成功,执行到以下代码进入接收等待状态,等待浏览器客户端请求
		  while(true){
		  Socket clientSocket = serverSocket.accept();
		  //服务器接收到请求之后，开始处理请求，这可能需要一段时间
		  //而当前服务器只有一个主线程在运行，在多用户请求的时候
		  //会造成用户排队等待的处理,用户体验较差，为了避免用户等待
		  //决定引入一次请求对应一个线程，多用户同事并发访问，服务器同事并发处理
		  /*InputStream in = clientServer.getInputStream();
		  byte[] bytes = new byte[in.available()];
		  int readNum = in.read(bytes);
		  System.out.println("readNum:"+readNum);
		  String requestMsg = new String(bytes);
		  System.out.println("requestMsg:"+requestMsg);*/
		  Thread handlerRequestThread = new Thread(new HandlerRequest(clientSocket));
		  Logger.log(handlerRequestThread + " begin handler request");
		  handlerRequestThread.start();
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
