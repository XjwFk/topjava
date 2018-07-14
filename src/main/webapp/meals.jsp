<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="topjava.com/myfunctions" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<a href="meals?action=create">Create meal</a><br/><br/>
<table border="1">
    <thead>
    <tr>
        <td>Id</td>
        <td>DateTime</td>
        <td>Description</td>
        <td>Calories</td>
        <td>Exceeded</td>
        <td colspan="2"></td>

    </tr>
    </thead>
    <c:forEach items="${meals}" var="meal">
        <tr bgcolor="${meal.exceed ? "red" : "lightgreen"}">
            <td>${meal.id}</td>
            <td>${f:formatLocalDateTime(meal.dateTime)}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${meal.exceed}</td>
            <td><a href="meals?action=update&id=<c:out value="${meal.id}"/>">update</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>