<%--
  Created by IntelliJ IDEA.
  User: Nata
  Date: 07.06.2020
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<%--<head>--%>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><a href="/restaurants"><spring:message code="restaurant.back"/></a> </h3>
    <hr>

    <h2 align="center"><spring:message code="menu.title"/> </h2>


    <table align="center" border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr align="center" style="font-style: italic">
            <td><spring:message code="menu.dishes"/></td>
            <td><spring:message code="menu.price"/></td>
        </tr>
        </thead>
        <c:forEach items="${menus}" var="menu">
            <jsp:useBean id="menu" type="ru.grig.ratingRestaurant.model.Menu" scope="page" />
            <tr align="left" style="font-style: italic">
<%--                <td>${menu.id}</td>--%>
<%--                <td>${menu.idRestaurant}</td>--%>
<%--                <td>${menu.nameRestaurant}</td>--%>
                <td>${menu.dishes}</td>
                <td>${menu.price}</td>
            </tr>
        </c:forEach>
    </table>
    -
</section>
</body>
</html>