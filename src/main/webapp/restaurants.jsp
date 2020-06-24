<%--
  Created by IntelliJ IDEA.
  User: Nata
  Date: 07.06.2020
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Restaurants</title>
    <br>
    <h3><a href="index.html">Main Page</a> </h3>
    <hr>
    <h2>Restaurans</h2>
</head>

<body>
<%--    <form method="post" action="restaurants">--%>
    <form method="post">
    <table align="center">
        <thead>
        <tr align="center" style="font-style: italic">
            <td>Id</td>
            <td>Restaurant</td>
            <td>Rating</td>
            <td>Menu</td>
            <td>Vote</td>
        </tr>
        </thead>
    <c:forEach items="${rest}" var="res">
        <jsp:useBean id="res" type="ru.grig.ratingRestaurant.with_restaurant.RestaurantWithRating" scope="page" />
        <tr align="left" style="font-style: italic">
        <td>${res.id}</td>
        <td>${res.name}</td>
        <td>${res.rating}</td>
<%--            <td>${res.menu}</td>--%>
            <td><a  href="restaurants?action=menu&id=${res.id}">Menu</a> </td>
<%--         <td><input name="vote" type="checkbox" name="vote" value="${res.id}"  /> </td>--%>
         <td><input name="vote" type="radio" name="vote" value="${res.id}"  /> </td>
        </tr>
    </c:forEach>
    </table>

        <button type="submit" name="butOK" value="1">OK</button>
        <button onclick="window.history.back()" name="butCancel" value="0">Cancel</button>
    </form>
</body>
</html>
