package com.huachen.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huachen.model.ChatContent;
import com.huachen.model.ChatRoom;
import com.huachen.model.User;
import com.huachen.service.ChatService;
import com.huachen.service.UserService;
import com.huachen.service.Impl.ChatServiceImpl;
import com.huachen.service.Impl.UserServiceImpl;

/**
 * Servlet implementation class Whisper
 */
@WebServlet("/Whisper")
public class Whisper extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Whisper() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String friendId = request.getParameter("friendId");
		String friendName = request.getParameter("friendName");
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		if(userId == Integer.parseInt(friendId)) {
			request.setAttribute("msg", "不能私聊自己");
			request.getRequestDispatcher("Index.jsp").forward(request, response);
			return ;
		}
		
		UserService userservice = new UserServiceImpl();
		ChatService chatservice = new ChatServiceImpl();
		
		List<ChatContent> contents = new ArrayList<>();
		List<User> users = new ArrayList<>();
		ChatRoom chatroom = new ChatRoom();
		
		//以好友名字创建房间，并得到该房间的基本信息
		chatservice.createRoom(friendName);
		chatroom = chatservice.find(friendName);
		
		//将自己和好友加入房间
		userservice.addUserRoom(userId, chatroom.getId());
		userservice.addUserRoom(Integer.parseInt(friendId), chatroom.getId());
		
		//得到房间消息和成员
		contents = chatservice.getAllContents(chatroom.getId());
		users = chatservice.getAllUsers(Integer.parseInt(friendName));
		
		request.setAttribute("roomName", friendName);
		request.getSession().setAttribute("chatRoom", chatroom);
		request.setAttribute("contents", contents);
		request.setAttribute("users", users);

		request.getRequestDispatcher("Index").forward(request, response);
	}

}
