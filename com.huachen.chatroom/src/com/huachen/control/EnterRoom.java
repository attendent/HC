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
import com.huachen.model.User;
import com.huachen.service.ChatService;
import com.huachen.service.Impl.ChatServiceImpl;

@WebServlet("/EnterRoom")
public class EnterRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public String record = "";
	
    public EnterRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String roomId = request.getParameter("roomId");
		String roomName = request.getParameter("roomName");
		List<User> userlist = new ArrayList<>();
		ChatService chatservice = new ChatServiceImpl();
		ChatRoom chatRoom = new ChatRoom();
		
		//将房间信息存储进chatRoom
        chatRoom.setId(Integer.parseInt(roomId));
        chatRoom.setRoomName(roomName);
        
        //得到该房间的成员
        userlist = chatservice.getAllUsers(Integer.parseInt(roomId));
        
        //清空消息记录
        record = "";

        request.getSession().setAttribute("contents", record);
        request.getSession().setAttribute("chatRoom",chatRoom);
        request.getSession().setAttribute("userlist",userlist);
        
        request.getRequestDispatcher("Index.jsp").forward(request, response);
	}
}
