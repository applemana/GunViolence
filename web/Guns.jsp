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

    <title>Guns</title>
</head>
<body>
<h1>${messages.title}</h1>
<table class="table table-striped table-hover">
    <tr>
        <th class="text-center" scope="col">GunId</th>
        <th class="text-center" scope="col">Stolen</th>
        <th class="text-center" scope="col">Gun Type</th>
        <th class="text-center" scope="col">Gun Number</th>
    </tr>
    <c:forEach items="${guns}" var="guns">
        <tr>
            <td align="center"><c:out value="${guns.getGunId()}"/></td>
            <td align="center"><c:out value="${guns.getStolen()}"/></td>
            <td align="center"><c:out value="${guns.getGunType()}"/></td>
            <td align="center"><c:out value="${guns.getNumberOfGuns()}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>