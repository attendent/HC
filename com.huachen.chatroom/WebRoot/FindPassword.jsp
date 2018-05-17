<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="js/JS.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>找回密码</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="tab"> 
		<div class="tab_box">
			<div id="tab">
				<div class="stu_error_box"></div>
				<form action="FindPassword" method="post" name="form1"
					onsubmit="return test() ">
					<h1>找回密码界面</h1>
					<div id="username">
						<label>用&nbsp;户&nbsp;名(0-6)：</label> <input type="text"
							name="userName" size="20" value="${user.userName }" />
					</div>
					<div id="code">
						<label>验&nbsp;&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
						<input type="text" id="inputCode" name="inputCode" /> <img
							src="pictureCode" id="myimg" onclick="change()"
							style="cursor: pointer" />
					</div>
					<div id="login">
						<button type="submit">
							&nbsp;&lt;发送邮件&gt;
						</button>
						 <a href="Login.jsp"><button id="Button" type="button" >&nbsp;&lt;回到登录界面&gt;
							</button></a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>