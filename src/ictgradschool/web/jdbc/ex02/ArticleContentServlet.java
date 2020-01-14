package ictgradschool.web.jdbc.ex02;

import com.sun.org.apache.xpath.internal.operations.Bool;
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

@WebServlet(name = "ex02-article-content", urlPatterns = { "/ex02-article-content" })
public class ArticleContentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO Exercise two step 4: Implement this method

    }
}
