package org.yxm.oa.servlet;

import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletResponse;


/**
 * 处理登录的小java程序，是webapp的开发人员负责开发
 * 该对象的创建，以及方法的调用应该由web服务器完成
 * @author webapp开发人员
 * @version 1.0
 * @since 1.0
 */
public class LoginServlet implements Servlet{
   /**
	 * 处理登录的核心方法，该方法由web服务器负责调用
	 */
	@Override
	public void service(ServletResponse response) {
		PrintWriter out = response.getWriter();
		System.out.println("正在身份验证，请稍后.......");
		out.append("<html>");
		out.append("<head>");
		out.append("<title>测试</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<h1 align='center'><font color='blue'>正在验证，请稍后......</font></h1>");
		out.append("</body>");
		out.append("</html>");	  
	}
}
