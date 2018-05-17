package com.huachen.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.huachen.dao.UserDao;
import com.huachen.dao.Impl.UserDaoImpl;
import com.huachen.model.ChatRoom;
import com.huachen.model.Role;
import com.huachen.model.User;
import com.huachen.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userdao = new UserDaoImpl();

	@Override
	public boolean login(String userName, String password) {
		if (userdao.isExist(userName, password) == true)
			return true;
		else
			return false;
	}

	@Override
	public boolean register(User user) {
		if (userdao.isExist(user.getUserName()) == false)
			if (userdao.register(user) == true)
				return true;
		return false;
	}

	@Override
	public boolean update(User user, String nextPassword) {
		if (userdao.isExist(user.getUserName(), user.getPassword()) == true)
			if (userdao.update(user, nextPassword) == true)
				return true;
		return false;
	}

	@Override
	public User find(Integer userId) {
		return null;
	}

	@Override
	public List<User> getListAll() {
		List<User> users = new ArrayList<>();
		users = userdao.getListAll();
		return users;
	}

	@Override
	public List<Role> getRoles(Integer userId) {
		List<Role> roles = new ArrayList<>();
		roles = userdao.getRoles(userId);
		return roles;
	}

	@Override
	public void updateRole(User user, List<Role> roles) {
		// TODO Auto-generated method stub

	}

	@Override
	public User find(String userName) {
		User user = new User();
		user = userdao.find(userName);
		return user;
	}

	@Override
	public boolean addFriend(Integer userId, Integer friendId) {
		if (userId != null && friendId != null)
			if (userdao.isExist(userId) == true)
				if (userdao.isFriend(userId, friendId) == false)
					if (userdao.addFriend(userId, friendId) == true)
						return true;
		return false;
	}

	@Override
	public List<User> getFriends(Integer userId) {
		List<User> friends = new ArrayList<>();
		friends = userdao.getFriends(userId);
		return friends;
	}

	@Override
	public boolean delFriend(Integer userId, Integer friendId) {
		if (userId != null && friendId != null)
			if (userdao.isExist(userId) == true)
				if (userdao.isFriend(userId, friendId) == true)
					if (userdao.delFriend(userId, friendId) == true)
						return true;
		return false;
	}

	@Override
	public List<ChatRoom> getChatRooms(Integer userId) {
		List<ChatRoom> chatRooms = new ArrayList<>();
		chatRooms = userdao.getChatRooms(userId);
		return chatRooms;
	}

	@Override
	public boolean updateRemark(Integer userId, Integer friendId,String remark) {
		if(userdao.updateRemark(userId,friendId,remark) == true) 
			return true;
		return false;
	}

	@Override
	public boolean addUserRoom(Integer userId, Integer roomId) {
		if(userdao.isUserRoom(userId, roomId) == false)
			if(userdao.addUserRoom(userId, roomId) == true)
				return true;
		return false;
	}

}
