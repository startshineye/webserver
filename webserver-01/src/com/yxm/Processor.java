package com.yxm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 服务器处理函数(基于多线程)
 * 
 * @author yxm
 * @date 2016-11-26
 */
public class Processor extends Thread {
	private Socket socket;
	private InputStream in;
	private PrintStream out;
	private final static String WEB_ROOT = "F:\\MVC\\webserver-01\\src\\source";// 规定只能访问指定目录下文件

	/**
	 * 构造方法
	 * 
	 * @param socket
	 */
	public Processor(Socket socket) {
		this.socket = socket;
		try {
			// 输入需要输入流
			in = socket.getInputStream();
			// 输出需要输出流
			out = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// 1.获取文件名
		String fileName = parse(in);
		this.sendFile(fileName);
	}

	/**
	 * 
	 * 根据请求输入流，得到访问资源名称
	 * 
	 * @param in
	 * @return
	 */
	public String parse(InputStream in) {
		/**
		 * 根据http内容查看用户访问时哪个文件
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(in));// 字节流变成字符流
		String fileName = null;
		try {
			String httpMessage = br.readLine();// 获得http发送请求第一行
			String[] content = httpMessage.split(" ");
			System.out.println(content.toString());
			if (content.length != 3) {// 不为3就不是http请求
				this.SendErrorMessage(400, "client query error");
				return null;
			}
			fileName = content[1];// 文件名是数组第二个值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 * 资源请求失败时给出提示
	 * 
	 * @param errorCode
	 * @param errorMessage
	 */
	public void SendErrorMessage(int errorCode, String errorMessage) {
		// 以HTTP协议形式打印出去
		out.println("HTTP/1.0" + errorCode + " " + errorMessage);
		out.println("content-type:text/html");
		out.println("<html>");
		out.println("<title>Error Message");
		out.println("</title>");
		out.println("<body>");
		out.println("<h1>ErrorCode:" + errorCode + ",Message:" + errorMessage
				+ "</h1>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * 正常请求时的输出
	 * @param fileName
	 */
	public void sendFile(String fileName) {
		/**
		 * 找到服务器中文件并返回，将服务器文件读到内存中，然后输出到浏览器
		 */
		File file = new File(Processor.WEB_ROOT + fileName);
		if (!file.exists()) {// 访问文件不存在404
			SendErrorMessage(404, "File Not Found");
			return;// 结束
		}
		try {
			InputStream in = new FileInputStream(file);
			byte content[] = new byte[(int) file.length()];
			in.read(content);// 以content字节大小读取文件到输入流
			// 以http协议形式打印出去
			out.println("HTTP/1.0 200 queryfile");
			out.println("content-length:" + content.length);
			out.println();
			out.write(content);
			out.flush();
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
