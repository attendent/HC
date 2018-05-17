package com.huachen.model;

import java.sql.Timestamp;

public class ChatContent {
	private Integer id;
	private String content;
	private Integer romeId;
	private String roomName;
	private Integer userId;
	private String userName;
	private Timestamp date;

	public ChatContent() {
	}

	public ChatContent(Integer id, String content, Integer roomId, String roomName, Integer userId, String userName,
			Timestamp date) {
		this.id = id;
		this.content = content;
		this.romeId = roomId;
		this.setRoomName(roomName);
		this.userId = userId;
		this.userName = userName;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRoomId() {
		return romeId;
	}

	public void setRoomId(int tid) {
		this.romeId = tid;
	}
	
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(Integer id) {
		this.userId = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
