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

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <title>Gun Violence</title>
    <style>
        .rssFeed {
            text-align: center;
        }

        h1 {
            font-size: 23px;
            color: rgb(51, 51, 51);
            font-family: sans-serif;
            padding-left: 10px;
            padding-right: 10px;
            margin-top: 11px;
        }

        .dropdown:hover > .dropdown-menu {
            display: block;
        }

        .dropdown-menu {
            min-width: 200px;
        }

        .dropdown-menu.columns-2 {
            min-width: 400px;
        }

        .dropdown-menu.columns-3 {
            min-width: 600px;
        }

        .dropdown-menu li a {
            padding: 5px 15px;
            font-weight: 300;
        }

        .multi-column-dropdown {
            list-style: none;
            margin: 0px;
            padding: 0px;
        }

        .multi-column-dropdown li a {
            display: block;
            clear: both;
            line-height: 1.428571429;
            color: #333;
            white-space: normal;
        }

        .multi-column-dropdown li a:hover {
            text-decoration: none;
            color: #fff;
            background-color: #999;
        }

        @media (max-width: 767px) {
            .dropdown-menu.multi-column {
                min-width: 240px !important;
                overflow-x: hidden;
            }
        }

        #col {
            width: 33.33333333%;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <h1>Gun Violence</h1>
    </div>
    <!--/.navbar-header-->

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li><a href="ReportShooting" style="font-size: 16px;">Report a Shooting</a></li>
            <li class="dropdown">
                <a href="states" class="dropdown-toggle" style="font-size: 16px;">States <b
                        class="caret"></b></a>
                <ul class="dropdown-menu multi-column columns-3">
                    <li>
                        <div class="row">
                            <div id="col" class="col-md-10">
                                <ul class="multi-column-dropdown">
                                    <li><a class="state-link" href="cities?state=AL">Alabama</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=AK">Alaska</a></li>
                                    <li><a class="state-link" href="cities?state=AZ">Arizona</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=AR">Arkansas</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=CA">California</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=CO">Colorado</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=Ct">Connecticut</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=DE">Delaware</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=DC">District of
                                        Columbia</a></li>
                                    <li><a class="state-link" href="cities?state=FL">Florida</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=GA">Georgia</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=HI">Hawaii</a></li>
                                    <li><a class="state-link" href="cities?state=ID">Idaho</a></li>
                                    <li><a class="state-link" href="cities?state=IL">Illinois</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=ID">Indiana</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=IA">Iowa</a></li>
                                    <li><a class="state-link" href="cities?state=KA">Kansas</a></li>
                                </ul>
                            </div>
                            <div id="col" class="col-md-10">
                                <ul class="multi-column-dropdown">
                                    <li><a class="state-link" href="cities?state=KY">Kentucky</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=LA">Louisiana</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=ME">Maine</a></li>
                                    <li><a class="state-link" href="cities?state=MD">Maryland</a>
                                    </li>
                                    <li><a class="state-link"
                                           href="cities?state=MA">Massachusetts</a></li>
                                    <li><a class="state-link" href="cities?state=MI">Michigan</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=MN">Minnesota</a>
                                    </li>
                                    <li><a class="state-link" href="cities?state=MS">Mississippi</a>
                                    </li>
                                    <li><a href="cities?state=MO">Missouri</a></li>
                                    <li><a href="cities?state=MT">Montana</a></li>
                                    <li><a href="cities?state=NE">Nebraska</a></li>
                                    <li><a href="cities?state=NV">Nevada</a></li>
                                    <li><a href="cities?state=NH">New Hampshire</a></li>
                                    <li><a href="cities?state=NJ">New Jersey</a></li>
                                    <li><a href="cities?state=NM">New Mexico</a></li>
                                    <li><a href="cities?state=NY">New York</a></li>
                                    <li><a href="cities?state=NC">North Carolina</a></li>

                                </ul>
                            </div>
                            <div id="col" class="col-md-10">
                                <ul class="multi-column-dropdown">
                                    <li><a href="cities?state=ND">North Dakota</a></li>
                                    <li><a href="cities?state=OH">Ohio</a></li>
                                    <li><a href="cities?state=OR">Oregon</a></li>
                                    <li><a href="cities?state=PA">Pennsylvania</a></li>
                                    <li><a href="cities?state=RI">Rhode Island</a></li>
                                    <li><a href="cities?state=SC">South Carolina</a></li>
                                    <li><a href="cities?state=SD">South Dakota</a></li>
                                    <li><a href="cities?state=TN">Tennessee</a></li>
                                    <li><a href="cities?state=TX">Texas</a></li>
                                    <li><a href="cities?state=UT">Utah</a></li>
                                    <li><a href="cities?state=VT">Vermont</a></li>
                                    <li><a href="cities?state=VI">Virgin Islands</a></li>
                                    <li><a href="cities?state=VA">Virginia</a></li>
                                    <li><a href="cities?state=WA">Washington</a></li>
                                    <li><a href="cities?state=WV">West Virginia</a></li>
                                    <li><a href="cities?state=WI">Wisconsin</a></li>
                                    <li><a href="cities?state=WY">Wyoming</a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </ul>
            </li>
            <li><a href="explorelaws" style="font-size: 16px;">Explore Gun Control Laws</a></li>
            <li><a href="Graphs" style="font-size: 16px;">Graphics</a></li>
        </ul>
    </div>
    <!--/.navbar-collapse-->
</nav>
<!--/.navbar-->

