package com.huachen.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huachen.model.User;
import com.huachen.service.UserService;
import com.huachen.service.Impl.UserServiceImpl;

@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddFriend() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		String friendName = request.getParameter("friendName");
		User friend = new User();
		UserService userservice = new UserServiceImpl();
		
		if(userId == null) {
			request.setAttribute("msg", "请先登录后再尝试此操作!");
			request.getRequestDispatcher("Index.jsp").forward(request, response);
			return ;
		}
		
		//由好友名得到好友的基本信息
		friend = userservice.find(friendName);
		if (userservice.addFriend(userId, friend.getId()) == true) {
			request.setAttribute("msg", "添加好友成功!");
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "添加好友失败!");
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		}
	}

}
