<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>default</title>
</head>
<body>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="resources.locale" var="loc" />
    <fmt:message bundle="${loc}" key="locale.default.welcome_message"   var="welcome_message" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.en" var="locale_button_en" />
    <fmt:message bundle="${loc}" key="locale.default.locale_botton.ru" var="locale_button_ru" />
    <fmt:message bundle="${loc}" key="locale.default.registration" var="registration" />

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


	<h1>${welcome_message}</h1>

	<form action="contoller"     method="post">
		<input      type="hidden"    name="command"  value="authorization">
		Login: 
		<input type="text"      name="login"    value="" /> <br /> 
		Password: 
		<input type="password"  name="password" value="" /> <br /> 
		<input type="submit"    name="submit"   value="press me" />
	</form>

	<h3>
		<c:out value="${requestScope.error}" />
	</h3>

	<a href="contoller?command=go_to_registration_page">${registration}</a>


	<br />
	<br />
	<br />
	
</body>
</html>