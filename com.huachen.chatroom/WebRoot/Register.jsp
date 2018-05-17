<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="js/JS.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>注册界面</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body onload="change()">
	<div id="tab">
		<div class="tab_box">
			<div>
				<div class="stu_error_box"></div>
				<form action="register.do" method="post" name="form1"
					onsubmit="return test()">
					<h1>注册界面</h1>
					<div id="userName">
						<label>用&nbsp;户&nbsp;名(0-16)：</label> <input type="text"
							name="userName" size="20" value="${userName }" />
					</div>
					<div id="nickName">
						<label>昵&nbsp;&nbsp;&nbsp;&nbsp;称(0-16)：</label> <input type="text"
							name="nickName" size="20" value="${nickName }" />
					</div>
					<div id="password">
						<label>密&nbsp;&nbsp;&nbsp;&nbsp;码(0-16)：</label> <input
							type="password" name="password" size="20" value="" />
					</div>
					<div id="mail">
						<label>邮&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;&nbsp;&nbsp;：</label> <input
							type="text" name="mail" id="mail" size="20" value="${mail }" />
					</div>
					<div id="code">
						<label>验&nbsp;&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
						<input type="text" id="inputCode" name="inputCode" /> <img
							src="pictureCode" id="myimg" onclick="change()"
							style="cursor: pointer" />
					</div>
					<c:if test="${!empty(msg) }">
						<script type="text/javascript">alert( "${msg }" )</script>
					</c:if>
					<div id="login">
						<button type="submit" >&nbsp;&lt;注册&gt;</button>
						<a href="Login.jsp"><button type="button" value="返回"
								name="button" >&nbsp;&lt;返回&gt;
							</button></a> <a href="Update.jsp"><button id="Button" type="button"
								value="修改密码" >&nbsp;&lt;修改密码&gt;
							</button></a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>