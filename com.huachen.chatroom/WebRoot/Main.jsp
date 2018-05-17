<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>滑稽聊天室</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
  </head>
  
  <body>
  <form action="InputInfomation" method=post>
  <textarea  cols="105" rows="25" readonly="readonly" name="show_textarea">${input_textarea }</textarea>
  <br>
  <textarea  cols="105" rows="5"  name="input_textarea"></textarea><br>
  <input type="submit" value="发送" name="button_one" >
  <a href = "GetChatList"><input type="button" value="查记录" name="inputformation" ></a><br>
 
  </form>
  </body>
</html>
