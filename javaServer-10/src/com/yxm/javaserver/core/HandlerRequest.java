package com.yxm.javaserver.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import javax.servlet.Servlet;

import org.yxm.oa.servlet.LoginServlet;

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
		 PrintWriter out = null;
		 //处理请求
		 try {
			//字节流转化成字符流
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    String requestURI = br.readLine().split(" ")[1];
		    out = new PrintWriter(clientSocket.getOutputStream());
		
		   if(requestURI.trim().endsWith(".html") || requestURI.trim().endsWith(".htm")){
			   responseStaticPage(requestURI,out);
		   }else{
			   String servletPath = requestURI;
			   if(servletPath.contains("?")){
				   servletPath = servletPath.split("\\?")[0];
			   }
			  /* if("/oa/login".equals(servletPath)){
				   LoginServlet loginServlet = new LoginServlet();
				   loginServlet.service();
			   }*/
			   String wenAppName = servletPath.split("/")[1];//oa
			   Map<String, String> servletMap = WebParse.servletMaps.get(wenAppName);
			   String urlPattern = servletPath.substring(wenAppName.length()+1);//login
			   String servletClassName = servletMap.get(urlPattern);
			   Class c = Class.forName(servletClassName);
			   Object obj = c.newInstance();//创建servlet对象
			   //调用servlet对象方法，怎么调用呢？我们不知道service对象中有什么方法，怎么办？
			   Servlet servlet = (Servlet)obj;
			   servlet.service();
		   }
		   out.flush();//必须要注意刷新
		 } catch (Exception e) {
			e.printStackTrace();
		}finally{//关闭流
			if(out != null){
				out.close();
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(clientSocket != null && !clientSocket.isClosed()){
				try {
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 以http协议返回静态html页面
	 * @param requestURI
	 * @param out
	 */
	public static void responseStaticPage(String requestURI,PrintWriter out){
		String htmlFilePath = requestURI.substring(1);
	    BufferedReader br = null;
		System.out.println(htmlFilePath);
		try {
			//返回正确提示
			br = new BufferedReader(new FileReader(htmlFilePath));
			//拼接一个响应的状态行、响应报头、空白行
			StringBuilder html = new StringBuilder();
			html.append("HTTP/1.1 200 OK\n");
			html.append("Content-Type: text/html;charset=UTF-8\n\n");
			String temp = null;
			while((temp=br.readLine())!=null){
				html.append(temp);
			}
			out.print(html);
		} catch (Exception e) {
			//返回错误提示
			//报404错误
			StringBuilder html404 = new StringBuilder();
			html404.append("HTTP/1.1 404 OK\n");
			html404.append("Content-Type: text/html;charset=UTF-8\n\n");
			html404.append("<html>");
			html404.append("<head>");
			html404.append("<title>404-Not Found</title>");
			html404.append("</head>");
			html404.append("<body>");
			html404.append("<h1 align='center'><font color='red'>404-Not Found!</font></h1>");
			html404.append("</body>");
			html404.append("</html>");
			out.print(html404.toString());
		}finally{
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
