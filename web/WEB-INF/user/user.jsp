<%-- 
    Document   : user
    Created on : 02.03.2017, 22:07:24
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Страница зарегистрированного пользователя</title>
    </head>
    <body>
        <h1>Привет, ${username}!</h1>
        <a href="logout">logout</a><br>
        <table class="tab-article">
        
            <tr class="tr-article"><td class="td-article"></td>
                <td class="td-article">${article.title}</td>
            </tr>
            <tr>
                <td class="td-article">${article.addArticleTime}</td>
                <td class="td-article">${article.author.login}</td>
            </tr>
            <tr>
                <td class="td-article"></td>
                <td class="td-article">${article.article}</a></td>
            </tr>
        
        </table>
    </body>
</html>
