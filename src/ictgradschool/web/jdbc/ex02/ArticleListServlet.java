package ictgradschool.web.jdbc.ex02;

import ictgradschool.web.jdbc.ex01.Article;
import ictgradschool.web.jdbc.ex01.ArticleDAO;
import ictgradschool.web.jdbc.examples.Lecturer;
import ictgradschool.web.jdbc.examples.example03_modifications.Example03LecturerDAO;
import ictgradschool.web.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ex02-article-list", urlPatterns = {"/ex02-article-list"})
public class ArticleListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO Exercise two step 1: Implement this method

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            List<Article> allarticles = ArticleDAO.getAllArticles(conn);

            req.setAttribute("articles", allarticles);
            req.getRequestDispatcher("WEB-INF/ex02-article-list-page.jsp").forward(req, resp);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // TODO Exercise two step 7: Write a doPost method

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title;
        String text;

        title = req.getParameter("title");
        text = req.getParameter("body");
        Article newArticle = new Article(title, text);


        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

              ArticleDAO.insertArticle(newArticle,conn);



//
//            req.setAttribute("newArticle", newArticle);

        } catch (SQLException e) {

            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);

        }
        resp.sendRedirect("./ex02-article-list");

    }


}



