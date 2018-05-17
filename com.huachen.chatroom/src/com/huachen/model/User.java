package com.huachen.model;

public class User {
	private Integer id;
	private String userName;
	private String password;
	private String nickName;
	private Integer lastLoginTime;
	private String lastChatRoom;
	private String mail;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Integer lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastChatRoom() {
		return lastChatRoom;
	}
	public void setLastChatRoom(String lastChatRoom) {
		this.lastChatRoom = lastChatRoom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}
