<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <form action="login" method="POST">
            <div class="loginWrapper">
              <h1>Авторизуйтесь!</h1>
              <p>${info}</p>
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
<%@include file="/WEB-INF/jspf/footer.jspf" %>