<%-- 
    Document   : login
    Created on : 21.02.2017, 16:52:42
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.servletContext.contextPath}/css/login.css" rel="stylesheet" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Авторизуйтесь!</h1>
        <p>${info}</p>
        <form action="login" method="POST">
            <p>Логин: <input type="text" name="login" placeholder="admin"></p>
            <p>Пароль: <input type="password" name="password" placeholder="12345"></p>
            <p><input type="submit" name="submit" value="Авторизоваться"></p>
            <input type="hidden" value="${path}" name="path">
        </form>
    </body>
</html>
