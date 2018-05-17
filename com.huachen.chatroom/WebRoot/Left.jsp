<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="js/JS.js"></script>
<title>My JSP 'test.jsp' starting page</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body {
	font-size: 15px;
	padding: 00px;
}

.menu {
	width: 500px;
	border-bottom: solid 1px gray;
}

.menu h3 {
	border: solid 1px gray;
	height: 30px;
	line-height: 30px;
	padding-left: 10px;
	padding: 0 5px;
	border-bottom: none;
	cursor: pointer;
}

.menu p {
	border-left: solid 1px gray;
	border-right: solid 1px gray;
	padding: 20px 0;
	padding-left: 5px;
}

.changecolor {
	background-color: red;
}
</style>
<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$(".menu p:not(:first)").hide();
		$(".menu h3").css("background", "#ccc");
		$(".menu h3").hover(
				function() {
					$(this).css("background-color", "gray").siblings("h3").css(
							"background-color", "#ccc");
				});
		$(".menu h3").mouseleave(function() {
			$(".menu h3").css("background", "#ccc");
		}); //离开时将其变为原来颜色
		var index = $(".menu h3").index(this);
		$(".menu h3").click(function() {
			$(this).next("p").slideToggle().siblings("p").slideUp();
		});
	});
</script>
</head>

<body>
	<div class="menu">
		<h3>我的好友</h3>
		<p>
			<c:forEach items="${friends }" var="item">
   ${item }
   </c:forEach>
		</p>
		<h3>在线人员</h3>
		<p>
			<a href="index.jsp">比尔盖茨</a><br /> <br /> <a href="index.jsp">比尔盖茨</a><br />
			<br /> <a href="index.jsp">比尔盖茨</a><br /> <br /> <a
				href="index.jsp">比尔盖茨</a><br /> <br /> <a href="index.jsp">比尔盖茨</a><br />
			<br /> <a href="index.jsp">比尔盖茨</a><br /> <br /> <a
				href="index.jsp">比尔盖茨</a><br />
		</p>
	</div>
	<c:if test="${!empty(msg) }">
						<script type="text/javascript">alert( "${msg }" );</script>
	</c:if>
	<form action="AddFriend" method="post" name="form1"
					onsubmit="return test() ">
		<label>用&nbsp;户&nbsp;名(0-16)：</label> <input type="text"
			name="friendName" size="20" />
		<button type="submit">&nbsp;&lt;添加好友&gt;</button>
	</form>
</body>
</html>
