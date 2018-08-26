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

    <title>Shootings/Year Graph</title>
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
        <li><a href="/GunViolence">Home</a></li>
        <li><a href="/GunViolence/Graphs">Graphics</a></li>
        <li>Shootings/Year</li>
    </ul>
</div>
<h1>${messages.title}</h1>
<div class='tableauPlaceholder' id='viz1532826202855' style='position: relative'>
    <noscript>
        <a href='#'>
            <img alt='Shootings (&gt; 10) - State - Year '
                 src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;Gu&#47;GunData-Graph_0&#47;Shootings10-State-Year&#47;1_rss.png'
                 style='border: none'/>
        </a></noscript>
    <object class='tableauViz' style='display:none;'>
        <param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F'/>
        <param name='embed_code_version' value='3'/>
        <param name='site_root' value=''/>
        <param name='name' value='GunData-Graph_0&#47;Shootings10-State-Year'/>
        <param name='tabs' value='no'/>
        <param name='toolbar' value='yes'/>
        <param name='static_image'
               value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;Gu&#47;GunData-Graph_0&#47;Shootings10-State-Year&#47;1.png'/>
        <param name='animate_transition' value='yes'/>
        <param name='display_static_image' value='yes'/>
        <param name='display_spinner' value='yes'/>
        <param name='display_overlay' value='yes'/>
        <param name='display_count' value='yes'/>
    </object>
</div>
<script type='text/javascript'>
    var divElement = document.getElementById('viz1532826202855');
    var vizElement = divElement.getElementsByTagName('object')[0];
    vizElement.style.width = '100%'
    ;vizElement.style.height = (divElement.offsetWidth * 0.55) + 'px';
    var scriptElement = document.createElement('script');
    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';
    vizElement.parentNode.insertBefore(scriptElement, vizElement);
</script>
</body>
</html>