<!--<div class="NavBar">
    <h1>Gun Violence</h1>
    <br/>

    <ul style="width: 250px;">
        <li><a href="ReportShooting" style="width: 218px">Report a Shooting</a></li>
        <li><a href="states">List of All States</a></li>
        <li class="dropdown">
            <a href="states"  style="width: 218px" class="dropbtn">States</a>
            <div class="dropdown-content">
                    <a href="cities?state=AL">Alabama</a>
                    <a href="cities?state=AK">Alaska</a>
                    <a href="cities?state=AZ">Arizona</a>
                    <a href="cities?state=AR">Arkansas</a>
                    <a href="cities?state=CA">California</a>
                    <a href="cities?state=CO">Colorado</a>
                    <a href="cities?state=Ct">Connecticut</a>
                    <a href="cities?state=DE">Delaware</a>
                    <a href="cities?state=DC">District of Columbia</a>
                    <a href="cities?state=FL">Florida</a>
                    <a href="cities?state=GA">Georgia</a>
                    <a href="cities?state=HI">Hawaii</a>
                    <a href="cities?state=ID">Idaho</a>
                    <a href="cities?state=IL">Illinois</a>
                    <a href="cities?state=ID">Indiana</a>
                    <a href="cities?state=IA">Iowa</a>
                    <a href="cities?state=KA">Kansas</a>
                    <a href="cities?state=KY">Kentucky</a>
                    <a href="cities?state=LA">Louisiana</a>
                    <a href="cities?state=ME">Maine</a>
                    <a href="cities?state=MD">Maryland</a>
                    <a href="cities?state=MA">Massachusetts</a>
                    <a href="cities?state=MI">Michigan</a>
                    <a href="cities?state=MN">Minnesota</a>
                    <a href="cities?state=MS">Mississippi</a>
                    <a href="cities?state=MO">Missouri</a>
                    <a href="cities?state=MT">Montana</a>
                    <a href="cities?state=NE">Nebraska</a>
                    <a href="cities?state=NV">Nevada</a>
                    <a href="cities?state=NH">New Hampshire</a>
                    <a href="cities?state=NJ">New Jersey</a>
                    <a href="cities?state=NM">New Mexico</a>
                    <a href="cities?state=NY">New York</a>
                    <a href="cities?state=NC">North Carolina</a>
                    <a href="cities?state=ND">North Dakota</a>
                    <a href="cities?state=OH">Ohio</a>
                    <a href="cities?state=OR">Oregon</a>
                    <a href="cities?state=PA">Pennsylvania</a>
                    <a href="cities?state=RI">Rhode Island</a>
                    <a href="cities?state=SC">South Carolina</a>
                    <a href="cities?state=SD">South Dakota</a>
                    <a href="cities?state=TN">Tennessee</a>
                    <a href="cities?state=TX">Texas</a>
                    <a href="cities?state=UT">Utah</a>
                    <a href="cities?state=VT">Vermont</a>
                    <a href="cities?state=VI">Virgin Islands</a>
                    <a href="cities?state=VA">Virginia</a>
                    <a href="cities?state=WA">Washington</a>
                    <a href="cities?state=WV">West Virginia</a>
                    <a href="cities?state=WI">Wisconsin</a>
                    <a href="cities?state=WY">Wyoming</a>
            </div>
        </li>
        <li><a href="explorelaws"  style="width: 218px">Explore Gun Control Laws</a></li>
        <li><a href="Graphs"  style="width: 218px">Graphics</a></li>
    </ul>
</div>-->
<div class="rssFeed">
    <object align="center">
        <!-- start sw-rss-feed code -->
        <script type="text/javascript">
            <!--
            rssfeed_url = new Array();
            rssfeed_url[0] =
                "https://www.google.com/alerts/feeds/03625264460399442606/17349316161219510604";
            rssfeed_frame_width = "600";
            rssfeed_frame_height = "400";
            rssfeed_scroll = "on";
            rssfeed_scroll_step = "6";
            rssfeed_scroll_bar = "off";
            rssfeed_target = "_blank";
            rssfeed_font_size = "12";
            rssfeed_font_face = "";
            rssfeed_border = "off";
            rssfeed_css_url = "";
            rssfeed_title = "off";
            rssfeed_title_name = "";
            rssfeed_title_bgcolor = "#3366ff";
            rssfeed_title_color = "#fff";
            rssfeed_title_bgimage = "";
            rssfeed_footer = "off";
            rssfeed_footer_name = "rss feed";
            rssfeed_footer_bgcolor = "#fff";
            rssfeed_footer_color = "#333";
            rssfeed_footer_bgimage = "";
            rssfeed_item_title_length = "150";
            rssfeed_item_title_color = "#337ab7";
            rssfeed_item_bgcolor = "#fff";
            rssfeed_item_bgimage = "";
            rssfeed_item_border_bottom = "on";
            rssfeed_item_source_icon = "off";
            rssfeed_item_date = "off";
            rssfeed_item_description = "on";
            rssfeed_item_description_length = "250";
            rssfeed_item_description_color = "#000";
            rssfeed_item_description_link_color = "#000";
            rssfeed_item_description_tag = "off";
            rssfeed_no_items = "0";
            rssfeed_cache = "e66a6016135e0a74963c829d3e548684";
            //-->
        </script>
        <script type="text/javascript" src="//feed.surfing-waves.com/js/rss-feed.js"></script>
    </object>
</div>

</body>
</html>