package ictgradschool.web.jdbc.ex01;

import ictgradschool.Keyboard;
import ictgradschool.web.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SingleArticlePrinter {

    private void start() throws IOException, SQLException {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            System.out.print("Enter the article id: > ");
            int id = Integer.parseInt(Keyboard.readInput());

            // TODO Exercise one step 5: Complete this.
            List<Article> ArticlebyID = ArticleDAO.getArticleById(1,conn);
            for(Article a : ArticlebyID)
            if (id != a.getArtid() ) {
                System.out.println("no matching article");
            }else{
                System.out.println(a);
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException, SQLException {
        new SingleArticlePrinter().start();
    }

}
