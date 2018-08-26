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

    <title>Graphics</title>
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

        .row {
            margin-right: 0;
            margin-left: 0;
            display: inline-block;
            max-width: 100%;
            height: auto;
        }

        .media {
            margin-top: 0;
            padding-left: 1px;
            padding-right: 1px;
            float: left;
            display: inline-block;
            position: relative;
            vertical-align: top;
        }

        .media__image {
            display: inline-block;
            max-width: 92%;
            height: auto;
            background-color: white;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            margin: 5px 5px;
        }

        .media__body {
            background: rgba(2, 117, 216, 0.7);
            bottom: 0;
            color: white;
            font-size: 1em;
            left: 0;
            opacity: 0;
            overflow: hidden;
            padding: 3.75em 3em;
            position: absolute;
            text-align: center;
            top: 0;
            right: 0;
            -webkit-transition: 0.6s;
            transition: 0.6s;
            width: 92%;
            margin: 5px 5px;
        }

        .media__body:hover {
            opacity: 1;
        }

        .media__body:after,
        .media__body:before {
            border: 1px solid rgba(255, 255, 255, 0.7);
            bottom: 1em;
            content: '';
            left: 1em;
            opacity: 0;
            position: absolute;
            right: 1em;
            top: 1em;
            -webkit-transform: scale(1.5);
            -ms-transform: scale(1.5);
            transform: scale(1.5);
            -webkit-transition: 0.6s 0.2s;
            transition: 0.6s 0.2s;
        }

        .media__body:before {
            border-bottom: none;
            border-top: none;
            left: 2em;
            right: 2em;
        }

        .media__body:after {
            border-left: none;
            border-right: none;
            bottom: 2em;
            top: 2em;
        }

        .media__body:hover:after,
        .media__body:hover:before {
            -webkit-transform: scale(1);
            -ms-transform: scale(1);
            transform: scale(1);
            opacity: 1;
        }

        .media__body h2 {
            margin-top: 0;
        }

        .media__body p {
            margin-bottom: 1.5em;
        }
    </style>
</head>
<body>
<ul class="breadcrumb">
    <li><a href="/GunViolence">Home</a></li>
    <li>Graphics</li>
</ul>
<h1>Graphics</h1>
<br/>
<div class="row">
    <div class="media"></div>
    <div class="media" style="max-width:49.9999%;">
        <a href="BarGraph"><img alt="" class="media__image" src="Bar Graph - ShootingsPerYear.png"/>
            <div class="media__body">
                <h2>Shootings/Year</h2>
            </div>
        </a>
    </div>

    <div class="media"></div>
    <div class="media" style="max-width:49.9999%;">
        <a href="ShootingsMaps"><img alt="" class="media__image" src="Shootings Maps.png"/>
            <div class="media__body">
                <h2>Maps of Shootings</h2>
            </div>
        </a>
    </div>
</div>
</body>
</html>