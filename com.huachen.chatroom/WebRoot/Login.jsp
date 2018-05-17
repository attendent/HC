<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="js/JS.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登录界面</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="tab"  onclick="qwe()"> 
		<div class="tab_box">
			<div id="tab">
				<div class="stu_error_box"></div>
				<form action="login.do" method="post" name="form1"
					onsubmit="return test() ">
					<h1>登录界面</h1>
					<div id="username">
						<label>用&nbsp;户&nbsp;名(0-6)：</label> <input type="text"
							name="userName" size="20" value="${user.userName }" />
					</div>
					<div id="password">
						<label>密&nbsp;&nbsp;&nbsp;&nbsp;码(0-6)：</label> <input
							type="password" name="password" size="20" value="${user.password }" />
					</div>
					<div id="code">
						<label>验&nbsp;&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
						<input type="text" id="inputCode" name="inputCode" /> <img
							src="pictureCode" id="myimg" onclick="change()"
							style="cursor: pointer" />
					</div>
					<c:if test="${!empty(msg) }">
						<script type="text/javascript">alert( "${msg }" );</script>
					</c:if>
					<input type="checkbox" name = "autologin" value = "true"/>自动登陆
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="FindPassword.jsp">忘记密码？</a>
					<a href="Register.jsp">&lt;没有账号？&gt;</a>
					<div id="login">
						<button type="submit">
							&nbsp;&lt;登录&gt;
						</button>
						 <a href="Update.jsp"><button id="Button" type="button"
								value="修改密码" >&nbsp;&lt;去修改密码&gt;
							</button></a>
							<a href="Index.jsp"><button id="Button" type="button"
								value="返回聊天室" >&nbsp;&lt;返回聊天室&gt;
							</button></a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>