<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test servlet</title>
</head>
<body>
<h1>Форма добавления нового клиента</h1>
<form action="account.jsp" method="post">
    <input type="text" maxlength="25" placeholder="Ваше имя" required><br>
    <input type="text" maxlength="25" placeholder="Ваша Фамилия" required><br>
    <input type="tel" maxlength="12" placeholder="Ваш номер телефона" required><br>
    <input type="button">
</form>
</body>
</html>
