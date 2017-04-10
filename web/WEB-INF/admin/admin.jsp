
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Привет, ${username}, Вы зашли на защищенный ресурс!</h1>
        <a href="logout">logout</a><br>
        <a href="newuser">Новый пользователь</a>
        <a href="newarticle">Новая статья</a>
        <p>Вы можете добавть/удалить группу (при добавлении придеживайтесь следующего соглашения:  <br>
             ИМЯ ГРУППЫ ПРОПИСНЫМИ БУКВАМИ ВО МНОЖЕСТВЕННОМ ЧИСЛЕ</p>
        <form action="newGroup" method="POST">
            <input type="text" name="new_group" placeholder="Имя группы">
            <input type="submit" value="Добавить группу">
        </form>
        <form action="deleteGroup" method="POST">
            <select name="deleteGroup">
                <c:forEach var="group" items="${groups}">
                    <option value="${group.id}">${group.groupName}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Удалить группу">
        </form>   
        <p></p>
        <form action="listGroups" method="POST">
            <input type="submit" value="Показать пользователей группы">
            <select name="selectedGroup">
                <c:forEach var="group" items="${groups}">
                    <option value="${group.id}">${group.groupName}</option>
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

