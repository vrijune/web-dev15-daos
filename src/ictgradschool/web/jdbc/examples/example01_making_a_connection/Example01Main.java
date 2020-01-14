package ictgradschool.web.jdbc.examples.example01_making_a_connection;

import ictgradschool.web.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * An example program showing how a DB {@link Connection} can be established, using the provided
 * {@link DBConnectionUtils} class.
 * <p>
 * When run, this program establishes a connection to the database, using the information contained within the
 * connection.properties file, which is located in the src folder.
 *
 * @author Andrew Meads
 */
public class Example01Main {

    private void start() {
        // Note that we ALWAYS use try-with-resources when creating connections.
        // If DB connections aren't closed properly, other database users - including yourself and your classmates - may
        // soon find they're unable to connect due to all the available connections being taken without being closed.
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            System.out.println("Database connection successful!");

        } catch (IOException ex) {

            // If you get an IOException, there's a problem with your properties file.
            ex.printStackTrace();

        } catch (SQLException ex) {

            // If you get an SQLException, it means a DB connection can't be established, probably due to an incorrect
            // URL, username, or password specified in the properties file.
            ex.printStackTrace();

        }
    }

    public static void main(String[] args) {

        new Example01Main().start();

    }

}
