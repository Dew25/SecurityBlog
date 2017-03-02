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
        <a href="logout">logout</a><br>
        <a href="newuser">Новый пользователь</a>
        <p>Вы можете добавть новую роль</p>
        <form action="newGroup" method="POST">
            <input type="text" name="new_group" placeholder="Имя группы">
            <input type="submit" value="Добавить группу">
        </form>
        <p></p>
        <form action="listGroups" method="POST">
            <input type="submit" value="Показать пользователей группы"><select name="selectedGroup">
                <c:forEach var="gr" items="${groups}">
                    <option value="${gr.id}">${gr.groupName}</option>
                </c:forEach>
            </select>
        </form>
        <ul>
            <c:forEach var="groupUser" items="${usersInGroup}">
                <li>${groupUser.login}</li>
            </c:forEach>
        </ul>
        <p>Вы можете добавить пользователя в группу</p>
        <form action="addToGroup" method="POST">
            <select name="select_user">
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}">${user.name} ${user.surname}</option>
                </c:forEach>
            </select>
            <select name="group">
                <c:forEach var="group" items="${groups}">
                    <option value="${group.id}">${group.groupName}</option>
                </c:forEach>
            </select>
            <br>
            <input type="submit" name="add" value="Добавить пользователя в группу">
            <input type="submit" name="remove" value="Удалить пользователя из группы">
        </form>
    </body>
</html>
