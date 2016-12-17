package javax.servlet;


import com.yxm.javaserver.core.RequestObject;

/**
 * Servlet规范核心接口
 * @author yxm
 *
 */
public interface Servlet {
	/**
	 * 处理请求的核心方法
	 */
   void service(RequestObject requestObject,ServletResponse response);
}
