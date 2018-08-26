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

    <title>Ratings</title>
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
<ul class="breadcrumb">
    <li><a href="/GunViolence">Home</a></li>
    <li><a href="states">States</a></li>
    <li>
        <a href="cities?state=<c:out value="${rating.getCity().getState().getStateAbbreviation()}"/>">${rating.getCity().getState().getStateName()}</a>
    </li>
    <li>${rating.getCity().getCityName()}, ${rating.getCity().getState().getStateAbbreviation()}</li>
</ul>
<h1>Rating
    for ${rating.getCity().getCityName()}, ${rating.getCity().getState().getStateAbbreviation()}</h1>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th class="text-center" scope="col">RatingId</th>
        <th class="text-center" scope="col">Grade</th>
        <th class="text-center" scope="col">Reason For Grade</th>
        <th class="text-center" scope="col">Delete</th>
        <th class="text-center" scope="col">Update</th>
    </tr>
    </thead>
    <tr>
        <td align="center"><c:out value="${rating.getRatingId()}"/></td>
        <td align="center"><c:out value="${rating.getGrade().prettyPrint()}"/></td>
        <td align="center"><c:out value="${rating.getReasonForGrade()}"/></td>
        <td align="center"><a href="ratingdelete?ratingid=<c:out value="${rating.getRatingId()}"/>">Delete</a>
        </td>
        <td align="center"><a href="ratingupdate?ratingid=<c:out value="${rating.getRatingId()}"/>">Update</a>
        </td>
    </tr>
</table>
</body>
</html>