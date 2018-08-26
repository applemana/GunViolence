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

    <title>School Shootings</title>
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

        .Search {
            float: right;
            margin-top: 60px;
            margin-right: 10px;
        }
    </style>

</head>
<body>
<ul class="breadcrumb">
    <li><a href="/GunViolence">Home</a></li>
    <li><a href="states">States</a></li>
    <c:choose>
        <c:when test="${shootings.size() != 0}">
            <li>${shootings.get(0).getCity().getState().getStateName()}</li>
        </c:when>
    </c:choose>
</ul>

<h1>${messages.title}</h1>
<table class="table table-striped table-hover">
    <tr>
        <th class="text-center" scope="col">Type Of School</th>
        <th class="text-center" scope="col">Number Killed</th>
        <th class="text-center" scope="col">City</th>
        <th class="text-center" scope="col">State</th>
        <th class="text-center" scope="col">Characteristics</th>
    </tr>
    <c:forEach items="${shootings}" var="shootings">
        <tr>
            <td align="center"><c:out value="${shootings.getSchoolType()}"/></td>
            <td align="center"><c:out value="${shootings.getNumberKilled()}"/></td>
            <td align="center"><c:out value="${shootings.getCity().getCityName()}"/></td>
            <td align="center"><c:out
                    value="${shootings.getCity().getState().getStateAbbreviation()}"/></td>
            <td align="left"><c:out value="${shootings.getCharacteristics()}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>