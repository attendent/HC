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
import com.huachen.service.ChatService;
import com.huachen.service.UserService;
import com.huachen.service.Impl.ChatServiceImpl;
import com.huachen.service.Impl.UserServiceImpl;

@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Index() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");
		Integer roomId = 1;
		String roomName = "聊天总群";
		if (userId == null) {
			String nickName = new String();
			int num = (int) (Math.random() * 10000);
			nickName = "游客" + num;
			request.getSession().setAttribute("nickName", nickName);
		}

		List<User> userlist = new ArrayList<>();
		ChatService chatservice = new ChatServiceImpl();
		ChatRoom chatRoom = new ChatRoom();
		
		chatRoom.setId(roomId);
        chatRoom.setRoomName(roomName);
		
		UserService userservice = new UserServiceImpl();
		List<Role> roles = new ArrayList<>();
		List<User> friends = new ArrayList<>();
		List<ChatRoom> chatRooms = new ArrayList<>();

		// 得到该用户的所有 角色(暂时没啥用),好友，聊天室
		roles = userservice.getRoles(userId);
		friends = userservice.getFriends(userId);
		chatRooms = userservice.getChatRooms(userId);
		userlist = chatservice.getAllUsers(roomId);

		request.getSession().setAttribute("roles", roles);
		request.getSession().setAttribute("friends", friends);
		request.getSession().setAttribute("chatRooms", chatRooms);
		request.getSession().setAttribute("chatRoom",chatRoom);
		request.getSession().setAttribute("userlist",userlist);

		response.sendRedirect("Index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
