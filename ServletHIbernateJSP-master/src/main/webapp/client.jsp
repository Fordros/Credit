<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='style.css' %>
</style>
<html>
<head>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
    <title>Test servlet</title>
</head>
<body>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>

<div class="body-style" >
<h1 class="text-center">Форма ввода нового клиента</h1>
<form  method="post" action="/addClient" >
    <div class="form-group">
        <input class="form-control" type="text" name="firstName" id="firstName" placeholder="Ваше имя">
    </div>
    <div class="form-group">
        <input class="form-control" type="text" name="lastName" id="lastName" value="" placeholder="Ваша фамилия">
    </div>
        <div class="form-group">
            <input class="form-control" type="tel" name="phone" id="phone" value="" placeholder="Ваш номер телефона">
        </div>
    <div class="form-group">
        <input class="form-control" type="text" name="country" id="country" placeholder="Страна проживания">
    </div>
    <div class="form-group">
        <input class="form-control" type="text" name="city" id="city" value="" placeholder="Город проживания">
    </div>
    <div class="form-group">
        <input class="form-control" type="text" name="street" id="street" value="" placeholder="Улица проживания">
    </div>
    <div class="form-group">
        <input class="form-control" type="text" name="building" id="building" placeholder="Дом">
    </div>
    <div class="form-group">
        <input class="form-control" type="text" name="apartment" id="apartment" value="" placeholder="Квартира">
    </div>
    <div class="form-group">
        <input class="form-control" class="btn btn-default" type="submit" name="addClient" value="Добавить" >
    </div>

</form>
</div>
    <p></p>
    <div class="table-style">
    <table class="table table-hover">
        <caption class="text-center">Список клиентов в БД</caption>
    <tr><th>Имя</th><th>Фамилия</th><th>Телефон</th><th>Страна</th><th>Город</th><th>Улица</th><th>Дом</th><th>Квартира</th></tr>
    <c:forEach var="cl" items="${client}">
        <tr><th>${cl.firstName}</th><th>${cl.lastName}</th><th>${cl.phone}</th><th>${cl.addr.country}</th><th>${cl.addr.city}</th><th>${cl.addr.street}</th><th>${cl.addr.building}</th><th>${cl.addr.apartment}</th></tr>
    </c:forEach>
</table>
        </div>



</body>
</html>
