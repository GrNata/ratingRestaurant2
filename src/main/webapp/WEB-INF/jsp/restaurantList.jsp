<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<%--<body>--%>

<%--<div id="zatemnenie">--%>
<%--    <div id="okno">--%>
<%--        Всплывающее окошко!<br>--%>
<%--        <a href="/restaurantList" class="close">Закрыть окно</a>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<a  href="/restaurantList/#zatemnenie" >Okno</a>--%>
<%--</body>--%>


<section>
    <hr>
    <h2 align="center"><spring:message code="restaurant.title"/> </h2>
<%--    <br>--%>

<%--    <jsp:useBean id="restaurant" type="ru.grig.ratingRestaurant.with_restaurant.RestaurantWithRating" scope="request"/>--%>
<%--    <h3><spring:message code="${meal.isNew() ?  'restaurant.add' : 'restaurant.edit'}"/> </h3>--%>
<%--    <form method="post" action="/topjava/meals">--%>

    <form method="post" action="/restaurantList">
        <a href="/restaurantList/create"><spring:message code="restaurant.add"/></a>
        <br><br>


        <table align="center" border="1" cellpadding="8" cellspacing="0">
<%--            <table>--%>
            <thead>
            <tr>
                <th><spring:message code="restaurant.name"/> </th>
                <th><spring:message code="restaurant.menu"/> </th>
                <th><spring:message code="restaurant.rating"/> </th>
                <th></th>
                <th></th>
                <th><spring:message code="restaurant.getmenu"/> </th>
            </tr>
            </thead>
            <c:forEach items="${rest}" var="res">
                <jsp:useBean id="res" type="ru.grig.ratingRestaurant.with_restaurant.RestaurantWithRating" scope="page" />
                <tr align="left" style="font-style: italic">
                        <%--                <td><c:out value="${res.id}"/></td>--%>
                    <td><c:out value="${res.name}"/></td>
                    <td><c:out value="${res.menu}"/></td>
                    <td><c:out value="${res.rating}"/></td>
                        <%--            <td>${res.menu}</td>--%>
                        <%--                <td><a  href="restaurants?action=menu&id=${res.id}">Menu</a> </td>--%>


                            <td><a href="/restaurantList/update?id=${res.id}"><spring:message code="restaurant.update"/></a></td>
                        <%--<td>--%>
                        <%--    <input type="button" value="men" onclick="ff()"/>--%>

                        <%--</td>--%>



                        <%--         <td><input name="vote" type="checkbox" name="vote" value="${res.id}"  /> </td>--%>
                            <td><a href="/restaurantList/delete?id=${res.id}"><spring:message code="restaurant.delete"/></a></td>
<%--                            <td><a href="/restaurantList/menuList?id=${res.id}"><spring:message code="restaurant.getmenu"/> </a> </td>--%>
                            <td><a href="/restaurantList/menuList?id=${res.id}"><spring:message code="restaurant.getmenu"/> </a> </td>
                </tr>
            </c:forEach>

        </table>
    </form>

    <%--    <form method="post" action="/ratingRestaurant/restaurantForm">--%>
<%--        <input type="hidden" name="id" value="${restaurant.id}">--%>
<%--        <dl>--%>
<%--            <dt><spring:message code="restaurant.name"/>:</dt>--%>
<%--            <dd><input type="text" value="${restaurant.name}" name="dateTime" required></dd>--%>
<%--        </dl>--%>
<%--        <dl>--%>
<%--            <dt><spring:message code="restaurant.menu"/></dt>--%>
<%--            <dd><input type="text" value="${restaurant.menu}" size=40 name="description" required></dd>--%>
<%--        </dl>--%>
<%--        <dl>--%>
<%--            <dt><spring:message code="restaurant.rating"/></dt>--%>
<%--            <dd><input type="number" value="${restaurant.rating}" name="calories" required></dd>--%>
<%--        </dl>--%>
<%--        <button type="submit"><spring:message code="common.save"/></button>--%>
<%--        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>--%>
<%--    </form>--%>


</section>
</body>
</html>