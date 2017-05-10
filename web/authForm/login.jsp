
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="login">
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
    </div>

