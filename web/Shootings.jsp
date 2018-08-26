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

    <title>Shootings</title>
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

        table {
            width: 100%;
        }
    </style>
</head>
<body>
<div class="w3-top">
    <ul class="breadcrumb">
        <li><a href="/GunViolence">Home</a></li>
        <li><a href="states">States</a></li>
        <li>
            <a href="cities?state=<c:out value="${shootings.get(0).getCity().getState().getStateAbbreviation()}"/>">${shootings.get(0).getCity().getState().getStateName()}</a>
        </li>
        <li>${shootings.get(0).getCity().getCityName()}, ${shootings.get(0).getCity().getState().getStateAbbreviation()}</li>
    </ul>
</div>
<h1 style="margin-top: 50px">Shootings
    in ${shootings.get(0).getCity().getCityName()}, ${shootings.get(0).getCity().getState().getStateAbbreviation()}</h1>
<table class="table table-striped table-hover">
    <tr style="position: sticky">
        <th class="text-center" scope="col" style="width: 93px">Shooting Id</th>
        <th class="text-center" scope="col" style="width: 90px">City Name</th>
        <th class="text-center" scope="col" style="width: 90px">Shooting Date</th>
        <th class="text-center" scope="col">Number Of Guns</th>
        <th class="text-center" scope="col">Characteristics</th>
        <th class="text-center" scope="col">Participants</th>
        <th class="text-center" scope="col">Guns</th>
    </tr>
    <c:forEach items="${shootings}" var="shootings">
        <tr>
            <td align="center"><c:out value="${shootings.getShootingId()}"/></td>
            <td align="center"><c:out value="${shootings.getCity().getCityName()}"/></td>
            <td align="center"><c:out value="${shootings.getShootingDate()}"/></td>
            <td align="center"><c:out value="${shootings.getNumberOfGuns()}"/></td>
            <td align="left"><c:out value="${shootings.getCharacteristics()}"/></td>
            <c:choose>
                <c:when test="${gunsAndParticipants.get(shootings)[1] != 0}">
                    <td align="center"><a
                            href="participants?shooting=<c:out value="${shootings.getShootingId()}"/>">Participants</a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td></td>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${gunsAndParticipants.get(shootings)[0] != 0}">
                    <td align="center"><a
                            href="guns?shooting=<c:out value="${shootings.getShootingId()}"/>">Guns</a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>