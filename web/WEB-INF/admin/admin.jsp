<%-- 
    Document   : admin
    Created on : 21.02.2017, 17:01:10
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Страница администратора</title>
    </head>
    <body>
        <h1>Привет, ${username}, Вы зашли на защищенный ресурс!</h1>
        <a href="logout">logout</a>
        
<!--        <p>Вы можете назначить роль пользователю</p>
        <form action="setRole" method="POST">
            <select name="select_user">
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}">${user.name} ${user.surname}</option>
                </c:forEach>
            </select>
            <select name="role">
                <option value="ADMIN">admin</option>
                <option value="EDITOR">редактор</option>
                <option value="READER">читатель</option>
                <option value="GUEST">гость</option>
            </select>
            <input type="submit" value="Назначить">
        </form>-->
    </body>
</html>
