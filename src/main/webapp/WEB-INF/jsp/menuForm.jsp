<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<okno>
<section>
    <jsp:useBean id="menu" type="ru.grig.ratingRestaurant.model.Menu" scope="request"/>
    <h3 align="center"><spring:message code="${menu.isNew() ?  'menu.add' : 'menu.update'}"/> </h3>
    <hr>

<%--    <form method="post" action="/menuList">--%>

    <form method="post" action="/menuList">
        <input type="hidden" name="id" value="${menu.id}">
        <dl>
            <dt><spring:message code="menu.dishes"/>:</dt>
            <dd><input type="text" value="${menu.dishes}" name="dishes" required></dd>
            <%--            <dd><input type="datetime-local" value="${restaurant.}" name="dateTime" required></dd>--%>
        </dl>
        <dl>
            <dt><spring:message code="menu.price"/></dt>
            <dd><input type="number" value="${menu.price}" size=40 name="price" required></dd>
        </dl>
        <dl>
<%--            <dt>RESTAURANT</dt>--%>
            <dd><input type="hidden" value="${menu.restaurant.id}" size=40 name="restId" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>



</section>
</okno>
</body>
</html>

