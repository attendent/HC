package com.huachen.service.Impl;

import java.util.List;

import com.huachen.dao.ChatDao;
import com.huachen.dao.Impl.ChatDaoImpl;
import com.huachen.model.ChatContent;
import com.huachen.model.ChatRoom;
import com.huachen.model.User;
import com.huachen.service.ChatService;

public class ChatServiceImpl implements ChatService {

	ChatDao chatdao = new ChatDaoImpl();
	
	@Override
	public boolean createRoom(String roomName) {
		if(chatdao.isExistRoom(roomName) == false) {
			if(chatdao.createRoom(roomName) == true) {
				return true;
			}		
		}		
		return false;
	}
	
	@Override
	public List<ChatRoom> getAllChatRooms() {
		return chatdao.getAllChatRooms();
	}
	
    @Override
    public List<ChatContent> getAllContents(Integer roomId) {
        return chatdao.getAllContents(roomId);
    }
    
    @Override
    public boolean addContent(ChatContent chatContent) {
        return chatdao.addContent(chatContent);
    }

	@Override
	public ChatRoom find(String roomName) {
		return chatdao.find(roomName);
	}

	@Override
	public List<User> getAllUsers(Integer roomId) {
		return chatdao.getAllUsers(roomId);
	}

	

	
}
