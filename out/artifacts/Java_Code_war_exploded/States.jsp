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

    <title>States</title>
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
<div class="w3-top">
    <ul class="breadcrumb">
        <li><a href="/GunViolence">Home</a></li>
        <li>States</li>
    </ul>
</div>
<h1 style="margin-top:50px; display:inline-block; width:50%">${messages.title}</h1>

<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th class="text-center" scope="col"> State Abbreviation</th>
        <th class="text-center" scope="col">State Name</th>
        <th class="text-center" scope="col">Laws</th>
        <th class="text-center" scope="col">School Shootings</th>
        <th class="text-center" scope="col">10 Most Recent Shootings</th>
    </tr>
    </thead>
    <c:forEach items="${states}" var="states">
        <tr>
            <td align="center"><c:out value="${states.getStateAbbreviation()}"/></td>
            <td align="center"><a
                    href="cities?state=<c:out value="${states.getStateAbbreviation()}"/>">${states.getStateName()}</a>
            </td>
            <td align="center"><a class="btn btn-info btn-sm"
                                  href="laws?state=<c:out value="${states.getStateAbbreviation()}"/>">Laws</a>
            </td>
            <td align="center"><a class="btn btn-info btn-sm"
                                  href="schoolshootings?state=<c:out value="${states.getStateAbbreviation()}"/>">School
                Shootings</a></td>
            <td align="center"><a class="btn btn-info btn-sm"
                                  href="shootings?state=<c:out value="${states.getStateAbbreviation()}"/>">Shootings</a>
            </td>
        </tr>
    </c:forEach>
</table>