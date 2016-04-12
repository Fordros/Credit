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
<h1 class="text-center">Форма поиска клиента</h1>
<form  method="post" action="/addClient" >
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
    <tr><th>Номер КС</th><th>ИНН</th><th>Кредитный лимит</th><th>Дата закрытия КЛ</th></tr>
    <c:forEach var="cl" items="${account}">
        <tr><th>${cl.accountNumber}</th><th>${cl.ssn}</th><th>${cl.creditLimit}</th><th>${cl.limitTerminationDate}</th></tr>
    </c:forEach>
</table>
        </div>



</body>
</html>
