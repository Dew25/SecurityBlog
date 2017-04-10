
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Привет, ${username}!</h1>
        <a href="logout">logout</a><br>
        <br>
        Заголовки опубликованных статей
        <br>
        <c:forEach var="article" items="${articles}" varStatus="status">
            <p><a href="user?article_id=${article.id}">${status.index+1}. ${article.title}</a> <a href="deletearticle?id=${article.id}">Удалить</a></p>
        </c:forEach>
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

