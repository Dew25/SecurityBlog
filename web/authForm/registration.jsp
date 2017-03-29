<%-- 
    Document   : registration
    Created on : 23.02.2017, 22:05:15
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.servletContext.contextPath}/css/registration.css" rel="stylesheet" type="text/css">
        <title>Регистрация</title>
    </head>
    <body>
        <form action="addNewUser" method="POST">
            <h1>Введите все данные</h1>
            <p>Имя: <input type="text" name="name" placeholder="Имя"></p>
            <p>Фамилия: <input type="text" name="surname" placeholder="Фамилия"></p>
            <p>Логин: <input type="text" name="login" placeholder="логин"></p>
            <p>Пароль: <input type="password" name="password" placeholder="password"></p>
            <p>Телефон: <input type="text" name="phone" placeholder="Телефон"></p>
            <p>Электронная почта: <input type="email" name="email" placeholder="email"></p>
            <p><input type="submit" name="submit" value="Зарегистрироваться"></p>
        </form>
    </body>
</html>
