<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<%--<head>--%>
<%--    <link type="text/css" href="<c:url value="/resources/css/main.css"/>"/>--%>
<%--&lt;%&ndash;    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    <link rel="stylesheet" href="resources/css/main.css">&ndash;%&gt;--%>
<%--</head>--%>

<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <okno>
    <jsp:useBean id="restaurant" type="ru.grig.ratingRestaurant.model.Restaurant" scope="request"/>
    <h3><spring:message code="${restaurant.isNew() ?  'restaurant.add' : 'restaurant.update'}"/> </h3>
    <hr>

    <form method="post" action="/restaurantList">
        <input type="hidden" name="id" value="${restaurant.id}">
        <dl>
            <dt><spring:message code="restaurant.name"/>:</dt>
            <dd><input type="text" value="${restaurant.name}" name="name" required></dd>
<%--            <dd><input type="datetime-local" value="${restaurant.}" name="dateTime" required></dd>--%>
        </dl>
        <dl>
            <dt><spring:message code="restaurant.menu"/></dt>
            <dd><input type="number" value="${restaurant.menu}" size=40 name="menu" required></dd>
        </dl>
        <dl>
<%--            <dt><spring:message code="restaurant.rating"/></dt>--%>
            <dd><input type="hidden" value="${restaurant.rating}" name="rating" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>


    </okno>
</section>
</body>
</html>
