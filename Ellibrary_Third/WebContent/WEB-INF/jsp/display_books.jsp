<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>display books</title>
</head>
<body>
<div align="center">
BOOKS

<%
java.util.List<by.htp.ellib.entity.Book> books = (java.util.List<by.htp.ellib.entity.Book>)request.getAttribute("books");
%>

 <table border="2" >
 <tr><td>TITLE</td><td>AUTHOR</td><td>PRICE</td><td>RELEASE YEAR</td><td>GENRE</td></tr>
    <c:forEach items="${requestScope.books}" var="book">
     <tr> 
        <td> ${book.title} </td>
        <td> ${book.author} </td>      
        <td> ${book.longPrice}   </td>
        <td> ${book.longReleaseYear}   </td>
        <td> ${book.genre}   </td>
     </tr>    
    </c:forEach>
</table>
</div>
</body>
</html>