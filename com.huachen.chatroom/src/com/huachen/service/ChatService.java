package com.huachen.service;


import java.util.List;

import com.huachen.model.ChatContent;
import com.huachen.model.ChatRoom;
import com.huachen.model.User;

public interface ChatService {
	//创建新聊天室
	public boolean createRoom(String roomName);
	
	//由房间名找到该房间的基本信息
	public ChatRoom find(String roomName);
	
	//得到所有房间
	public List<ChatRoom> getAllChatRooms();
	
	//得到某房间所有消息记录
    public List<ChatContent> getAllContents(Integer id);
    
    //将某条消息记录加入数据库
    public boolean addContent(ChatContent chatContent);
    
    //得到房间内所有用户
    public List<User> getAllUsers(Integer roomId);
}
