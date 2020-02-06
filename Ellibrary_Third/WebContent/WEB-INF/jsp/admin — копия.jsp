<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/html" href="styleLibrary.css">
<title>АДМИН</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="resources.locale" var="loc" />
    <fmt:message bundle="${loc}" key="locale.admin.hello_message"   var="hello_message" />
    <fmt:message bundle="${loc}" key="locale.admin.to_do_message"   var="to_do_message" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.en" var="locale_button_en" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.ru" var="locale_button_ru" />
	<fmt:message bundle="${loc}" key="locale.admin.add_book"   var="add_book" />
	<fmt:message bundle="${loc}" key="locale.admin.delete_book"   var="delete_book" />
	<fmt:message bundle="${loc}" key="locale.admin.show_books"   var="show_books" />
	<fmt:message bundle="${loc}" key="locale.admin.see_users"   var="see_users" />
	<fmt:message bundle="${loc}" key="locale.admin.find_by_author"   var="find_by_author" />
	<fmt:message bundle="${loc}" key="locale.admin.find_by_genre"   var="find_by_genre" />
	<fmt:message bundle="${loc}" key="locale.admin.find_by_title"   var="find_by_title" />
 </br>
<div align="right">
<form action="contoller" method="post" >
   <input type="hidden" name="command" value="change_locale">
   <input type="hidden" name="locale" value="ru">
   <input type="submit" name="${locale_button_ru}" value="${locale_button_ru}" />
</form>
<form action="contoller" method="post" >
   <input type="hidden" name="command" value="change_locale">
   <input type="hidden" name="locale" value="eng">
   <input type="submit" name="${locale_button_en}" value="${locale_button_en}" />
</form>
</div>

	<h1>${hello_message}</h1>
	<h2>${to_do_message}</h2>
	
	<a href="contoller?command=go_to_add_new_book"> ${add_book}</a></br>
	<a href="contoller?command=go_to_delete_book"> ${delete_book}</a></br>
    <a href="contoller?command=show_all_books">${show_books}</a></br>	
    <a href="contoller?command=see_all_users">${see_users}</a></br>	
    <a href="contoller?command=go_to_find_by_author">${find_by_author}</a></br>
 	<a href="contoller?command=go_to_find_by_genre">${find_by_genre}</a></br>
	<a href="contoller?command=go_to_find_by_title">${find_by_title}</a></br>
	<br />
	<br/>
	
	
	</form>

	
</body>
</html>