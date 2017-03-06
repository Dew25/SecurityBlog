<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Добро пожаловать</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/index.css"
    </head>
    <body>
        <div>Добро пожаловать в блог</div>
        <p></p>
        <p>Для начала работы необходимо 
            <a href="newuser">добавить нового пользователя</a>
            <br>Создайте логин пользователя "admin" с паролем "12345".
            <br> В базе данных в таблице REGUSER, в поле "GROUPS" замените "GUESTS" на  "ADMINS",
        </p>
        <a href="admin">Перейти на защищенную страничку администратора</a>
        <br>
        <a href="user">Перейти на защищенную страничку зарегистрированного пользователя</a>
        <p>В блоге опубликовано:</p>
        <table class="tab-article">
        <c:forEach var="article" items="${articles}">
            <tr class="tr-article"><td class="td-article"></td>
                <td class="td-article">${article.title}</td>
            </tr>
            <tr>
                <td class="td-article">${article.addArticleTime}</td>
                <td class="td-article">${article.author.login}</td>
            </tr>
            <tr>
                <td class="td-article"></td>
                <td class="td-article">${fn:substring(article.article,0,300)} ...<a href="user?article_id=${article.id}">смотреть все</a></td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
