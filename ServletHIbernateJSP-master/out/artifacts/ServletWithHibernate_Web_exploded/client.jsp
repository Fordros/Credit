<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test servlet</title>
</head>
<body>
<h1>Hello </h1>
<c:forEach var="cl" items="${client}">
    <p>${cl.firstName} </p>
    <p>${cl.lastName} </p>
    <p>---------------</p>
</c:forEach>
</body>
</html>
