package com.huachen.service;

public interface Verification {

	public boolean isLength(String username, String password);

	public boolean isLength(String username, String password, String nextpassword);

	// 判断验证码是否正确
	public boolean isCode(String checkcode, String inputCode);

	public boolean mailFormat(String mail); 
}
