package org.yxm.oa.servlet;

import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SaveServlet implements Servlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) {
		String username = request.getParameter("username");
	    String sex = request.getParameter("sex");
	    String[] interests = request.getParameters("interest");
	    
	    StringBuilder interest = new StringBuilder();
	    for (String string : interests) {
	    	interest.append(string);
	    	interest.append(" ");
	    }
	    	//连接数据库操作
	    	PrintWriter out = response.getWriter();
	    	out.print("用户名："+username+"<br>");
	    	out.print("性别："+sex+"<br>");
	    	out.print("兴趣："+interest+"<br>");
	}

}
