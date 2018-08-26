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

    <title>Laws</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
        <li><a href="/GunViolence/states">States</a></li>
        <li>${laws.get(0).getState().getStateName()}</li>
    </ul>
</div>
<h1 style="margin-top: 50px">${messages.title}</h1>
<table class="table table-striped table-hover">
    <tr>
        <th class="text-center" scope="col">LawId</th>
        <th class="text-center" scope="col">Effect</th>
        <th class="text-center" scope="col">Contents</th>
        <th class="text-center" scope="col">Category</th>
        <th class="text-center" scope="col">Date</th>
    </tr>
    <c:forEach items="${laws}" var="laws">
        <tr>
            <td align="left"><c:out value="${laws.getLawId()}"/></td>
            <td align="left"><c:out value="${laws.getEffect().toString()}"/></td>
            <td align="left"><c:out value="${laws.getContents()}"/></td>
            <td align="left"><c:out value="${laws.getCategory()}"/></td>
            <td align="left" style="width: 90px;"><c:out value="${laws.getYear()}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>