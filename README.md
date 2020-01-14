Web Lab 15 &ndash; JDBC & DAOs
==========

Begin by forking this repository into your namespace by clicking the ```fork``` button above, then selecting your username from the resulting window. Once completed, click the ```clone``` button, copy the ```Clone with HTTPS``` value. Open IntelliJ, and from the welcome screen click ```Check out from Version Control -> Git```, then paste the copied URL into the ```URL``` field of the resulting window. Provide your GitLab username and password if prompted.

Explore the files in the project, familiarizing yourself with the content.

When complete, demonstrate your code to your tutor. This must be verified with your tutor by the end of the week.

Before you start
----------
1. There are extensive, well-commented examples included in the project. Make sure to study these if you're stuck.

2. In the `scripts` folder, you'll see three SQL script files. Make sure to run these against your database. [`ex01-init.sql`](scripts/ex01-init.sql) is required for exercises one and two; [`ex03-init.sql`](scripts/ex03-init.sql) is required for exercise three; and [`examples-init.sql`](scripts/examples-init.sql) is required to run the provided examples.

3. Remember to modify [`connection.properties`](web/WEB-INF/res/connection.properties), located in the `WEB-INF/res` folder, to point to your database and to provide your username and password.

4. **Always remember your `try-with-resources` statements** whenever you create new connections, statements or prepared statements, and whenever you obtain query results. Forgetting to do so may cause database resource starvation issues - not just for you, but for everyone accessing the trex-sandwich server!

5. As a security best-practice, **always** use prepared statements where possible, especially when the SQL takes parameters.


Exercise One &ndash; JDBC SELECTs & DAOs
----------
In this exercise, we'll begin a simple Data Access Object (DAO) which will allow us to query for articles in a database based on their id or title, or simply get a list of all articles. We'll then use the DAO in some simple console applications to test its functionality.

Perform the following tasks to complete this exercise, testing as you go:

1. After making sure that you've run [`ex01-init.sql`](scripts/ex01-init.sql) with no errors, the first step is to complete the [`Article`](src/ictgradschool/web/jdbc/ex01/Article.java) class, which represents information about an article stored in the database. Examine the `CREATE TABLE` statement in [`ex01-init.sql`](scripts/ex01-init.sql) to determine the fields which are needed by this "Plain Old Java Object" (POJO).

   **Hint:** Remember that it might pay to allow the article's id (primary key) field to be `null`. This is because we're using the database's `AUTO_INCREMENT` functionality to generate the primary key value automatically when we first add an article to the database. See the [`Lecturer`](src/ictgradschool/web/jdbc/examples/Lecturer.java) class in the examples for further details.

   In addition to the class's fields, make sure to include appropriate getters, setters, and constructors. An appropriate `toString()` method might also help when printing out an article's details in the next step.
   
2. Complete the `getAllArticles()` method in the [`ArticleDAO`](src/ictgradschool/web/jdbc/ex01/ArticleDAO.java) class. This method should execute an appropriate SQL `SELECT` statement on the given [`Connection`](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/Connection.html), which will get all articles from the database. It should then iterate through the [`ResultSet`](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/ResultSet.html) that's returned, and add a new [`Article`](src/ictgradschool/web/jdbc/ex01/Article.java) to the given `articles` list for each row in the result set.

3. Complete the [`ArticleListPrinter`](src/ictgradschool/web/jdbc/ex01/ArticleListPrinter.java) class, which is a simple program that prints out a list of all articles in the database. This program's `start()` method should:

   1. Create a connection to the database; then
   2. Use your DAO's `getAllArticles()` method you created in step 2 above to get a list of all articles; then
   3. Loop through that list and print them out.

4. Create a new method in [`ArticleDAO`](src/ictgradschool/web/jdbc/ex01/ArticleDAO.java), called `getArticleById()`. This method should take an `int` id and a database [`Connection`](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/Connection.html) as arguments, and should use a *prepared statement* to execute a database query that will get the article with the given id from the database. If such an article exists, the method should return that article. Otherwise, it should return `null`.

