package ictgradschool.web.jdbc.ex01;

import ictgradschool.Keyboard;
import ictgradschool.web.jdbc.examples.Lecturer;
import ictgradschool.web.jdbc.examples.example02_queries.Example02LecturerDAO;
import ictgradschool.web.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ArticleSearcher {

    // TODO Exercise one step 7: Write this program according to the requirements in the lab handout.

    private void start() throws IOException, SQLException {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            while (true) {

                System.out.print("Please enter a search string: > ");

                String title = Keyboard.readInput();
                if(title.equals("quit")){
                    System.exit(0);
                }
                if (title.length() == 0) {
                    System.exit(0);

                }
                List<Article> ArticleByTitle = ArticleDAO.getArticleMatching(title, conn);

                if (ArticleByTitle == null) {
                    System.out.println("no matching article");
                } else {

                    for (Article a : ArticleByTitle)

                        System.out.println(a);
                }
            }


            } catch(SQLException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }


    }


    public static void main(String[] args) throws IOException, SQLException {
        new ArticleSearcher().start();
    }

}
