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
import com.huachen.service.ChatService;
import com.huachen.service.Impl.ChatServiceImpl;

@WebServlet("/GetChatList")
public class GetChatList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String record = "";

	public GetChatList() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roomId = request.getParameter("roomId");

		ChatService chatservice = new ChatServiceImpl();
		List<ChatContent> contents = new ArrayList<>();

		contents = chatservice.getAllContents(Integer.parseInt(roomId));

		record = "";
		for (ChatContent content : contents) {
			record += content.getUserName() + " 于 " + content.getDate() + " 说： " + content.getContent() + "\n";
		}

		// 将当前聊天输入内容存储
		request.getSession().setAttribute("contents", record);

		request.getRequestDispatcher("Index").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
