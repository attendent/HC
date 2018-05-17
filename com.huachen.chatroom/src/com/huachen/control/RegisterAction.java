package com.huachen.control;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huachen.model.User;
import com.huachen.service.UserService;
import com.huachen.service.Verification;
import com.huachen.service.Impl.UserServiceImpl;
import com.huachen.service.Impl.VerificationImpl;
import com.huachen.util.DigertUtils;

@WebServlet("/register.do")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String nickName = request.getParameter("nickName");
		String mail = request.getParameter("mail");
		String inputCode = request.getParameter("inputCode");
		String code = (String) request.getSession().getAttribute("code");
		
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		request.setAttribute("nickName", nickName);
		request.setAttribute("mail", mail);

		Verification ver = new VerificationImpl();
		//判断验证码
		if (ver.isCode(inputCode, code) == false) {
			request.setAttribute("msg", "验证码不正确");
			request.getRequestDispatcher("Register.jsp").forward(request, response);
			return;
		}
		
		// 判断输入用户名密码长度
		if (ver.isLength(userName, password) == false) {
			request.setAttribute("msg", "用户名或密码长度有误");
			request.getRequestDispatcher("Register.jsp").forward(request, response);
			return;
		}
		
		//判断邮箱格式
		if(ver.mailFormat(mail) == false) {
			request.setAttribute("msg", "邮箱格式有误");
			request.getRequestDispatcher("Register.jsp").forward(request, response);
			return;
		}
		password = DigertUtils.md5(password);
		
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setNickName(nickName);
		user.setMail(mail);
		
		UserService userservice = new UserServiceImpl();
		if (userservice.register(user) == true) {
			request.setAttribute("msg", "注册成功");
			Integer roomId = 1;
			user = userservice.find(userName);
			userservice.addUserRoom(user.getId(), roomId);
			request.getRequestDispatcher("Register.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "用户名已存在");
			request.getRequestDispatcher("Register.jsp").forward(request, response);
		}
	}
}
