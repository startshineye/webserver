package javax.servlet;
/**
 * 请求转发器
 * @author SUN
 */
public interface RequestDispatcher {
  void forward(ServletRequest requesst,ServletResponse response);
}
