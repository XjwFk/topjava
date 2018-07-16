<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meal</h2>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post" action="meals" name="frmAddUsr">
    Id : <input type="text" readonly="readonly" name="id" value="${meal.id}"><br/>
    DateTime : <input type="datetime-local" name="dateTime" value="${meal.dateTime}"><br/>
    Description : <input type="text" name="description" value="${meal.description}"><br/>
    Calories : <input type="number" name="calories" value="${meal.calories}"><br/>
    <button type="submit">OK</button>
    <button onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>