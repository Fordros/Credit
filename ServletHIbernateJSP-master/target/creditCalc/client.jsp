<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='style.css' %>
</style>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <title>Test servlet</title>
</head>
<body background="BlurredBackground%239.jpg">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>

<div >
<h1 class="text-center">Форма поиска клиента</h1>
<form  class="body-style" method="post" action="/addClient" >
    <div class="form-group">
        <input class="form-control" type="text" name="accNumber" id="accNumber" placeholder="Номер карточного счета">
    </div>
    <div class="form-group">
        <input class="form-control" class="btn btn-default" type="submit" name="addClient" value="Найти" >
    </div>

</form>
</div>
    <p></p>
    <div class="table-style">
    <table class="table table-hover">
        <caption class="text-center">Список клиентов в БД</caption>
    <tr>
        <th>№</th>
        <th>Дата</th>
        <th>Задолженность</th>
        <th>Платеж</th>
        <th>Процент по телу КЛ</th>
        <th>Процент по НО</th>
        <th>Сумма процентов по телу</th>
        <th>Сумма процентов по НО</th>
        <th>Обшая задолженность на дату</th>
    </tr>
    <c:forEach var="cd" items="${calcDebts}">
        <tr>
            <th>${cd.id}</th>
            <th>${cd.date}</th>
            <th>${cd.debts}</th>
            <th>${cd.pay}</th>
            <th>${cd.percentPrincipalDebt}</th>
            <th>${cd.percentPastDueDebts}</th>
            <th>${cd.sumPercentPrincipalDebt}</th>
            <th>${cd.sumPercentPastDueDebts}</th>
            <th>${cd.fullDebts}</th>
        </tr>
    </c:forEach>
</table>
        </div>



</body>
</html>
