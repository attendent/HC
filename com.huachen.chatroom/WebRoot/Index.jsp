<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
	function change1() {
		if (document.getElementById("remark").style.display = "") {
			document.getElementById("remark").style.display = "none";
		} else {
			document.getElementById("remark").style.display = "";
		}
	}
</script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/JS.js"></script>
<script type="text/javascript" src="js/WebSocket.js"></script>
<title>网上聊天室</title>
</head>

<body topmargin="0" leftmargin="0" rightmargin="0">
	<form action="">
		<table width="100%" height="79" border="0" cellpadding="0"
			cellspacing="0" align=center>
			<tr>
				<td bgcolor="F9A859" valign="top">
					<table width="100%" height="50" border="0" align="center"
						cellpadding="0" cellspacing="0" bgcolor="FBEAD0">
						<tr>
							<td align="center" style="font-size: 40px;">网上聊天室</td>
						</tr>
					</table>

				</td>
			</tr>
			<tr>
				<td bgcolor="F9A859" valign="top">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="center" style="font-size: 20px" valign="middle">欢迎
								<c:if test="${!empty(nickName) }">
              	${nickName }
              </c:if> 访问! 当前在线人数为<c:if test="${empty(peopleOnline) }">
                     0
                     </c:if> <c:if test="${!empty(peopleOnline) }">
                     ${peopleOnline }
                     </c:if>人
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>


<div id="left" onclick="printf()">
	<div class="menu">
		<div style="height: 160px; overflow: auto">
			<h3>我的好友</h3>
			<p>
				<c:forEach items="${friends }" var="friend">
   ${friend.nickName }
   <%-- <a href="DelFriend?friendId=${item.id }"><button type="button">刪除好友</button></a> --%>
					<form action="DelFriend" method=post>
						<input type="text" style="display: none" name="friendId"
							value="${friend.id }">
						<button type="submit" value="删除好友">删除好友</button>
					</form>

					<button type="button" onclick="change1()">修改备注</button>
					<form action="Remark" id="remark" method=post style="display: none">
						<input type="hidden" name="friendId" value="${friend.id }">
						<label>修改备注:</label><input type="text" name="remark" value="楼下小黑">
						<button type="submit" value="确定">确定</button>
					</form>
					<form action="Whisper" method=post>
						<input type="hidden" name="friendId" value="${friend.id }">
						<input type="hidden" name="friendName" value="${friend.nickName }">
						<button type="submit">私聊好友</button>

					</form>
					<form action="Invite" method=post>
						<input type="hidden" name="friendId" value="${friend.id }">
						<input type="hidden" name="roomId" value="${chatRoom.id }">
						<button type="submit">邀请好友</button>
						
					</form>
					<%-- <a href="Remark?friendId=${item.id }"><button type="button">修改备注</button></a> --%>
					<br>
				</c:forEach>
			</p>
		</div>

		<div style="height: 150px; overflow: auto">
			<h3>聊天室成员</h3>
			<p>
				<c:forEach items="${userlist }" var="item">
   ${item.nickName }
   <br>
				</c:forEach>
			</p>
		</div>

		<div style="height: 160px; overflow: auto">
			<h3>聊天室列表</h3>
			<p>
				<c:forEach items="${chatRooms }" var="rooms">
   ${rooms.roomName }
   <form action="EnterRoom" method="post" name="enterRoom">
						<input type="hidden" name="roomId" value="${rooms.id }"> <input
							type="hidden" name="roomName" value="${rooms.roomName }">
						<button type="submit">进入该群</button>
					</form>
					<br>
				</c:forEach>
			</p>
		</div>
	</div>




	<form action="AddFriend" method="post" name="form1"
		onsubmit="return test() ">
		<label>用&nbsp;户&nbsp;名(0-16)：</label> <input type="text"
			name="friendName" size="20" />
		<button type="submit">&nbsp;&lt;添加好友&gt;</button>
	</form>

</div>

<c:if test="${!empty(msg) }">
	<script>
		alert("${msg }");
	</script>
</c:if>

<body>
	<div id="main">
		<h1>
			<c:if test="${!empty(chatRoom) }">${chatRoom.roomName }</c:if>
		</h1>
		<textarea id="message" style="resize: none" cols="140" rows="25"
			readonly="readonly" name="show_textarea">${contents }</textarea>
		<textarea id="text" style="resize: none" cols="140" rows="5"
			name="input_textarea"></textarea>
		<button onclick="send()" onclick="clear()">发送消息</button>
		<a href="GetChatList?roomId=${chatRoom.id }"><button>消息记录</button></a>
		<br> <a href="Register.jsp">前往注册</a> <a href="Login.jsp">前往登录</a>
	</div>
</body>

<script>
	var websocket = null;
	// 判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		//alert('当前浏览器支持 websocket');
		websocket = new WebSocket(
				'ws://localhost:8080/com.huachen.chatroom/newwebsocket/'
						+ "${nickName }" + '//' + "${chatRoom.id}" + '//'
						+ "${chatRoom.roomName}");
	} else {
		alert('当前浏览器 Not support websocket')
	}
	// 连接发生错误的回调方法
	websocket.onerror = function() {
		setMessageInnerHTML("WebSocket连接发生错误");
		reconnect();
	};
	// 连接成功建立的回调方法
	websocket.onopen = function() {
		//setMessageInnerHTML("WebSocket连接成功");
		heartCheck.start();
	}
	// 接收到消息的回调方法
	websocket.onmessage = function(event) {
		setMessageInnerHTML(event.data);
		heartCheck.reset();
	}
	// 连接关闭的回调方法
	websocket.onclose = function() {
		setMessageInnerHTML("WebSocket连接关闭");
		reconnect();
	}
	// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		closeWebSocket();
	}
	// 将消息显示在网页上
	function setMessageInnerHTML(innerHTML) {
		document.getElementById('message').innerHTML += innerHTML;
	}
	// 关闭WebSocket连接
	function closeWebSocket() {
		websocket.close();
	}
	// 发送消息
	function send() {
		var message = document.getElementById('text').value;
		websocket.send(message);
	}
	var t = document.getElementById("message");
	setInterval(function() {
		t.scrollTop = t.scrollHeight;
	}, 1000);
	function clear() {
		$('#resultComment').textbox("setValue", "");
	}
	var heartCheck = {
		timeout : 60000,//60ms
		timeoutObj : null,
		reset : function() {
			clearTimeout(this.timeoutObj);
			this.start();
		},
		start : function() {
			this.timeoutObj = setTimeout(function() {
				ws.send("HeartBeat");
			}, this.timeout)
		}
	}
</script>
</html>