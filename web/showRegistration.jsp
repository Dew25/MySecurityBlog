<%-- 
    Document   : showRegistration
    Created on : Jan 18, 2019, 11:56:37 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Регистрация</title>
    </head>
    <body>
        <h1>Зарегистрируйтесь!</h1>
        <form action="registration" method="POST">
            Имя:<br>
            <input type="text" name="name"><br>
            Фамилия:<br>
            <input type="text" name="surname"><br>
            email:<br>
            <input type="text" name="email"><br>
            Логин:<br>
            <input type="text" name="login"><br>
            Пароль:<br>
            <input type="password" name="password1"><br>
            Повторите пороль:<br>
            <input type="password" name="password2"><br>
            <br><br>
            <input type="submit" value="Зарегистрироваться">
        </form>
    </body>
</html>
