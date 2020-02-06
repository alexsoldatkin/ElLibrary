<%@page import="by.htp.ellib.controller.Controller"%>
<%@ page language="java" import="by.htp.ellib.entity.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>main page</title>
</head>
<body>
 
 <h1>HELLO, 
 <% User user;
	String userLogin;
	String userName;
 	int id;
 	user=(User)request.getAttribute("user");
 	id=user.getId();
 	userLogin=user.getLogin();
 	userName=user.getLogin();
 %>
   	<c:out value="${requestScope.user.name}"/>
   	!
 </h1>
 <a href="contoller?command=go_to_find_by_author">Найти книги по автору</a></br>
 <a href="contoller?command=go_to_find_by_genre">Найти книги по жанру</a></br>
 <a href="contoller?command=go_to_find_by_title">Найти книги по названию</a></br>
 <a href="contoller?command=show_all_books">Показать все книги</a></br>
 
</body>
</html>