<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>          
    <head>
        <title>Добро пожаловать</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.servletContext.contextPath}/css/index.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div>Добро пожаловать в блог</div>
        <p></p>
        <p>Для начала работы необходимо 
            <a href="newuser">добавить нового пользователя</a>
            <br>Незарегистрированные пользователи могут видеть заголовки опубликованных статей.
            <br>Создайте логин пользователя "admin" с паролем "12345".
            <br>Создайте нового пользователя с логином "user".
            <br>Пользователи группы "USERS" могут читать полные статьи и заходить на страничку для
            <br>зарегистрированных пользователей.
        </p>
        <a href="admin">Перейти на защищенную страничку администратора</a>
        <br>
        <a href="user">Перейти на защищенную страничку зарегистрированного пользователя</a>
        <p>В блоге опубликовано:</p>
        <table class="tab-article">
        <c:forEach var="article" items="${articles}">
            <tr class="tr-article">${article.title}<td class="td-article"></td>
                <td class="td-article"></td>
            </tr>
            <tr>
                <td class="td-article">Автор: ${article.userLogin}</td>
                <td class="td-article">Дата публикации: ${article.date}</td>
            </tr>
            <tr>
                <td class="td-article" colspan="2">${fn:substring(article.article,0,300)} ...<a href="user?article_id=${article.id}">смотреть все</a></td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