5. Using your method from step 4 above, complete the implementation of the [`SingleArticlePrinter`](src/ictgradschool/web/jdbc/ex01/SingleArticlePrinter.java) program. This program prompts the user to enter an id, and should print out the article with that id, if it exists. If not, a message such as "no matching article" should be printed instead.

6. Create a new method in [`ArticleDAO`](src/ictgradschool/web/jdbc/ex01/ArticleDAO.java), called `getArticlesMatching`. This method should take a `String` search query and a database [`Connection`](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/Connection.html) as arguments, and should return a list of all articles whose *title* contains the given search string.

7. Write a simple console application called [`ArticleSearcher`](src/ictgradschool/web/jdbc/ex01/ArticleSearcher.java) that will allow a user to search for articles based on their titles. The program should perform the following tasks:
   
   1. Prompt the user to enter a search string
   2. If the user enters an empty string, the program should exit.
   3. Otherwise, the program should search for matching articles using your method you wrote above.
   4. If there are any matches, a list of matching articles should be printed.
   5. If there are no matches, a message saying so should be printed.
   6. Either way, your program should loop back to the beginning, allowing the user to keep entering search strings until they wish to quit.
   
   
Exercise Two &ndash; Servlets & INSERTs
----------
In this exercise, we'll practice integrating JDBC with a Servlet. We will re-use the [`ArticleDAO`](src/ictgradschool/web/jdbc/ex01/ArticleDAO.java) class you've already written, and add additional functionality to it. Perform the following steps to complete this exercise, testing as you go:

1. Begin by modifying the `doGet()` method of [`ArticleListServlet`](src/ictgradschool/web/jdbc/ex02/ArticleListServlet.java). This method should establish a database connection, then use your DAO class written in exercise one to get a list of all articles. It should then forward the request onto the JSP page [`WEB-INF/ex02-article-list-page.jsp`](web/WEB-INF/ex02-article-list-page.jsp) using a request dispatcher, making sure to supply the articles list as a request attribute.

2. Modify [`WEB-INF/ex02-article-list-page.jsp`](web/WEB-INF/ex02-article-list-page.jsp) so that, at the marked location, the list of articles you supplied in step 1 is displayed in the table. Use appropriate JSTL/EL tags and expressions for this.

3. Modify the code you wrote in step 2 above to add a hyperlink to each article's title in the table. The hyperlink should point to `./ex02-article-content` (which will be served by [`ArticleContentServlet`](src/ictgradschool/web/jdbc/ex02/ArticleContentServlet.java) below), and should supply the article's `id` as a request parameter.

   **Hint:** Form submissions aren't the only way of supplying request parameters to servlets - you can directly write the query string (the part of the URL after the '?') in the hyperlink's `href`. For example:
   
   ```html
   <a href="./ex02-article-content?id=42">Hello, World!</a>
   ```
   
4. Modify the `doGet()` method of [`ArticleContentServlet`](src/ictgradschool/web/jdbc/ex02/ArticleContentServlet.java). This method should establish a database connection, then use your DAO class written in exercise one to get the article with the id matching the one supplied as a parameter in step 3. It should then forward the request onto the JSP page [`WEB-INF/ex02-article-content-page.jsp`](web/WEB-INF/ex02-article-content-page.jsp), supplying the matching article as a request attribute.

5. Modify [`WEB-INF/ex02-article-content-page.jsp`](web/WEB-INF/ex02-article-content-page.jsp) to display the article supplied in step 4.

6. Back on [`WEB-INF/ex02-article-list-page.jsp`](web/WEB-INF/ex02-article-list-page.jsp), you'll notice a form allowing the user to add new articles. Currently this form does nothing, as the servlet does not accept POST requests.

   First, we need to create the functionality in the DAO which allows articles to be added. To do this, complete the `insertArticle()` method of [`ArticleDAO`](src/ictgradschool/web/jdbc/ex01/ArticleDAO.java). This method takes an article and a database connection as parameters and should execute an SQL `INSERT` statement to add the article to the database.
   
   **Hint:** We want to write our code so that the new article's id is auto-generated by the database, and returned to us. We can then set it on the supplied article before returning from the method. As an example of how this can be done, please see the DAO included in *example 03*.
   
