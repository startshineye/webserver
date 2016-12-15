package com.yxm.javaserver.core;

import java.io.PrintWriter;

import javax.servlet.ServletResponse;

/**
 * 负责响应的对象
 * 
 * @author yxm
 * 
 */
public class ResponseObject implements ServletResponse{
	private PrintWriter out;

	public void setWriter(PrintWriter out) {
		this.out = out;
	}
	@Override
	public PrintWriter getWriter() {
		// TODO Auto-generated method stub
		return out;
	}

}
