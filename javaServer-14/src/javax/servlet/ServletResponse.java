package javax.servlet;

import java.io.PrintWriter;

/**
 * 负责响应的接口
 * @author yxm
 *
 */
public interface ServletResponse {
   PrintWriter getWriter();
}
