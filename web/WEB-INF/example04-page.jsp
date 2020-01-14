<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Example 04 - Lecturers from Database</title>
    <link rel="stylesheet" href="./assets/site.css">
</head>
<body>

<div class="container">
    <h1>Lecturers</h1>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Staff number</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Office</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="lecturer" items="${lecturers}">
            <tr>
                <td>${lecturer.staffNo}</td>
                <td>${lecturer.firstName}</td>
                <td>${lecturer.lastName}</td>
                <td>${lecturer.office}e</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>