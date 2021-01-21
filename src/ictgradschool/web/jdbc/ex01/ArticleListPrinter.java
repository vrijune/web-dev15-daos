package ictgradschool.web.jdbc.ex01;

import ictgradschool.web.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleListPrinter {

    private void start() throws IOException, SQLException {

        // TODO Exercise one step 3: Implement this.



        try (Connection connection = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            List<Article> allarticles = ArticleDAO.getAllArticles(connection);
            System.out.println("All articles");

            for (Article article : allarticles) {
                System.out.println(article);
            }

            System.out.println();
        }


    }

    public static void main(String[] args) throws IOException, SQLException {
        new ArticleListPrinter().start();
    }

}