7. Now we'll use the code we wrote in step 6 above. Add a `doPost()` method to [`ArticleListServlet`](src/ictgradschool/web/jdbc/ex02/ArticleListServlet.java) which reads the data submitted in the form, and uses it to create a new [`Article`](src/ictgradschool/web/jdbc/ex01/Article.java)object. The method should then establish a database connection and use your method from step 6 to save the article to the database. Finally, it should send a *redirect* to `./ex02-article-list` using the following code:

   ```java
   resp.sendRedirect("./ex02-article-list");
   ```
   
   This will cause the page to reload, which should display the new article in the list.

   
(BONUS) Exercise Two-and-a-half &ndash; DELETEs
----------
Further modify[`ArticleDAO`](src/ictgradschool/web/jdbc/ex01/ArticleDAO.java) to include a method which will delete an article from the database, given its id. Next, modify [`WEB-INF/ex02-article-content-page.jsp`](web/WEB-INF/ex02-article-content-page.jsp) to include a hyperlink or button that, when clicked, will delete the currently-displayed article then return to the main articles list page.

**Hint:**  In addition to edditing the JSP file, you will likely have to modify one of the existing servlets or add a new one to handle this functionality.


Exercise Three &ndash; More Complex Data
----------
Take a look at [`ex03-init.sql`](scripts/ex03-init.sql) in the `scripts` folder (and run it on your database if you haven't already done so). Here you'll see a database that's a little more complex than the Articles database used in the previous exercises. In this exercise, we'll build a console application allowing the user to query this database, and in doing so we'll see that similar methods of encapsulation and good practice can be used (i.e. DAOs), even if the Java objects don't match up one-to-one with database tables.

Take a look at the [`RoleInfo`](src/ictgradschool/web/jdbc/ex03/RoleInfo.java) class in the `ex03` package. Objects of this class contain information about an actor's name, the title of a film with that actor, and the role that actor played in that film. We'll be adding methods to the[`FilmsDAO`](src/ictgradschool/web/jdbc/ex03/FilmsDAO.java) class which will perform SQL queries that return lists[`RoleInfo`](src/ictgradschool/web/jdbc/ex03/RoleInfo.java) objects. The information required to construct these objects comes from multiple tables - `pfilms_film`, `pfilms_actor`, `pfilms_role`, and `pfilms_participates_in`.

Perform the following steps to complete this exercise, testing as you go. It might pay to design and test the SQL queries separately before writing the JDBC code, as they are significantly more complex this time.

1. Complete the `getRoleInfoByActor()` method in the [`FilmsDAO`](src/ictgradschool/web/jdbc/ex03/FilmsDAO.java) class, which takes a search string and a connection as parameters. The method should run an SQL query which gets the names of all actors containing the given search string (either first or last name), along with titles of films those actors participated in, and the roles of those actors in those films. This should be achievable with a single multi-table `SELECT` statement. The method should then iterate through the returned [`ResultSet`](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/ResultSet.html) and, for each row, add a corresponding[`RoleInfo`](src/ictgradschool/web/jdbc/ex03/RoleInfo.java) object to the given list.

2. Modify the `informationByActor()` method of [`Ex03Main`](src/ictgradschool/web/jdbc/ex03/Ex03Main.java) so that it calls your method from step 1, supplying the user's input. It should then iterate through the returned list of[`RoleInfo`](src/ictgradschool/web/jdbc/ex03/RoleInfo.java) objects and print them out. If no matches were found, a message saying so should be printed instead.

3. Create another method in[`FilmsDAO`](src/ictgradschool/web/jdbc/ex03/FilmsDAO.java), called `getRoleInfoByFilm()`. This method should function similarly to `getRoleInfoByActor()`, except it should search for matches in film titles, rather than actor names.

4. Add new functionality to the console app that will use your method from step 3 to allow users to search by film title.

(BONUS) Exercise Three-and-a-half &ndash; More Practice
----------
Create a new POJO in the `ex03` package representing the title of a film along with its genre. Add a method to your DAO which takes a search string as a parameter, and returns a list of film titles with genres containing the search string, along with those films' genres. Add new functionality to your console app which allows the user to search by genre.