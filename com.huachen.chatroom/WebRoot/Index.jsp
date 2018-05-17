<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function change1(){
	if(document.getElementById("remark").style.display=""){
		document.getElementById("remark").style.display="none";
	}else{
		document.getElementById("remark").style.display="";
	}	
}
</script>
<link rel="stylesheet" type="text/css" href="css/main.css">
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


<div id="left">
	<div class="menu">
		<div style="height: 200px; overflow: auto">
			<h3>我的好友</h3>
			<p>
				<c:forEach items="${friends }" var="item">
   ${item.nickName }
   <%-- <a href="DelFriend?friendId=${item.id }"><button type="button">刪除好友</button></a> --%>
					<form action="DelFriend" method=post>
						<input type="text" style="display: none" name="friendId"
							value="${item.id }">
						<button type="submit" value="删除好友">删除好友</button>
					</form>

					<button type="button" onclick="change1()">修改备注</button>
					<form action="Remark"  id="remark" method=post style="display:none">
						<input type="hidden" name="friendId"
							value="${item.id }">
						<label>修改备注:</label><input type="text" name="remark" value="楼下小黑">
						<button type="submit" value="确定">确定</button>
					</form>
					<form action="Whisper" method=post>
					<input type="hidden" name="friendId" value="${item.id }">
					<input type="hidden" name="friendName" value="${item.nickName }">
					<input type="submit" value="私聊">
					
					</form>
					<%-- <a href="Remark?friendId=${item.id }"><button type="button">修改备注</button></a> --%>
					<br>
				</c:forEach>
			</p>
		</div>

		<div style="height: 200px; overflow: auto">
			<h3>聊天室成员</h3>
			<p>
				<c:forEach items="${userlist }" var="item">
   ${item.nickName }
   <br>
				</c:forEach>
			</p>
		</div>

		<h3>聊天室列表</h3>
		<p>
			<c:forEach items="${chatRooms }" var="rooms">
   ${rooms.roomName }
   <form action="EnterRoom" method="post" name="enterRoom">
   <input type="hidden" name="roomId" value="${rooms.id }">
   <input type="hidden" name="roomName" value="${rooms.roomName }">
   <input type="submit" value="进入">
   </form>
   <br>
			</c:forEach>
		</p>
	</div>
	<c:if test="${!empty(msg) }">
		<script type="text/javascript">
			alert("${msg }");
		</script>
	</c:if>
	<form action="AddFriend" method="post" name="form1"
		onsubmit="return test() ">
		<label>用&nbsp;户&nbsp;名(0-16)：</label> <input type="text"
			name="friendName" size="20" />
		<button type="submit">&nbsp;&lt;添加好友&gt;</button>
	</form>

</div>


<body>
	<div id="main">
	<h1><c:if test="${!empty(chatRoom) }">${chatRoom.roomName }</c:if></h1>
		<form action="InputInfomation" method=post>
			<textarea cols="105" rows="25" readonly="readonly"
				name="show_textarea">${input_textarea }</textarea>				
			<br>
			<textarea cols="105" rows="5" name="input_textarea"></textarea>
			<br> 
			<input type="hidden" name="userId" value="${userId }">
			<input type="hidden" name="roomId" value="${chatRoom.id }">
			<input type="hidden" name="roomName" value="${chatRoom.roomName }">
			<input type="submit" value="发送" name="button_one">
			<a href="GetChatList"><input type="button" value="查记录"
				name="inputformation"></a><br> <a href="Login.jsp">前往登录</a>
			<a href="Register.jsp">前往注册</a>
		</form>
	</div>
</body>
</html>