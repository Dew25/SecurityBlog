<%-- 
    Document   : newArticle
    Created on : 03.03.2017, 17:46:00
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить новую статью</title>
    </head>
    <body>
        <h1>Привет, ${username}!</h1>
        Добавить новую статью
        <br>${info}
        <br>
        <form action="addarticle" method="POST">
        <input type="text" name="title" placeholder="Заголовок статьи">
        <br><textarea name="article" placeholder="Текст статьи"></textarea>
        <input type="submit" value="Добавить статью">
        </form>
        <br>
        Заголовки опубликованных статей
        <br>
        <c:forEach var="article" items="${articles}" varStatus="status">
            <p><a href="user?article_id=${article.id}">${status.index+1}. ${article.title}</a> <a href="deletearticle?id=${article.id}">Удалить</a></p>
        </c:forEach>
        
    </body>
</html>
