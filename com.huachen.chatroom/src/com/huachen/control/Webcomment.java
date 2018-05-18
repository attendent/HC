package com.huachen.control;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.huachen.model.ChatContent;
import com.huachen.service.ChatService;
import com.huachen.service.Impl.ChatServiceImpl;

@ServerEndpoint("/newwebsocket/{nickName}/{roomId}/{roomName}")
public class Webcomment {
	String chatRecord = "";
	private static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<Webcomment> webSocketSet = new CopyOnWriteArraySet<Webcomment>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	@OnOpen
	public void onOpen(@PathParam("nickName") String nickName, @PathParam("roomId") String roomId,
			@PathParam("roomName") String roomName, Session session) {
		this.session = session;
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
		this.session = session;
		webSocketSet.add(this);

		String message = String.format("[%s,%s]\n", nickName, "加入聊天室");
		// 群发消息
		for (Webcomment item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	@OnClose
	public void onClose(@PathParam("nickName") String nickName) {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
		String message = String.format("[%s,%s]\n", nickName, "退出聊天室");
		// 群发消息
		for (Webcomment item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	@OnMessage
	public void onMessage(String message, @PathParam("nickName") String nickName, @PathParam("roomId") String roomId,
			@PathParam("roomName") String roomName, Session session) {

		ChatContent chatContent = new ChatContent();

		chatContent.setContent(message);
		chatContent.setUserName(nickName);
		chatContent.setRoomId(Integer.parseInt(roomId));
		chatContent.setRoomName(roomName);
		chatContent.setDate(new Timestamp(System.currentTimeMillis()));

		ChatService chatservice = new ChatServiceImpl();

		// 将本次得到的信息写入数据库
		chatservice.addContent(chatContent);

		chatRecord = nickName + " 于 " + new Timestamp(System.currentTimeMillis()) + " 说： " + message + "\n";

		for (Webcomment item : webSocketSet) {
			try {
				item.sendMessage(chatRecord);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		Webcomment.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		Webcomment.onlineCount--;
	}
}