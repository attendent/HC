package com.huachen.control;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huachen.model.User;
import com.huachen.service.UserService;
import com.huachen.service.Verification;
import com.huachen.service.Impl.UserServiceImpl;
import com.huachen.service.Impl.VerificationImpl;
import com.huachen.util.DigertUtils;

@WebServlet("/login.do")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String inputCode = request.getParameter("inputCode");
		String code = (String) request.getSession().getAttribute("code");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		request.getSession().setAttribute("user", user);

		Verification ver = new VerificationImpl();
		UserService userservice = new UserServiceImpl();

		// 判断验证码
		if (ver.isCode(inputCode, code) == false) {
			request.setAttribute("msg", "验证码不正确");
			request.getRequestDispatcher("Login.jsp").forward(request, response);

		} else {
			// 判断正确，判断输入用户名密码长度
			if (ver.isLength(userName, password) == true) {
				password = DigertUtils.md5(password);
				
				if (userservice.login(userName, password) == true) {
					//登录成功
					//由用户名找到该用户的基本信息并存储在user中
					user = userservice.find(userName);					
					
					request.getSession().setAttribute("userName", userName);
					request.getSession().setAttribute("userId", user.getId());
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("nickName", user.getNickName());
					
					if ("true".equals(request.getParameter("autologin"))) {
						//如果勾选自动登录
						Cookie cookie = new Cookie("autologin", URLEncoder.encode(userName, "utf-8") + "." + password);
						cookie.setMaxAge(60);
						cookie.setPath("/com.huachen.chatroom");
						response.addCookie(cookie);
					}
					response.sendRedirect("Index");

				} else {
					request.setAttribute("msg", "用户名或密码输入错误！");
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "用户名或密码长度错误！");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}
	}

}
