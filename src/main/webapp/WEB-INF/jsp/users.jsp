<%--
  Created by IntelliJ IDEA.
  User: Nata
  Date: 07.06.2020
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<head>
    <title>Users</title>
</head>
<body>
<br>
    <h3><a href="../../index.html">Main Page</a> </h3>
    <hr>
    <h2>Users</h2>

<hr><br>
<form method="post">
    <b>Select user</b>
    <select name="userId">
        <option value="1">Nata</option>
        <option value="2">Dima</option>
        <option value="3">Seva</option>
    </select>
    <button type="submit">OK</button>
</form>
<hr><br>

    <table align="center">
        <thead>
            <tr align="center" style="font-style: italic">
            <td>Id</td>
            <td>User</td>
            <td>Email</td>
            <td>Date</td>
            <td>Role</td>
            </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" type="ru.grig.ratingRestaurant.model.User" scope="page" />
            <tr align="left" style="font-style: italic">
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.registered}</td>
                <td>${user.role}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
