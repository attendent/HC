package com.huachen.dao;

import java.util.List;

import com.huachen.model.ChatContent;
import com.huachen.model.ChatRoom;
import com.huachen.model.User;

public interface ChatDao {
	public boolean isExistRoom(String roomName);

	public boolean isExistRoom(Integer roomId);

	public ChatRoom find(String roomName);

	// 创建聊天室
	public boolean createRoom(String roomName);

	// 得到所有聊天室
	public List<ChatRoom> getAllChatRooms();

	// 得到某聊天室所有聊天记录
	public List<ChatContent> getAllContents(Integer roomId);

	// 添加消息记录
	public boolean addContent(ChatContent chatContent);

	// 得到聊天室内所有用户
	public List<User> getAllUsers(Integer roomId);
}
