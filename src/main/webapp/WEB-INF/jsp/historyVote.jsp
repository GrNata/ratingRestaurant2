<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
<%--    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css'/>"/>--%>
    <%--    <link rel="stylesheet" href="resources/css/main.css">--%>
</head>

<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
<hr>
    <h3><a href="/restaurants"><spring:message code="restaurant.back"/></a> </h3>
    <hr>

    <h2 align="center"><spring:message code="vote.history"/> </h2>

    <form method="get" action="/restaurants/historyVote">
<%--        <a href="/menuList/create"><spring:message code="menu.add"/></a>--%>
<%--        <br><br>--%>

        <table align="center" border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr align="center" style="font-style: italic">
<%--                <td><spring:message code="vote.id_user"/></td>--%>
                <td><spring:message code="vote.id_rest"/></td>
                <td><spring:message code="vote.vote_date_time"/></td>
<%--                <td></td>--%>
<%--                <td></td>--%>
            </tr>
            </thead>
            <c:forEach items="${votes}" var="vote">
                <jsp:useBean id="vote" type="ru.grig.ratingRestaurant.with_restaurant.VoteWithRestaurant" scope="page" />
                <tr align="left" style="font-style: italic">
<%--                    <td>${vote.idUser}</td>--%>
                    <td>${vote.restaurant}</td>
                    <td>${vote.date}</td>
<%--                    <td><a href="/menuList/update?id=${menu.id}"><spring:message code="menu.update"/> </a> </td>--%>
<%--                    <td><a href="/menuList/delete?id=${menu.id}"><spring:message code="menu.delete"/> </a> </td>--%>
                </tr>
            </c:forEach>
        </table>

    </form>
</section>
</body>
</html>