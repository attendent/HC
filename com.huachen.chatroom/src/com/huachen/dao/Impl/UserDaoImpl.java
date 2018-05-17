package com.huachen.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huachen.dao.UserDao;
import com.huachen.model.ChatRoom;
import com.huachen.model.Role;
import com.huachen.model.User;
import com.huachen.util.JdbcUtils;

public class UserDaoImpl implements UserDao {

	JdbcUtils util = new JdbcUtils();

	@Override
	public boolean isExist(String userName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where username=?";
		JdbcUtils util = new JdbcUtils();
		try {
			con = JdbcUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;
	}

	@Override
	public boolean isExist(String userName, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where username=? and password=? ";
		JdbcUtils util = new JdbcUtils();
		try {
			con = JdbcUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;
	}

	// 注册
	@Override
	public boolean register(User user) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getCon();
			String sql = "insert into user (username,password,nickname,mail) values(?,?,?,?);";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickName());
			ps.setString(4, user.getMail());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;
	}

	// 修改
	@Override
	public boolean update(User user, String nextpassword) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getCon();
			String sql = "update user set password='" + nextpassword + "' where username='" + user.getUserName() + "'";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;
	}

	// 得到所有用户
	@Override
	public List<User> getListAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();// 创建list集合，用于保持User对象
		try {
			con = JdbcUtils.getCon();
			String sql = "select * from user";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();// 创建User对象用于保持从数据看查出来的数据
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setNickName(rs.getString(4));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return list;
	}

	// 得到用户的角色
	@Override
	public List<Role> getRoles(Integer userId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Role> list = new ArrayList<Role>();
		try {
			con = JdbcUtils.getCon();
			String sql = "SELECT r.* FROM role r, user_role ur WHERE ur.userid = '" + userId + "' AND r.id = ur.roleid";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt(1));
				role.setRoleName(rs.getString(2));
				list.add(role);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return list;
	}

	// 修改用户角色
	@Override
	public void updateRole(User user, List<Role> roles) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getCon();
			String sql = "DELETE FROM user_role WHERE userid = '" + user.getId() + "'";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();

			sql = "INSERT INTO user_role (userid,roleid) VALUES(?,?)";
			ps = (PreparedStatement) con.prepareStatement(sql);
			for (Role role : roles) {
				ps.setInt(1, user.getId());
				ps.setInt(2, role.getId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
	}

	//找到用戶
	@Override
	public User find(Integer id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();
		try {
			con = JdbcUtils.getCon();
			String sql = "SELECT * FROM user WHERE id = '" + id + "'";
			ps = (PreparedStatement) con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return user;
	}

	@Override
	public User find(String userName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();
		try {
			con = JdbcUtils.getCon();
			String sql = "SELECT * FROM user WHERE username = '" + userName + "'";
			ps = (PreparedStatement) con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setNickName(rs.getString(4));
				user.setLastLoginTime(rs.getInt(5));
				user.setLastChatRoom(rs.getString(6));
				user.setMail(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return user;
	}

	//得到所有朋友
	@Override
	public List<User> getFriends(Integer userId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();
		try {
			con = JdbcUtils.getCon();
			String sql = "SELECT user.* FROM user,user_friend uf WHERE uf.userid = '" + userId + "' AND user.id = uf.friendid";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setNickName(rs.getString(4));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return list;
	}

	@Override
	public boolean addFriend(Integer userId, Integer friendId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getCon();
			String sql = "INSERT INTO user_friend (userid,friendid) VALUES(?,?)";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, friendId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;
	}

	@Override
	public boolean isExist(Integer userId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where id='"+ userId +"'";
		JdbcUtils util = new JdbcUtils();
		try {
			con = JdbcUtils.getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;
	}

	@Override
	public boolean delFriend(Integer userId, Integer friendId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getCon();
			String sql = "DELETE FROM user_friend WHERE userid = '" + userId + "' AND friendId = '"+ friendId +"'";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;
	}

	@Override
	public boolean isFriend(Integer userId, Integer friendId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getCon();
			String sql = "SELECT * FROM user_friend WHERE userid = '"+ userId +"' ANd friendid = '"+ friendId +"'";
 			ps = (PreparedStatement) con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;
	}

	@Override
	public List<ChatRoom> getChatRooms(Integer userId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ChatRoom> chatRooms = new ArrayList<>();// 创建list集合，用于保持User对象
		try {
			con = JdbcUtils.getCon();
			String sql = "SELECT c.* FROM chatroom c,user_room uc WHERE uc.userid = '"+ userId +"' AND c.id = uc.roomid";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ChatRoom chatRoom = new ChatRoom();
				chatRoom.setId(rs.getInt(1));
				chatRoom.setRoomName(rs.getString(2));
				chatRooms.add(chatRoom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return chatRooms;
	}

	@Override
	public boolean updateRemark(Integer userId, Integer friendId,String remark) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getCon();
			
			String sql = "DELETE FROM user_friend WHERE userid = '" + userId + "' AND friendid = '"+ friendId +"'";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();

			sql = "INSERT INTO user_friend (userid,friendid,remark) VALUES(?,?,?)";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, friendId);
			ps.setString(3, remark);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;
	}

	@Override
	public boolean addUserRoom(Integer userId, Integer roomId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getCon();
			String sql = "INSERT INTO user_room (userid,roomid) VALUES(?,?)";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, roomId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;
	}

	@Override
	public boolean isUserRoom(Integer userId, Integer roomId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getCon();
			String sql = "SELECT * FROM user_room WHERE userid = '"+ userId +"' ANd roomid = '"+ roomId +"'";
 			ps = (PreparedStatement) con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;
	}
}
