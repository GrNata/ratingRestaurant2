<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="ru">--%>

<%--<html>--%>
<head>
    <title>Модальное окно</title>
<%--    <link rel="stylesheet" type="text/css" href="main.css">--%>
    <style>
        <%@include file="/resources/css/modal.css"%>
        <%@include file="/resources/css/main.css"%>
        <%@include file="/resources/css/style.css"%>
    </style>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">--%>
</head>

<body>

<section>
    <hr>
    <h2 align="center"><spring:message code="restaurant.title"/> </h2>

        <table align="center" border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th><spring:message code="restaurant.name"/> </th>
                <th><spring:message code="restaurant.menu"/> </th>
                                <th></th>
            </tr>
            </thead>
            <c:forEach items="${rest}" var="res">
                <jsp:useBean id="res" type="ru.grig.ratingRestaurant.model.Restaurant" scope="page" />
                <tr align="left" style="font-style: italic">
                        <%--                <td><c:out value="${res.id}"/></td>--%>
                    <td><c:out value="${res.name}"/></td>
                    <td><c:out value="${res.menu}"/></td>
                    <td>
                        <div>
<%--                        <a href="/modal#myModal" name="rest" value="${res.id}" id="myBtn-1" class="myBtn"><spring:message code="menu.title"/></a>--%>
<%--                        <button id="myBtn-1" class="myBtn" value="${res.id}" name="rest" onclick="getRest(${res.id})">Open Modal-1</button>--%>
    <a href="#dialog" name="modal">Вызов модального окна</a>

<%--                        <button data-target="#myModel" data-toggle="modal">Open Modal-1</button>--%>
<%--                        <button data-toggle="modal" id="myBtn-1" class="myBtn" value="${res.id}" name="rest">Open Modal-1</button>--%>
<%--    <a href="/modal/#myModal" id="myBtn-1" class="myBtn" value="${res.id}" name="rest">Open Modal-1</a>>--%>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
</section>



<script type="text/javascript">
    /* Данная функция создаёт кроссбраузерный объект XMLHTTP */
    function getXmlHttp() {
        var xmlhttp;
        try {
            xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (E) {
                xmlhttp = false;
            }
        }
        if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
            xmlhttp = new XMLHttpRequest();
        }
        return xmlhttp;
    }
    function summa() {
        var a = document.getElementById("a").value; // Считываем значение a
        var b = document.getElementById("b").value; // Считываем значение b
        var xmlhttp = getXmlHttp(); // Создаём объект XMLHTTP
        xmlhttp.open('POST', 'modal', true); // Открываем асинхронное соединение
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
        xmlhttp.send("a=" + encodeURIComponent(a) + "&b=" + encodeURIComponent(b)); // Отправляем POST-запрос
        xmlhttp.onreadystatechange = function() { // Ждём ответа от сервера
            if (xmlhttp.readyState == 4) { // Ответ пришёл
                alert( xmlhttp.status + ': ' + xmlhttp.readyState );
                // console.log("status = 4");
                if(xmlhttp.status == 200) { // Сервер вернул код 200 (что хорошо)
                    // console.log("200");
                    alert( xmlhttp.status + ': ' + xmlhttp.status);
                    document.getElementById("suma").innerHTML = xmlhttp.responseText; // Выводим ответ сервера
                    console.log("suma");
                } else {
                    alert( xmlhttp.status + ': ' + xmlhttp.statusText );
                }
            }
            console.log("After IF");
        };
    }
</script>
<div>
    <input type="text" name="a" id="a" />
    <br />
    <input type="text" name="b" id="b" />
    <br />
    <input type="button" value="Сумма" onclick="summa()" />
<%--    <jsp:useBean id="suma" scope="page" />--%>

    <p>Сумма равна: <span id="suma1"></span></p>
<%--    <p>Сумма равна 2: <span id="suma">${suma}</span></p>--%>
</div>


<%--        <button id="myBtn-1" class="myBtn">Open Modal-1</button>--%>

<%--<button id="myBtn-2" class="myBtn">Open Modal-2</button>--%>
<%--<button id="myBtn-3" class="myBtn">Open Modal-3</button>--%>
<%--</body>--%>
<%--<h2>Bottom Modal</h2>--%>
<%--<br>--%>

<!-- The Modal -->

<%--<body>--%>
<%--<script type="text/javascript">--%>
<%--    <%@include file="/WEB-INF/js/main.js"%>--%>
<%--</script>--%>


<%--<!-- Само окно -->--%>
<%--<div id="boxes">--%>
<%--    <div id="dialog" class="window"> Текст модального окна--%>
<%--        <div class="top">--%>
<%--            <a href="#" class="link close"/>Закрыть</a>--%>
<%--        </div>--%>
<%--        <div class="content">Текст в модальном окне.</div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<!-- Маска, затемняющая фон -->--%>
<%--<div id="mask"></div>--%>



</body>

<%--<div id="myModal" class="modal">--%>
<%--    <!-- Modal content -->--%>
<%--    <div class="modal-content">--%>
<%--        <div class="modal-header">--%>
<%--            <span class="close">&times;</span>--%>
<%--            <h2>Modal Header</h2>--%>
<%--            <h3 value="${res.id}">${i}</h3>--%>
<%--        </div>--%>
<%--        <div class="modal-body"><iframe name="myiframe"></iframe>--%>
<%--&lt;%&ndash;            <p>Some text in the Modal Body</p>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <p>Some other text...</p>&ndash;%&gt;--%>

<%--            <table align="center" border="1" cellpadding="8" cellspacing="0">--%>
<%--                <thead>--%>
<%--                <tr align="center" style="font-style: italic">--%>
<%--                    <td><spring:message code="menu.dishes"/></td>--%>
<%--                    <td><spring:message code="menu.price"/></td>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <c:forEach items="${menus}" var="menu">--%>
<%--                    <jsp:useBean id="menu" type="ru.grig.ratingRestaurant.model.Menu" scope="page" />--%>
<%--                    <tr align="left" style="font-style: italic">--%>
<%--                        <td>${menu.dishes}</td>--%>
<%--                        <td>${menu.price}</td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--            </table>--%>


<%--        </div>--%>
<%--        <div class="modal-footer">--%>
<%--            <h3>Modal Footer</h3>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<%--<script>--%>
<%--    <%@include file="/WEB-INF/js/modal.js"%>--%>
<%--</script>--%>


<body>
<%--    <main>--%>
<%--        <button id="myBtn" class="button js-button-campaign" onclick="openWin()">Open Modal-1</button>--%>
<%--&lt;%&ndash;                <div class="button js-button-campaign" type="button">&ndash;%&gt;--%>
<%--&lt;%&ndash;            <span>Акция</span>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </div>&ndash;%&gt;--%>
<%--    </main>--%>
<%--    <div id="myModal"  class="overlay js-overlay-campaign">--%>
<%--        <div class="popup js-popup-campaign">--%>
<%--            <h2>Внимание акция!!!</h2>--%>
<%--            <div class="close-popup js-close-campaign">--%>
<%--               <span class="close">&times;</span>--%>
<%--&lt;%&ndash;               <h2>Modal Header</h2>&ndash;%&gt;--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <script type="text/javascript">--%>
<%--        <%@include file="/WEB-INF/js/main.js"%>--%>
<%--    </script>--%>

</body>

</html>
