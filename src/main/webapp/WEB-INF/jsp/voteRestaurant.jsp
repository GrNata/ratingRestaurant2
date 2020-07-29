<%--
  Created by IntelliJ IDEA.
  User: Nata
  Date: 08.06.2020
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Vote</title>
    <br>
    <h3><a href="../../index.html">Main Page</a> </h3>
    <hr>
    <h3><a href="restaurants?action=list">Restaurants</a> </h3>
    <hr>
    <h2>Вы проголосовали за ресторан</h2>
</head>
<body>
    <jsp:useBean id="rest" type="ru.grig.ratingRestaurant.model.Restaurant" scope="request"/>
    <form method="post">
        <label>${rest.name}</label>
        </br>
    </form>
    <form method="post">
        <h1>MENU:</h1>
        <table>
            <c:forEach items="${menus}" var="menu">
                <jsp:useBean id="menu" type="ru.grig.ratingRestaurant.model.Menu" scope="page" />
                <tr align="left" style="font-style: italic">
                    <td>${menu.id}</td>
                    <td>${menu.dishes}</td>
                    <td>${menu.price}</td>
                </tr>
            </c:forEach>
        </table>
    </form>

</body>
</html>
