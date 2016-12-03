package com.yxm.junit;

public class SystemService {
 public boolean login(String username,String password){
	 return "admin".equals(username) && "123".equals(password);
 }
}
