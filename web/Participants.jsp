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

    <title>Participants</title>
</head>
<body>
<h1>${messages.title}</h1>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th class="text-center" scope="col">ParticipantId</th>
        <th class="text-center" scope="col">Age</th>
        <th class="text-center" scope="col">Gender</th>
        <th class="text-center" scope="col">Name</th>
        <th class="text-center" scope="col">Relationship</th>
        <th class="text-center" scope="col">Participant Type</th>
        <th class="text-center" scope="col">Status</th>
        <th class="text-center" scope="col">Participant Number</th>
    </tr>
    </thead>
    <c:forEach items="${participants}" var="participants">
        <tr>
            <td align="center"><c:out value="${participants.getParticipantId()}"/></td>
            <td align="center"><c:out value="${participants.getAge()}"/></td>
            <td align="center"><c:out value="${participants.getGender()}"/></td>
            <td align="center"><c:out value="${participants.getName()}"/></td>
            <td align="center"><c:out value="${participants.getRelationship()}"/></td>
            <td align="center"><c:out value="${participants.getParticipantType().toString()}"/></td>
            <td align="center"><c:out value="${participants.getStatus()}"/></td>
            <td align="center"><c:out value="${participants.getParticipantNum()}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>