<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Display users</title>
</head>
<body>
<div align="center">
USERS
<%
java.util.List<by.htp.ellib.entity.UserData> users = (java.util.List<by.htp.ellib.entity.UserData>)request.getAttribute("users");
%>

 <table border="2" >
 <tr><td>ID</td><td>Login</td><td>Name</td><td>Surname</td><td>Email</td><td>Sex</td><td>Birth Year</td></tr>
    <c:forEach items="${requestScope.users}" var="user">
     <tr> 
        <td> ${user.id} </td>
        <td> ${user.login} </td>      
        <td> ${user.name}   </td>
        <td> ${user.surname}   </td>
        <td> ${user.email}   </td>
        <td> ${user.sex}   </td>
        <td> ${user.birthYear}   </td>
     </tr>    
    </c:forEach>
</table>
</div>
</body>
</html>