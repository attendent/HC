package com.huachen.Service.Impl;

import java.util.List;

import com.huachen.dao.UserDao;
import com.huachen.dao.Impl.UserDaoImpl;
import com.huachen.model.Role;
import com.huachen.model.User;
import com.huachenservice.UserService;

public class UserServiceImpl implements UserService{

	UserDao userdao = new UserDaoImpl();
	@Override
	public boolean login(String userName, String password) {
		if(userdao.isExist(userName, password) == true)
			return true;
		else
			return false;
	}

	@Override
	public boolean register(User user) {
		if(userdao.isExist(user.getUserName()) == false)
			if(userdao.register(user) == true)
				return true;
		return false;	
	}

	@Override
	public boolean update(User user, String nextPassword) {
		if (userdao.isExist(user.getUserName(), user.getPassword()) == true)
			if(userdao.update(user, nextPassword) == true)
				return true;
		return false;
	}

	@Override
	public User find(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRoles(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRole(User user, List<Role> roles) {
		// TODO Auto-generated method stub
		
	}

}
