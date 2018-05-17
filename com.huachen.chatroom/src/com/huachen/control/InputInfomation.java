package com.huachen.control;

import java.io.IOException;
import java.sql.Timestamp;
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

@WebServlet("/InputInfomation")
public class InputInfomation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//定义聊天记录变量
	public String chat_record = "";
	
    public InputInfomation() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//得到所需数据
		String roomId = request.getParameter("roomId");
		String roomName = request.getParameter("roomName");
		String input_textarea = request.getParameter("input_textarea");//得到用户输入的消息
        String nickName = (String) request.getSession().getAttribute("nickName");
        
        ChatContent chatContent = new ChatContent();
        List<ChatContent> contents = new ArrayList<>();
        
        //将数据存储进model
        chatContent.setContent(input_textarea);
        chatContent.setUserName(nickName);
        chatContent.setRoomId(Integer.parseInt(roomId));
        chatContent.setRoomName(roomName);
        chatContent.setDate(new Timestamp(System.currentTimeMillis()));
        
        ChatService chatservice = new ChatServiceImpl();
        
        //将本次得到的信息写入数据库并得到所有消息记录
        chatservice.addContent(chatContent);
        contents = chatservice.getAllContents(Integer.parseInt(roomId));
        
        //将服务器中消息记录清零并从数据库中重新读取消息记录
        chat_record = "";
        for(ChatContent content : contents) {
        	chat_record += content.getUserName() + " 于 " + content.getDate() + " 说： " + content.getContent() + "\n";
        }
        
        //将当前聊天输入内容存储
        request.getSession().setAttribute("input_textarea",chat_record);
        
        request.getRequestDispatcher("Index").forward(request, response);
        return ;
	}

}
