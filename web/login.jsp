<%-- 
    Document   : login
    Created on : Jan 10, 2019, 9:13:26 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Авторизация</title>
    </head>
    <body>
        <h1>Войдите в систему</h1>
        <form action="login" method="POST">
            <div id="loginBox">
                <p><strong>Ваш логин:</strong>
                    <input placeholder="Введите логин" type="text" size="20" name="username"></p>
                <p><strong>Пароль:</strong>
                    <input placeholder="Введите пароль" type="password" size="20" name="password"></p>
                <p><input type="submit" value="Авторизоваться"></p>
            </div>
        </form>
    </body>
</html>
