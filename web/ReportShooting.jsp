<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <!--  jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

    <!-- Isolated Version of Bootstrap, not needed if your site already uses Bootstrap -->
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css"/>

    <!-- Bootstrap Date-Picker Plugin -->
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Special version of Bootstrap that only affects content wrapped in .bootstrap-iso -->
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css"/>

    <title>Report a Shooting</title>
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

        .bootstrap-iso .col-sm-6 {
            width: auto;
        }

        .bootstrap-iso .col-md-6 {
            width: auto;
        }

        .bootstrap-iso
        .formden_header h2,
        .bootstrap-iso
        .formden_header p,
        .bootstrap-iso form {
            font-family: Arial, Helvetica, sans-serif;
            color: black
        }

        .bootstrap-iso form button,
        .bootstrap-iso form button:hover {
            color: white !important;
        }

        .bootstrap-iso
        .form-control:focus {
            border-color: #337ab7;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(51, 122, 183, 0.6);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(51, 122, 183, 0.6);
        }

        .asteriskField {
            color: red;
        }
    </style>

    <script>
        function empty() {
            var result = true;
            var shootingType = document.getElementById("shootingType").value;
            var cityId = document.getElementById("cityId").value;
            var shootingDate = document.getElementById("date").value;
            var numGuns = document.getElementById("numberOfGuns").value;
            var numKilled = document.getElementById("numberKilled").value;
            var numInjured = document.getElementById("numberInjured").value;

            if (shootingType === "Select a Shooting Type") {
                //alert("Please enter a city ID");
                document.getElementById("shootingTypeGroup").className = "form-group has-error";
                result = false;
            }
            if (cityId === "") {
                //alert("Please enter a city ID");
                document.getElementById("cityIdGroup").className = "form-group has-error";
                result = false;
            }
            if (shootingDate === "") {
                //alert("Please enter the date of the shooting");
                document.getElementById("shootingDateGroup").className = "form-group has-error";
                result = false;
            }
            if (numGuns === "") {
                //alert("Please enter the number of guns involved in the shooting");
                document.getElementById("numberOfGunsGroup").className = "form-group has-error";
                result = false;
            }
            if (numInjured === "") {
                //alert("Please enter the number of individuals injured");
                document.getElementById("numberInjuredGroup").className = "form-group has-error";
                result = false;
            }
            if (numKilled === "") {
                //alert("Please enter number of individuals killed");
                document.getElementById("numberKilledGroup").className = "form-group has-error";
                result = false;
            }

            return result;
        }
    </script>

</head>
<body>
<ul class="breadcrumb">
    <li><a href="/GunViolence">Home</a></li>
    <li>Report a Shooting</li>
</ul>

<h1>${messages.title}</h1>
<br>

<!-- HTML Form (wrapped in a .bootstrap-iso div) -->
<div class="bootstrap-iso">
    <div class="container-fluid" style="width: 52%; margin: auto;">
        <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-12">
                <div method="post">
                    <div class="form-group" id="shootingTypeGroup">
                        <label class="control-label" for="shootingType">
                            Shooting Type
                            <span class="asteriskField">
                                        *
                                    </span>
                        </label>
                        <select class="select form-control" id="shootingType" name="shootingType">
                            <option value="Fatal">
                                Select a Shooting Type
                            </option>
                            <option value="Fatal">
                                Fatal
                            </option>
                            <option value="Non Fatal">
                                Non Fatal
                            </option>
                            <option value="School">
                                School
                            </option>
                        </select>
                    </div>
                    <div class="form-group" style="float: left; width: 33%; padding-right: 15px;" id="cityIdGroup">
                        <label class="control-label" for="cityId">
                            City ID
                            <span class="asteriskField">
                                        *
                                    </span>
                        </label>
                        <input class="form-control" id="cityId" name="city_Id" type="text"/>
                    </div>


                    <div class="form-group"
                         style="float: left; width: 33%; padding-right: 15px"
                         id="shootingDateGroup">
                        <label class="control-label" for="date">
                            Shooting Date
                            <span class="asteriskField">
                                        *
                                    </span>
                        </label>
                        <input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY"
                               type="text"/>
                    </div>
                    <div class="form-group" style="float: right; width: 33%;" id="numberOfGunsGroup">
                        <label class="control-label " for="numberOfGuns">
                            Number of Guns
                            <span class="asteriskField">
                                        *
                                    </span>
                        </label>
                        <input class="form-control" id="numberOfGuns" name="numberOfGuns"
                               type="text"/>
                    </div>
                    <div style="clear: left;"></div>
                    <div class="form-group ">
                        <label class="control-label " for="characteristics">
                            Characteristics
                        </label>
                        <textarea class="form-control" cols="40" id="characteristics"
                                  name="characteristics" rows="5"></textarea>
                    </div>
                    <div class="form-group" style="float: left; width: 50%; padding-right: 15px" id="numberInjuredGroup">
                        <label class="control-label" for="numberInjured">
                            Number Injured
                            <span class="asteriskField">
                                        *
                                    </span>
                        </label>
                        <input class="form-control" id="numberInjured" name="numberInjured"
                               type="text"/>
                    </div>
                    <div class="form-group"
                         style="float: left; width: 50%;"
                         id="numberKilledGroup">
                        <label class="control-label" for="numberKilled">
                            Number Killed
                            <span class="asteriskField">
                                        *
                                    </span>
                        </label>
                        <input class="form-control" id="numberKilled" name="numberKilled"
                               type="text"/>
                    </div>
                    <div class="form-group ">
                        <label class="control-label " for="typeOfSchool">
                            Type of School
                        </label>
                        <select class="select form-control" id="typeOfSchool" name="typeOfSchool">
                            <option value="">
                                Select a School Type
                            </option>
                            <option value="College">
                                College
                            </option>
                            <option value="High School">
                                High School
                            </option>
                            <option value="Middle School">
                                Middle School
                            </option>
                            <option value="Elementary School">
                                Elementary School
                            </option>
                            <option value="Unknown">
                                Unknown
                            </option>
                        </select>
                        <span class="help-block" id="hint_typeOfSchool">
                                    Only needed if the shooting was a school shooting
                                </span>
                    </div>
                    <div class="form-group">
                        <div>
                            <button class="btn btn-primary " name="submit" type="submit"
                                    onClick="return empty()">
                                Submit
                            </button>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <!-- Extra JavaScript/CSS added manually in "Settings" tab -->
    <!--  jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

    <!-- Bootstrap Date-Picker Plugin -->
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

    <script>
        $(document).ready(function () {
            var date_input = $('input[name="date"]'); //our date input has the name "date"
            var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent()
                                                                : "body";
            var options = {
                format: 'mm/dd/yyyy',
                container: container,
                todayHighlight: true,
                autoclose: true,
            };
            date_input.datepicker(options);
        })
    </script>

</body>
</html>
