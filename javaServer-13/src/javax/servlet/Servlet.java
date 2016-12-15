package javax.servlet;


import com.yxm.javaserver.core.ResponseObject;

/**
 * Servlet规范核心接口
 * @author yxm
 *
 */
public interface Servlet {
	/**
	 * 处理请求的核心方法
	 */
   void service(ServletResponse response);
}
