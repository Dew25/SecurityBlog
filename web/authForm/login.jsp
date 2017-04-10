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
        <style>
            <%@include file="../resource/css/login.css" %>
        </style>
        <!--<link href="${pageContext.servletContext.contextPath}/css/login.css" rel="stylesheet" type="text/css">-->
        <title>Авторизация</title>
    </head>
    <body>
        <h1>Авторизуйтесь!</h1>
        <p>${info}</p>
        <form action="login" method="POST">
            <div class="container">
              <label><b>Username</b></label>
              <input type="text" placeholder="Enter Username" name="login" required>

              <label><b>Password</b></label>
              <input type="password" placeholder="Enter Password" name="password" required>
              <input type="hidden" name="path">

              <button type="submit">Login</button>
              <input type="checkbox" checked="checked"> Remember me
            </div>

            <div class="container" style="background-color:#f1f1f1">
              <button type="button" class="cancelbtn">Cancel</button>
              <span class="psw">Forgot <a href="#">password?</a></span>
            </div>
        </form>
    </body>
</html>
