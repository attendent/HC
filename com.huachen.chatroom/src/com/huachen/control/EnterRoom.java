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
import com.huachen.service.Impl.ChatServiceImpl;

@WebServlet("/EnterRoom")
public class EnterRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public String chat_record = "";
	
    public EnterRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String roomId = request.getParameter("roomId");
		String roomName = request.getParameter("roomName");
		List<ChatContent> contents = new ArrayList<>();
		List<User> userlist = new ArrayList<>();
		ChatService chatservice = new ChatServiceImpl();
		ChatRoom chatRoom = new ChatRoom();
		
		//将房间信息存储进chatRoom
        chatRoom.setId(Integer.parseInt(roomId));
        chatRoom.setRoomName(roomName);
        
        //得到该房间的消息记录和成员
        contents = chatservice.getAllContents(Integer.parseInt(roomId));
        userlist = chatservice.getAllUsers(Integer.parseInt(roomId));
        
        //将服务器中消息记录清零并从数据库中重新读取消息记录
        chat_record = "";
        for(ChatContent content : contents) {
        	chat_record += content.getUserName() + " 于 " + content.getDate() + " 说： " + content.getContent() + "\n";
        }
        
        //将当前聊天输入内容存储
        request.getSession().setAttribute("input_textarea",chat_record); 
        request.getSession().setAttribute("chatRoom",chatRoom);
        request.getSession().setAttribute("userlist",userlist);
        
        request.getRequestDispatcher("Index").forward(request, response);
	}
}
