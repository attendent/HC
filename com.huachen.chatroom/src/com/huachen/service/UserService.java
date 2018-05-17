package com.huachen.service;

import java.util.List;

import com.huachen.model.ChatRoom;
import com.huachen.model.Role;
import com.huachen.model.User;

public interface UserService {
	// 用户登录
	public boolean login(String userName, String password);

	// 用户注册
	public boolean register(User user);

	// 用户更新
	public boolean update(User user, String nextPassword);

	// 查找用户
	public User find(String userName);

	public User find(Integer userId);

	// 得到所有用户
	public List<User> getListAll();

	// 添加好友
	public boolean addFriend(Integer userId, Integer friendId);

	// 删除好友
	public boolean delFriend(Integer userId, Integer friendId);

	// 修改好友备注
	public boolean updateRemark(Integer userId, Integer friendId, String remark);

	// 邀请好友进入聊天室
	public boolean addUserRoom(Integer userId, Integer roomId);

	// 得到用户所有好友
	public List<User> getFriends(Integer userId);

	// 得到所有用户角色
	public List<Role> getRoles(Integer userId);

	// 更新所有用户角色
	public void updateRole(User user, List<Role> roles);

	// 得到用户所有聊天室
	public List<ChatRoom> getChatRooms(Integer userId);
}
