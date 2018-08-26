<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <title>Update a Rating</title>
</head>
<body>
<h1>Update Rating</h1>
<form action="ratingupdate" method="post">
    <p>
        <label for="ratingid">Rating ID</label>
        <input id="ratingid" name="ratingid" value="${fn:escapeXml(param.ratingid)}">
    </p>
    <p>
        <label for="grade">New Grade</label>
        <input id="grade" name="grade" value="">
    </p>
    <p>
        <label for="reason">New Reason For Grade</label>
        <input id="reason" name="reason" value="">
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/><br/>
<p>
    <span id="successMessage"><b>${messages.success}</b></span>
</p>
</body>
</html>