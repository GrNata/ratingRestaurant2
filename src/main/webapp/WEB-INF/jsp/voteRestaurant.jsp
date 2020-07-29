<%--
  Created by IntelliJ IDEA.
  User: Nata
  Date: 08.06.2020
  Time: 14:04
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
    <hr>
    <%--    <h3><a href="restaurants?action=list">Restaurants</a> </h3>--%>
    <%--    <h3><a href="/restaurants">Restaurants</a> </h3>--%>
    <h3><a href="restaurants"><spring:message code="restaurant.back"/></a> </h3>
    <hr>

    <h2 align="center"><spring:message code="vote.title"/></h2>
    <br>

    <jsp:useBean id="rest" type="ru.grig.ratingRestaurant.model.Restaurant" scope="request"/>
    <h3 align="center"><spring:message code="vote.voted"/> ${rest.name}</h3>

    <form method="post">
        <h1 align="center"><spring:message code="menu.title"/></h1>
        <table align="center" border="1" cellpadding="8" cellspacing="0">
            <c:forEach items="${menus}" var="menu">
                <jsp:useBean id="menu" type="ru.grig.ratingRestaurant.model.Menu" scope="page" />
                <tr align="left" style="font-style: italic">
<%--                    <td>${menu.id}</td>--%>
                    <td>${menu.dishes}</td>
                    <td>${menu.price}</td>
                </tr>
            </c:forEach>
        </table>
    </form>

</section>
</body>
</html>
