<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Exercise 02 - Article List</title>
    <link rel="stylesheet" href="./assets/site.css">
</head>
<body>

<div class="container">
    <h1>Exercise 02 - Articles</h1>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
        </tr>
        </thead>
        <tbody>

        <%-- TODO Exercise two step 2: Display articles list --%>

        <%-- TODO Exercise two step 3: Add hyperlinks to ./ex02-article-content --%>

        </tbody>
    </table>

    <h2>New Article</h2>
    <form action="./ex02-article-list" , method="post">

        <div>
            <label for="new-article-title">Title:</label><br>
            <input type="text" name="title" id="new-article-title" placeholder="Your title here" required>
        </div>
        <div>
            <label for="new-article-body">Content:</label><br>
            <textarea name="body" id="new-article-body" placeholder="Your content here" rows="10" required></textarea>
        </div>
        <div>
            <button type="submit">Submit</button>
        </div>

    </form>
</div>

</body>
</html>