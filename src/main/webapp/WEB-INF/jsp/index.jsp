<%--
  Created by IntelliJ IDEA.
  User: Nata
  Date: 13.07.2020
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
<%--    Для bootstrap--%>
    <!-- CSS only -->
<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">--%>

    <!-- JS, Popper.js, and jQuery -->
<%--    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>--%>
<%--    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>--%>
</head>


<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>

<div>
<section>
    <form method="post" action="users">
        <spring:message code="app.login"/>
            <select name="userId">
                <option value="100000">Nata</option>
                <option value="100001">Dima</option>
                <option value="100002">Seva</option>
            </select>
        <button type="submit"><spring:message code="common.select"/> </button>

    </form>
    <hr>

    <br>
    <a href="/modal">MODAL</a>

</section>
</div>

<br>

<div class="container-fluid">
    <h1> Три различных блока</h1>
    <div class="row">
        <div class="col-md-4" style="background-color: crimson">Left</div>
        <div class="col-md-4" style="background-color: coral">Center</div>
        <div class="col-md-4" style="background-color: darkgreen">Rigth</div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-9 col-md-4 col-lg-6" style="background-color: darkmagenta">Первый</div>
    <div class="col-xs-12 col-sm-3 col-md-8 col-lg-6" style="background-color: darksalmon">Второй</div>
</div>

<button class="btn btn-info" data-toggle="modal" data-target="#myModal">ОКНО</button>

</body>



<body>
<div id="myModal" class="modal fade" tabindex="-1">
<%--    <div class="modal-dialog modal-lg">--%>
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">X</button>
                <h4 class="modal-title">МОДАЛЬНОЕ ОКНО</h4>
            </div>
            <div class="modal-body"><p>МОЙ ТЕКСТ</p></div>
            <div class="modal-footer">
                <button class="btn btn-danger" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>--%>
<!-- Latest compiled and minified JavaScript -->
<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>--%>
<script>
    $('#myModal').modal("toggle");
    $('#myModal').on('show.bs.modal', function (event) {
        alert("ПОКАЗ")
    });
    $('#myModal').on('hidden.bs.modal', function (event) {
        alert("Выполнено")
    });
</script>

</body>


</html>
