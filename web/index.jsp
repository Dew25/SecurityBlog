<%@ page contentType="text/html" pageEncoding="UTF-8"%>
    <div id="main">
        
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
        <aside class="leftAside">
            <h2>Темы статей</h2>
            <ul>
                <li><a href="#">Тема 1</a></li>
                <li><a href="#">Тема 2</a></li>
                <li><a href="#">Тема 3</a></li>
                <li><a href="#">Тема 3</a></li>

            </ul>
        </aside>
        <section>
        <c:forEach var="article" items="${articles}">
            <article>
                <h1>${article.title}</h1>
                <div class="text-article">
                    ${fn:substring(article.article,0,300)} ...
                </div>
                <div class="fotter-article">
                    <span class="read"><a href="user?article_id=${article.id}">
                            Читать...</a></span>
                    <span class="date-article">Дата статьи: ${article.date}</span>
                </div>
            </article>
        </c:forEach>
    </section>
    </div>
