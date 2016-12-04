package com.yxm.javaserver.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期工具类
 * @author yxm
 * @version 1.0
 * @since 1.o
 */
public class DateUtil {
  private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm:ss SSS");
  private DateUtil(){
	  
  }
  /**
   * 获取系统当前时间
   * @return 系统当前时间
   */
  public static String getCurrentTime(){
	  return sdf.format(new Date());
  }
}
