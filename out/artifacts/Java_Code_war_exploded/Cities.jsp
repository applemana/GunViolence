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
    <title>Cities</title>
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
        <li><a href="/GunViolence/states">States</a></li>
        <li>${cities.get(0).getState().getStateName()}</li>
    </ul>
</div>
<h1 style="margin-top:50px; display:inline-block; width:50%">${messages.title}</h1>
<form class="Search" action="shootings">
    <select name="cityid" class="selectpicker" data-style="btn-primary">
        <option>Select a City in ${cities.get(0).getState().getStateName()}</option>
        <c:forEach items="${cities}" var="cities">
            <option value="${cities.getCityId()}">${cities.getCityName()}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Submit">
</form>
<br/>

<div id="createRating"><a href="ratingcreate">Create A Rating</a></div>
<br/>
<table class="table table-striped table-hover">
    <thead>

    <tr>
        <th class="text-center" scope="col">CityId</th>
        <th class="text-center" scope="col">City Name</th>
        <th class="text-center" scope="col">Ratings</th>
    </tr>
    </thead>
    <c:forEach items="${cities}" var="cities">
        <tr>
            <td align="center"><c:out value="${cities.getCityId()}"/></td>
            <td align="center"><a
                    href="shootings?cityid=<c:out value="${cities.getCityId()}"/>">${cities.getCityName()}</a>
            </td>
            <td align="center"><a class="btn btn-info btn-sm" style="min-width: 38px;"
                                  href="ratings?cityid=<c:out value="${cities.getCityId()}"/>">${ratings.get(cities.getCityId()).getRating().getGrade().prettyPrint()}</a>
            </td>
        </tr>
    </c:forEach>
</table>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>