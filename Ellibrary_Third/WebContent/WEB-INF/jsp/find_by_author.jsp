<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Найти книги автора</title>
</head>
<body>
<form action="contoller" method="post">
<input type="hidden" name="command" value="find_by_author"></br>
Введите имя автора:
<input type="text" name="author" value="">
</br>
<input type="submit" name="submit" value="найти">


</form>
</body>
</html>