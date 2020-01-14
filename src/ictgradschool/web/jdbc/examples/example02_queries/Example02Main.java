package ictgradschool.web.jdbc.examples.example02_queries;

import ictgradschool.web.jdbc.examples.Lecturer;
import ictgradschool.web.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * A sample program which shows how we can use createStatement(), prepareStatement(), and executeQuery() to execute
 * SQL SELECT statements on a database, then to iterate through the provided ResultSet to get the results of that query.
 * <p>
 * Notice how there's no DB access code in this file, beyond establishing the connection. This is an example of good
 * encapsulation - see {@link Example02LecturerDAO} for more details.
 *
 * @author Andrew Meads
 */
public class Example02Main {

    private void start() {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            // Notice that we can re-use the same Connection object for both these calls to the database. We don't need
            // to re-establish a connection every time we create a new SQL query.

            // Notice that this code looks nice and clean - we don't have SQL statements mixed in with our Java code here.
            // We've kept that all in one place - the DAO class. This is good practice.

            List<Lecturer> allLecturers = Example02LecturerDAO.getAllLecturers(conn);
            System.out.println("All lecturers:");
            for(Lecturer l : allLecturers) {
                System.out.println(l);
            }

            System.out.println();

            List<Lecturer> lecturersInComp219 = Example02LecturerDAO.getCourseLecturers("COMP", 219, conn);
            System.out.println("Lecturers teaching COMP219:");
            for(Lecturer l : lecturersInComp219) {
                System.out.println(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Example02Main().start();
    }

}
