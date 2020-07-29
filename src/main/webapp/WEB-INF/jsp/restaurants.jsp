<%--
  Created by IntelliJ IDEA.
  User: Nata
  Date: 07.06.2020
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>--%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>


<section>
    <hr>
    <h3><a href="/restaurants/historyVote"><spring:message code="vote.history"/></a> </h3>
    <hr>

    <h2 align="center"><spring:message code="restaurant.title"/> </h2>

    <form method="post" action="/restaurants">

    <table align="center" border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th><spring:message code="restaurant.name"/> </th>
                <th><spring:message code="restaurant.menu"/> </th>
                <th><spring:message code="restaurant.rating"/> </th>
                <th><spring:message code="restaurant.getmenu"/> </th>
                <th><spring:message code="restaurant.vote"/> </th>
<%--                <th></th>--%>
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
                <td><a  href="restaurants?action=menu&id=${res.id}" name="restId">Menu</a> </td>


<%--                <td><a  href="/restaurants/menus?id=${res.id}" ><spring:message code="menu.title"/></a> </td>--%>
<%--                <td>--%>

<%--                <a href="/restaurants/#zatemnenie" value="${res.id}" name="rest"><spring:message code="menu.title"/></a>--%>

<%--                </td>--%>


<%--                <td><input type="radio" name="vote" value="${res.id}"  id="${res.name}" /> </td>--%>
                <td><input type="radio" name="vote" value="${res.id}" id="${res.name}" onclick="ff(id, value)" /> </td>

            </tr>
        </c:forEach>

    </table>
        <br>

<%--    <button type="submit" name="butOK" value="1"><spring:message code="common.vote"/></button>--%>
    <button type="submit" name="butOK" value="1" onclick="messageVote()"><spring:message code="common.vote"/></button>
<%--    <button type="submit" value="1">OK</button>--%>
    <button onclick="window.history.back()" name="butCancel" value="0"><spring:message code="common.cancel"/><% String jsarrayAsJavaString = null; %></button>
<%--    <button onclick="window.history.back()" value="0">Cancel</button>--%>


        <script type="text/javascript">
            var nameRest
            var idRest
            function messageVote() {
                alert("Вы проголосовали за ресторан "+nameRest )
            }

            function ff(id, value) {
                alert("function ff:"+id+" - "+value)
                nameRest = id
                idRest = value
            }

            function mod(t) {
                var rest = $(t).attr('value');
                alert("id rest - "+rest+" - "+t)
                $('#restId').val(rest)
                return rest;
            }
         </script>


    </form>
</section>
</body>

<%--<form method="post" action="/restaurants">--%>
<%--<div id="zatemnenie" class="z_class">--%>
<div id="zatemnenie" class="z_class">
    <div id="okno" class="o_class">
        <spring:message code="menu.title"/>
<%--        <h3 id="restId" value=mod()>res = </h3>--%>
        <br>
        <% String code = request.getParameter("rest"); %>
        <% System.out.println("REST WIND = "+code);  %>
        <table>
            <thead>
            <tr>
                <th><spring:message code="menu.dishes"/> </th>
                <th><spring:message code="menu.price"/> </th>
            </tr>
            </thead>
            <c:forEach items="${menus}" var="menu">
                <jsp:useBean id="menu" type="ru.grig.ratingRestaurant.model.Menu" scope="page" />
                <tr>
                    <td>${menu.dishes}</td>
                    <td>${menu.price}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="/restaurants" class="close">Закрыть окно</a>
    </div>
</div>
<%--</form>--%>

</html>


<%--<head>--%>
<%--    <title>Restaurants</title>--%>
<%--    <br>--%>
<%--    <h3><a href="1_index.html">Main Page</a> </h3>--%>
<%--    <hr>--%>
<%--    <h2>Restaurans</h2>--%>
<%--</head>--%>

<%--<body>--%>
<%--&lt;%&ndash;    <form method="post" action="restaurants">&ndash;%&gt;--%>
<%--    <form method="post">--%>
<%--    <table align="center">--%>
<%--        <thead>--%>
<%--        <tr align="center" style="font-style: italic">--%>
<%--            <td>Id</td>--%>
<%--            <td>Restaurant</td>--%>
<%--            <td>Rating</td>--%>
<%--            <td>Menu</td>--%>
<%--            <td>Vote</td>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--    <c:forEach items="${rest}" var="res">--%>
<%--        <jsp:useBean id="res" type="ru.grig.ratingRestaurant.with_restaurant.RestaurantWithRating" scope="page" />--%>
<%--        <tr align="left" style="font-style: italic">--%>
<%--        <td>${res.id}</td>--%>
<%--        <td>${res.name}</td>--%>
<%--        <td>${res.rating}</td>--%>
<%--&lt;%&ndash;            <td>${res.menu}</td>&ndash;%&gt;--%>
<%--            <td><a  href="restaurants?action=menu&id=${res.id}">Menu</a> </td>--%>
<%--&lt;%&ndash;         <td><input name="vote" type="checkbox" name="vote" value="${res.id}"  /> </td>&ndash;%&gt;--%>
<%--         <td><input name="vote" type="radio" name="vote" value="${res.id}"  /> </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--    </table>--%>

<%--        <button type="submit" name="butOK" value="1">OK</button>--%>
<%--        <button onclick="window.history.back()" name="butCancel" value="0">Cancel</button>--%>
<%--    </form>--%>
<%--</body>--%>
<%--</html>--%>
