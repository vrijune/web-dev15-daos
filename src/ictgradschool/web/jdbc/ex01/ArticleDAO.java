package ictgradschool.web.jdbc.ex01;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    public static List<Article> getAllArticles(Connection conn) throws SQLException {

        List<Article> articles = new ArrayList<>();

        // TODO Exercise one step 2: Complete this.

        return articles;

    }

    // TODO Exercise one step 4: Write getArticleById method


    // TODO Exercise one step 6: Write getArticlesMatching method


    public static boolean insertArticle(Article article, Connection conn) throws SQLException {

        // TODO Exercise two step 6: Implement this.

        return false;

    }

    // TODO Exercise two-and-a-half: Write deleteArticle method

}
