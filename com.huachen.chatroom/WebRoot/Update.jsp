<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<script type="text/javascript" src="JS/JS.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改密码</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="tab">
		<div class="tab_box">
			<div>
				<div class="stu_error_box"></div>
				<form action="login.do" method="post" name="form1"
					onsubmit="return test() ">
					<h1>修改密码界面</h1>
					<div id="username">
						<label>用&nbsp;户&nbsp;名(0-6)：</label> <input type="text"
							name="username" size="20" value="123" />
					</div>
					<div id="password">
						<label>原&nbsp;密&nbsp;码(0-6)：</label> <input
							type="password" name="password" size="20" value="123" />
					</div>
					<div id="password">
						<label>修&nbsp;改&nbsp;为(0-6)：</label> <input
							type="password" name="nextpassword" size="20" value="123" />
					</div>
					<c:if test="${!empty(msg) }">
						<script type="text/javascript">alert( "${msg }" )</script>
					</c:if>
					<div id="login">
						<button type="submit">&nbsp;&lt;确定&gt;</button>
						<a href="register.jsp"><button type="button" value="注册"
								name="button" >&nbsp;&lt;注册&gt;
							</button></a> <a href="index.jsp"><button id="Button" type="button"
								value="返回登录界面" >&nbsp;&lt;返回登录界面&gt;
							</button></a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>