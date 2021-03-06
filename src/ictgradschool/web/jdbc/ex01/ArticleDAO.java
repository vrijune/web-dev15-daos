package ictgradschool.web.jdbc.ex01;

import ictgradschool.web.jdbc.examples.Lecturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    public static List<Article> getAllArticles(Connection conn) throws SQLException {

        List<Article> articles = new ArrayList<>();

        // TODO Exercise one step 2: Complete this.
        try (Statement stmt = conn.createStatement()) {


            try (ResultSet resultSet = stmt.executeQuery(
                    "SELECT * FROM lab15_articles"
            )) {
                while (resultSet.next()) {


                    Article article = createArticleFromResultSet(resultSet);
                    articles.add(article);


                }


            }
        }

        return articles;

    }

    private static Article createArticleFromResultSet(ResultSet resultSet) throws SQLException {

        Article article = new Article(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3)

        );

        return article;

    }


    // TODO Exercise one step 4: Write getArticleById method
    public static List<Article> getArticleById(int artid, Connection connection) {

        List<Article> articles = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from lab15_articles a where a.artid = ? "
        )) {
            statement.setInt(1, artid);

            try (ResultSet rs = statement.executeQuery()) {
                while (!rs.next()) {
                    return null;
                }
                Article article = createArticleFromResultSet(rs);
                articles.add(article);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;
    }


    // TODO Exercise one step 6: Write getArticlesMatching method
    public static List<Article> getArticleMatching(String title, Connection connection) {

        List<Article> articles = new ArrayList<>();
        title = "%" + title + "%";

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from lab15_articles a where a.title LIKE ? "
        )) {
            statement.setString(1, title);

            try (ResultSet rs = statement.executeQuery()) {
                while (!rs.next()) {
                    return null;
                }
                Article article = createArticleFromResultSet(rs);
                articles.add(article);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;


    }


    public static boolean insertArticle(Article article, Connection conn) throws SQLException {

        // TODO Exercise two step 6: Implement this.


        if (article.getArtid() == null) {
            return insertArticleGenerateId(article, conn);
        } else {
            return insertArticleUseArtId(article, conn);
        }
    }

    private static boolean insertArticleUseArtId(Article article, Connection conn) throws SQLException {

        // We can use prepared statements for database inserts as well as queries.
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO lab15_articles(artid,title,body) VALUES (?, ?, ?)")) {

            stmt.setInt(1, article.getArtid());
            stmt.setString(2, article.getTitle());
            stmt.setString(3, article.getText());


            // rowsAffected will be 1 if the insert was successful (because we're trying to insert 1 row), or 0 if it wasn't.
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected == 1);

        }
    }


    private static boolean insertArticleGenerateId(Article article, Connection conn) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO lab15_articles(title, body) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, article.getTitle());
            stmt.setString(2, article.getText());


            int rowsAffected = stmt.executeUpdate();

            // If we didn't actually insert anything, we can't continue.
            if (rowsAffected == 0) {
                return false;
            }

            // This code will allow us to get the primary keys that were generated by the database. These will be returned
            // in a ResultSet with one column. Each row will correspond to one generated key (in case we inserted multiple
            // rows at once). In this case, there will be only one row since we only inserted one lecturer.
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                keys.next(); // Move to the fist row.
                int artid = keys.getInt(1);
                article.setArtid(artid); // Set the lecturer's staff number to the one generated by the DB.

                return true;
            }


        }
    }
        // TODO Exercise two-and-a-half: Write deleteArticle method

        public static boolean deleteArticle (int artid, Connection conn) throws SQLException {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM lab15_article WHERE artid_no = ?")) {
                stmt.setInt(1,artid);


                int rowsAffected = stmt.executeUpdate();

                return (rowsAffected == 1);
            }

        }
}
