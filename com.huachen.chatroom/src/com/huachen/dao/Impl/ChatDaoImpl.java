package com.huachen.dao.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.huachen.dao.ChatDao;
import com.huachen.model.ChatContent;
import com.huachen.model.ChatRoom;
import com.huachen.model.User;
import com.huachen.util.JdbcUtils;
import com.mysql.jdbc.Connection;

public class ChatDaoImpl implements ChatDao {
	JdbcUtils util = new JdbcUtils();

	@Override
	public boolean isExistRoom(String roomName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from chatroom where roomname=?";
		JdbcUtils util = new JdbcUtils();
		try {
			con = JdbcUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, roomName);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return false;	}

	@Override
	public boolean isExistRoom(Integer roomId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from chatroom where id='"+ roomId +"'";
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
	public List<ChatRoom> getAllChatRooms() {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "select * from chatroom";
		ResultSet rs = null;
		List<ChatRoom> chatRooms = new ArrayList<>();
		try {
			con = JdbcUtils.getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			ChatRoom chatRoom;
			while (rs.next()) {
				chatRoom = new ChatRoom();
				chatRoom.setId(rs.getInt("tid"));
				chatRoom.setRoomName(rs.getString("roomname"));
				chatRooms.add(chatRoom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chatRooms;
	}

	@Override
	public List<ChatContent> getAllContents(Integer roomId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from chatcontent where roomid=? order by date asc";
		List<ChatContent> chatContents = new ArrayList<>();
		try {
			con = JdbcUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, roomId);
			rs = ps.executeQuery();
			ChatContent chatContent;
			while (rs.next()) {
				chatContent = new ChatContent(rs.getInt("id"), rs.getString("content"), rs.getInt("roomid"),
						rs.getString("roomName"), rs.getInt("userid"), rs.getString("username"),
						rs.getTimestamp("date"));
				Timestamp now = new Timestamp(System.currentTimeMillis());
				long t = now.getTime() - chatContent.getDate().getTime();
				if (t / 1000 <= (long) 3600 * 24 * 7) {
					chatContents.add(chatContent);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chatContents;
	}

	@Override
	public boolean addContent(ChatContent chatContent) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into chatcontent(content,roomid,username,date,roomname) values(?,?,?,?,?)";
		try {
			con = JdbcUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, chatContent.getContent());
			ps.setInt(2, chatContent.getRoomId());
			ps.setString(3, chatContent.getUserName());
			ps.setTimestamp(4, chatContent.getDate());
			ps.setString(5, chatContent.getRoomName());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean createRoom(String roomName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getCon();
			String sql = "insert into chatroom (roomname) values(?);";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, roomName);
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
	public List<User> getAllUsers(Integer roomId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();
		try {
			con = JdbcUtils.getCon();
			String sql = "SELECT user.* FROM user,user_room ur WHERE ur.roomid = '" + roomId + "' AND user.id = ur.userid";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setNickName(rs.getString(4));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return users;
	}

	@Override
	public ChatRoom find(String roomName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ChatRoom chatRoom = new ChatRoom();
		try {
			con = JdbcUtils.getCon();
			String sql = "SELECT * FROM chatroom WHERE roomname = '" + roomName + "'";
			ps = (PreparedStatement) con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				chatRoom.setId(rs.getInt(1));
				chatRoom.setRoomName(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(ps, con, rs);
		}
		return chatRoom;
	}
}
