<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
<%--    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>--%>
<%--        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">--%>
<%--    <h1>"${pageContext.request.contextPath}/css/main.css"</h1>--%>
</head>

<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>

    <h3><a href="/restaurantList"><spring:message code="restaurant.back"/></a> </h3>
    <hr>

    <h2 align="center"><spring:message code="menu.title"/> </h2>

    <form method="post" action="/menuList">
        <a href="/menuList/create"><spring:message code="menu.add"/></a>
        <br><br>

    <table align="center" border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr align="center" style="font-style: italic">
            <td><spring:message code="menu.dishes"/></td>
            <td><spring:message code="menu.price"/></td>
            <td></td>
            <td></td>
        </tr>
        </thead>
        <c:forEach items="${menus}" var="menu">
            <jsp:useBean id="menu" type="ru.grig.ratingRestaurant.model.Menu" scope="page" />
            <tr align="left" style="font-style: italic">
                <td>${menu.dishes}</td>
                <td>${menu.price}</td>
                <td><a href="/menuList/update?id=${menu.id}"><spring:message code="menu.update"/> </a> </td>
                <td><a href="/menuList/delete?id=${menu.id}"><spring:message code="menu.delete"/> </a> </td>
            </tr>
        </c:forEach>
    </table>

    </form>
</section>
</body>
</html>