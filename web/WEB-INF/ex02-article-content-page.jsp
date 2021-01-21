<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Exercise 02 - Article Content</title>
    <link rel="stylesheet" href="./assets/site.css">
</head>
<body>

<div class="container">

    <%-- TODO Exercise two step 5: Display the article --%>

    <c:forEach var="articleContent" items="${articlesContent}">


        <h1>Article title goes here</h1>
        <h2>${articleContent.title}</h2>
        <p>Article body goes here</p>
        <td>${articleContent.text}</td>

    </c:forEach>

    <%-- TODO Exercise two-and-a-half: Add button or hyperlink to delete article --%>
    <FORM name="form1 " method="post">
    <input type="button" value="click" id="delete">
    </FORM>

</div>

</body>
</html>