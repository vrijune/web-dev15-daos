package ictgradschool.web.jdbc.ex02;

import ictgradschool.web.jdbc.ex01.Article;
import ictgradschool.web.jdbc.ex01.ArticleDAO;
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

@WebServlet(name = "ex02-article-list", urlPatterns = { "/ex02-article-list" })
public class ArticleListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO Exercise two step 1: Implement this method

    }

    // TODO Exercise two step 7: Write a doPost method
}
