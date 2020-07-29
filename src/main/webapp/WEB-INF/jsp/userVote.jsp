<%--
  Created by IntelliJ IDEA.
  User: Nata
  Date: 17.06.2020
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Votes</title>
    <br>
<%--    <h3><a href="restaurants?action=list">Restaurants</a> </h3>--%>
    <h3><a href="restaurants?action=list">Restaurants</a> </h3>
    <hr>
    <h2>Menu</h2>
</head>
<body>


<table align="center">
    <thead>
    <tr align="center" style="font-style: italic">
        <td>Id</td>
        <td>Restaurant</td>
        <td>Menu</td>
        <td>Price</td>
    </tr>
    </thead>
    <c:forEach items="${menus}" var="menu">
        <%--            <jsp:useBean id="menu" type="ru.grig.ratingRestaurant.model.Menu" scope="page" />--%>
        <jsp:useBean id="menu" type="ru.grig.ratingRestaurant.with_restaurant.MenuWithRestaurant" scope="page" />
        <tr align="left" style="font-style: italic">
            <td>${menu.id}</td>
                <%--                <td>${menu.idRestaurant}</td>--%>
            <td>${menu.nameRestaurant}</td>
            <td>${menu.dishes}</td>
            <td>${menu.price}</td>
        </tr>
    </c:forEach>
</table>    -

</body>
</html>

