package ictgradschool.web.jdbc.examples.example02_queries;

import ictgradschool.web.jdbc.examples.Lecturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains JDBC code to execute queries on the unidb_lecturers table (see examples-init.sql).
 * <p>
 * Encapsulating your database access code in separate files like this is good practice, as it promotes readability,
 * reusability, and maintainability.
 *
 * @author Andrew Meads
 * @see <a href="https://en.wikipedia.org/wiki/Data_access_object">DAO Wikipedia article</a>
 */
public class Example02LecturerDAO {

    /**
     * Gets all {@link Lecturer}s in the database.
     *
     * @param conn the DB connection to use
     * @return a {@link List} of lecturer objects
     * @throws SQLException if there's an error connecting to the database, or an error with the SQL query.
     */
    public static List<Lecturer> getAllLecturers(Connection conn) throws SQLException {

        List<Lecturer> lecturers = new ArrayList<>();

        // createStatement gives us a Statement object we can use to perform simple queries and updates.
        // Note the try-with-resources here - Statements and ResultSets (see below) also need to be closed when we're
        // finished with them (not just the Connections themselves).
        try (Statement stmt = conn.createStatement()) {

            // executeQuery will give us back a ResultSet object containing a table of results - in this case,
            // all rows in the unidb_lecturers table.
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM unidb_lecturers")) {

                // We can iterate through all rows in the ResultSet like this...
                while (rs.next()) {

                    // Within this while loop, we can use methods such as rs.getString, rs.getInt() etc to get the
                    // values in certain columns in the current row. For example:

                    // Gets the integer value in column 1, which is the first column (ResultSet indices are 1-based, not 0-based).
                    // int staffNo = rs.getInt(1);

                    // Gets the integer value in the "staff_no" column. This way reads better, and you don't need to
                    // remember the order of the columns - but it is ever-so-slightly slower performance-wise (the
                    // difference should be unnoticeable in most cases).
                    // int alsoStaffNo = rs.getInt("staff_no");

                    // in our case though, we'll call a method which can create a Lecturer object for us, given a ResultSet.
                    // This way, we can reuse the code contained in the method later on.
                    Lecturer lecturer = createLecturerFromResultSet(rs);
                    lecturers.add(lecturer);

                }

            }

        }

        return lecturers;
    }

    /**
     * Creates a {@link Lecturer} object from the current row of the given {@link ResultSet}.
     *
     * @param rs the DB query result from which we should create a lecturer
     * @return a {@link Lecturer}
     * @throws SQLException if there's a DB access error
     */
    private static Lecturer createLecturerFromResultSet(ResultSet rs) throws SQLException {

        Lecturer lecturer = new Lecturer(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
        );

        return lecturer;

    }

    /**
     * Gets all {@link Lecturer}s in the database which teach the course with the given dept and num.
     *
     * @param courseDept the course department, e.g. "COMP"
     * @param courseNum  the course number, e.g. "219"
     * @param conn       the DB connection to use
     * @return a {@link List} of lecturer objects
     * @throws SQLException if there's an error connecting to the database, or an error with the SQL query.
     */
    public static List<Lecturer> getCourseLecturers(String courseDept, int courseNum, Connection conn) throws SQLException {

        List<Lecturer> lecturers = new ArrayList<>();

        // prepareStatement is like createStatement, except we pre-supply the SQL to use, with any variables replaced
        // with "?". You MUST use prepared statements when running queries depending on user input, to help prevent
        // SQL injection attacks. In this case, there are two variables - t.dept an t.num.
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT l.* FROM unidb_lecturers l, unidb_teach t WHERE l.staff_no = t.staff_no AND t.dept = ? AND t.num = ?")) {

            // To fill the ?'s in prepared statements, use the setString, setInt, etc. methods. Again, indices are
            // 1-based, in the order they're written in the query. In this case, dept first, num second.
            stmt.setString(1, courseDept);
            stmt.setInt(2, courseNum);

            // When executing a prepared statement, we don't supply any SQL into the executeQuery method - we've already supplied
            // the SQL above!
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    // We can re-use the code we already wrote in the createLecturerFromResultSet method.
                    Lecturer lecturer = createLecturerFromResultSet(rs);
                    lecturers.add(lecturer);
                }

            }

        }

        return lecturers;
    }

}
