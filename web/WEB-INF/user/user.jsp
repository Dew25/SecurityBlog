<%-- 
    Document   : user
    Created on : 02.03.2017, 22:07:24
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.servletContext.contextPath}/css/user.css" rel="stylesheet" type="text/css">
        <title>Страница зарегистрированного пользователя</title>
    </head>
    <body>
        <h1>Привет, ${username}!</h1>
        <a href="logout">logout</a><br>
        <table class="tab-article">
        
            <tr class="tr-article">${article.title}<td class="td-article"></td>
                <td class="td-article"></td>
            </tr>
            <tr>
                <td class="td-article">Автор: ${article.userLogin}</td>
                <td class="td-article">Дата публикации: ${article.date}</td>
            </tr>
            <tr>
                <td class="td-article" colspan="2">${article.article}</td>
            </tr>
        
        </table>
            <p>Комментарии к статье:</p>
        <table>
            <c:forEach var="comment" items="${article.comments}">
                <tr>
                    <td>Автор: ${comment.userLogin}</td>
                    <td>Дата: ${comment.date}
                </tr>
                <tr>
                    <td colspan="2">${comment.title}</td>
                </tr>
                <tr>
                    <td colspan="2">${comment.text}</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${userGroup=='ADMINS'}">
                            <a href="deleteComment?article_id=${article.id}&comment_id=${comment.id}" name="delete_comment">Удалить</a>
                        </c:if>
                    </td>
                </tr>
                
            </c:forEach>
        </table>
        <form action="addComment" method="POST">
            <table class="tab-comment">
                <tr>
                    <td colspan="2"><input type="text" name="comment_title"></td>
                </tr>
                <tr>
                    <td colspan="2"><textarea name="comment_text"></textarea>
                        <input type="hidden" name="article_id" value="${article.id}">
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Отправить"</td>
                </tr>
            </table>
        </form>
    </body>
</html>
