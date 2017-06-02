
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
        <form action="addNewUser" method="POST">
            <div class="regWrapper"
            <h1>Введите все данные</h1>
            <p>Имя: <input type="text" name="name" placeholder="Имя"></p>
            <p>Фамилия: <input type="text" name="surname" placeholder="Фамилия"></p>
            <p>Логин: <input type="text" name="login" placeholder="логин"></p>
            <p>Пароль: <input type="password" name="password" placeholder="password"></p>
            <p>Телефон: <input type="text" name="phone" placeholder="Телефон"></p>
            <p>Электронная почта: <input type="email" name="email" placeholder="email"></p>
            <p><input type="submit" name="submit" value="Зарегистрироваться"></p>
            </div>
        </form>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
