<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>registration</title>
</head>
<body>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="resources.locale" var="loc" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_login_message" var="enter_login" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_password_message" var="enter_password" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_email_message" var="enter_email" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_name_message" var="enter_name" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_surname_message" var="enter_surname" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_sex_message" var="enter_sex" />
    <fmt:message bundle="${loc}" key="locale.registration.enter_birthYear_message" var="enter_birthYear" />

<div align="right">
<form action="contoller" method="post" >
   <input type="hidden" name="command" value="change_locale">
   <input type="hidden" name="locale" value="ru">
   <input type="submit" name="submit" value="ru" />
</form>
<form action="contoller" method="post" >
   <input type="hidden" name="command" value="change_locale">
   <input type="hidden" name="locale" value="eng">
   <input type="submit" name="submit" value="eng" />
</form>
</div>


 <form action="contoller" method="post" >
    <input type="hidden" name="command" value="registration">
   ${enter_login}:
  	<input type="text" name="login" value=""/>
  	<br/>
  	${enter_password}:  	
  	<input type="password" name="password" value=""/>	
  	<br/>
  	${enter_email}:
  	<input type="text" name="email" value=""/>
  	<br/>
  	${enter_name}:  	
  	<input type="text" name="name" value=""/>
  	<br/>
  	${enter_surname}:
  	<input type="text" name="surname" value=""/>
  	<br/>
  	${enter_sex}:  	
  	<input type="text" name="sex" value=""/>
  	<br/>
  	${enter_birthYear}:
  	<input type="number" name="birthYear" value=""/>
  	<br/>

  	<br/>
  	
  	<input type="submit" name="submit" value="press me"/>
   </form>
</body>
</html>