package com.huachen.control;

import java.io.IOException;
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

@WebServlet("/update.do")
public class UpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset= UTF-8  ");
		response. setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nextpassword = request.getParameter("nextpassword");
		request.getSession().setAttribute(username, username);

		// 判断输入用户名密码长度
		Verification ver = new VerificationImpl();
		if (ver.isLength(username, password, nextpassword) == false) {
			request.setAttribute("msg", "用户名或密码长度错误!");
			request.getRequestDispatcher("Update.jsp").forward(request, response);
		}
		
		password = DigertUtils.md5(password);
		nextpassword = DigertUtils.md5(nextpassword);
		
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		
		UserService userservice = new UserServiceImpl();
		if (userservice.update(user, nextpassword) == true) {
			System.out.println("c");
			request.setAttribute("msg", "修改密码成功，点击后返回登录界面");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "修改密码失败!");
			request.getRequestDispatcher("Update.jsp").forward(request, response);
		}
	}
}