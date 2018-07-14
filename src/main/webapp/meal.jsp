<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="topjava.com/myfunctions" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meal</h2>
<form method="post" action="meals" name="frmAddUsr">
    Id : <input type="text" readonly="readonly" name="id" value="${meal.id}"><br/>
    DateTime : <input type="datetime-local" name="dateTime" value="${meal.dateTime}"><br/>
    Description : <input type="text" name="description" value="${meal.description}"><br/>
    Calories : <input type="text" name="calories" value="${meal.calories}"><br/>
    <input type="submit" value="OK">
</form>
</body>
</html>