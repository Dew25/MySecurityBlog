<%-- 
    Document   : admin
    Created on : Jan 10, 2019, 9:18:21 AM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Страница администратора</title>
    </head>
    <body>
        <h1>Hello ${info}!</h1>
        <a href="logout">Выйти</a>
        <br>
        <form action="editRole" method="POST">
            Список пользователей:<br>
            <select name="login">
                <c:forEach var="groupuser" items="${listGroupuser}">
                    <option value="${groupuser.usersLogin.login}">${groupuser.usersLogin.login}: ${groupuser.usersLogin.person.name} ${groupuser.usersLogin.person.surname}</option>
                </c:forEach>
            </select>
            <br><br>
            <input type="submit" name="makeAdmin" value="Сделать администратором"><br>
            <input type="submit" name="rmAdmin" value="Сделать сделать обычным пользователем">
        </form>
    </body>
</html>
