package com.huachen.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huachen.model.ChatRoom;
import com.huachen.model.Role;
import com.huachen.model.User;
import com.huachen.service.UserService;
import com.huachen.service.Impl.UserServiceImpl;

@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Index() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");
		String userName = (String) request.getSession().getAttribute("userName");
		if (userName == null) {
			int num = (int) (Math.random() * 10000);
			userName = "游客" + num;
			request.getSession().setAttribute("userName", userName);
			request.getSession().setAttribute("nickName", userName);
			response.sendRedirect("Index.jsp");
			return;
		}

		UserService userservice = new UserServiceImpl();
		List<Role> roles = new ArrayList<>();
		List<User> friends = new ArrayList<>();
		List<ChatRoom> chatRooms = new ArrayList<>();

		// 得到该用户的所有 角色(暂时没啥用),好友，聊天室
		roles = userservice.getRoles(userId);
		friends = userservice.getFriends(userId);
		chatRooms = userservice.getChatRooms(userId);

		request.getSession().setAttribute("roles", roles);
		request.getSession().setAttribute("friends", friends);
		request.getSession().setAttribute("chatRooms", chatRooms);

		response.sendRedirect("Index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
