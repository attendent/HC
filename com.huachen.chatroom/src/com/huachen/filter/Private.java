package com.huachen.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.huachen.model.ChatContent;
import com.huachen.model.ChatRoom;
import com.huachen.model.User;
import com.huachen.service.ChatService;
import com.huachen.service.Impl.ChatServiceImpl;

public class Private implements Filter{

	String chat_record = "";
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException{
		HttpServletRequest Request = (HttpServletRequest) request;
		Integer roomId = 1;
		String roomName = "groud";
		List<ChatContent> contents = new ArrayList<>();
		List<User> userlist = new ArrayList<>();
		ChatService chatservice = new ChatServiceImpl();
		ChatRoom chatRoom = new ChatRoom();
		
		//将房间信息存储进chatRoom
        chatRoom.setId(roomId);
        chatRoom.setRoomName(roomName);
        
        //得到该房间的消息记录和成员
        contents = chatservice.getAllContents(roomId);
        userlist = chatservice.getAllUsers(roomId);
        
        //将服务器中消息记录清零并从数据库中重新读取消息记录
        chat_record = "";
        for(ChatContent content : contents) {
        	chat_record += content.getUserName() + " 于 " + content.getDate() + " 说： " + content.getContent() + "\n";
        }
        
        //将当前聊天输入内容存储
        Request.getSession().setAttribute("input_textarea",chat_record); 
        Request.getSession().setAttribute("chatRoom",chatRoom);
        Request.getSession().setAttribute("userlist",userlist);
		
		request.getRequestDispatcher("Index").forward(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	

}
