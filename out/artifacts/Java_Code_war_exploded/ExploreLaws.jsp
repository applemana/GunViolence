<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <title>Explore Laws</title>
    <style>
        ul.breadcrumb {
            padding: 10px 16px;
            list-style: none;
            background-color: #eee;
        }

        ul.breadcrumb li {
            display: inline;
            font-size: 18px;
        }

        ul.breadcrumb li + li:before {
            padding: 8px;
            color: black;
            content: "/\00a0";
        }

        ul.breadcrumb li a {
            color: #0275d8;
            text-decoration: none;
        }

        ul.breadcrumb li a:hover {
            color: #01447e;
            text-decoration: underline;
        }
    </style>

</head>
<body>
<div class="w3-top">
    <ul class="breadcrumb">
        <li><a href="/GunViolence">Home</a></li>
        <li>Explore Laws</li>
    </ul>
</div>
<h2 style="margin-top: 50px">Select Type</h2>
<form action="explorelaws" method="post">
    <select name="type" class="selectpicker" data-style="btn-primary">
        <option value="RESTRICIVE">Restrictive</option>
        <option value="PERMISSIVE">Permissive</option>
        <option value="OTHER">Other</option>
    </select>
    <input type="submit" value="Submit">
</form>
<h1>${laws.get(0).getEffect().toString()} Laws</h1>
<table class="table table-striped table-hover">
    <tr>
        <th>Law Id</th>
        <th>Effects</th>
        <th>Contents</th>
        <th>Category</th>
        <th>Date</th>
        <th>State</th>

    </tr>
    <c:forEach items="${laws}" var="law">
        <tr>
            <td><c:out value="${law.getLawId()}"/></td>
            <td><c:out value="${law.getEffect()}"/></td>
            <td><c:out value="${law.getContents()}"/></td>
            <td style="width: 250px;"><c:out value="${law.getCategory()}"/></td>
            <td style="width: 100px;"><c:out value="${law.getYear()}"/></td>
            <td><a class="butn btn-info btn-sm"
                   href="laws?state=<c:out value="${law.getState().getStateAbbreviation()}"/>">${law.getState().getStateAbbreviation()}</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>