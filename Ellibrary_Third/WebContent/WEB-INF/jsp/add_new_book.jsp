<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>add new book</title>
</head>
<body>
<form action="contoller" method="post">
<input type="hidden" name="command" value="add_new_book"/>
Добавление новой книги
<br/>
Введите название:
<input type="text" name="title" value=""/>
<br/>
Введите автора:
<input type="text" name="author" value=""/>
<br/>
Введите цену:
<input type="number" name="price" value=""/>
<br/>
Введите жанр:
<input type="text" name="genre" value=""/>
<br/>
Введите год издания:
<input type="number" name="release_year" value=""/>
<br/>
<br/>
  	<input type="submit" name="submit" value="Add new book"/>
</form>
</body>
</html>