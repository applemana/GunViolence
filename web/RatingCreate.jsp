<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <title>Create a Rating</title>
</head>
<body>
<h1>Rate the City</h1>
<form action="ratingcreate" method="post">
    <p>
        <label for="grade">Grade</label>
        <input id="grade" name="grade" value="">
    </p>
    <p>
        <label for="explanation">Explanation</label>
        <input id="explanation" name="explanation" value="">
    </p>
    <p>
        <label for="cityid">CityId</label>
        <input id="cityid" name="cityid" value="">
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/><br/>
<p>
    <span id="message"><b>${messages.success}</b></span>
</p>
</body>
</html>