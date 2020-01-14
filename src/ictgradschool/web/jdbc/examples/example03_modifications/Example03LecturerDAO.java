package ictgradschool.web.jdbc.examples.example03_modifications;

import ictgradschool.web.jdbc.examples.Lecturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an extension of the Example02LecturerDAO, which adds methods to INSERT, UPDATE and DELETE Lecturers.
 *
 * @author Andrew Meads
 * @see <a href="https://en.wikipedia.org/wiki/Data_access_object">DAO Wikipedia article</a>
 */
public class Example03LecturerDAO {

    /**
     * Updates the given {@link Lecturer}, which should already exist in the database. If the lecturer doesn't already
     * exist in the database, this method will do nothing and return false.
     *
     * @param lecturer the lecturer to update
     * @param conn     the DB connection to use
     * @return true if the lecturer was successfully updated, false otherwise.
     * @throws SQLException if there's a database access error.
     */
    public static boolean updateLecturer(Lecturer lecturer, Connection conn) throws SQLException {

        // We can use prepared statements for database updates as well as queries.
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE unidb_lecturers SET fname = ?, lname = ?, office = ? WHERE staff_no = ?")) {

            // We set the prepared statement's variables exactly the same - replacing the ?'s with their actual values
            // obtained from the Lecturer object.
            stmt.setString(1, lecturer.getFirstName());
            stmt.setString(2, lecturer.getLastName());
            stmt.setString(3, lecturer.getOffice());
            stmt.setInt(4, lecturer.getStaffNo());

            // The executeUpdate() method will run an SQL INSERT, UPDATE, or DELETE. It will return an int value
            // specifying how many rows are affected by the statement. In this case, this will be either 1 (if the update
            // was successful) or 0 (if it wasn't, for example because a lecturer with the given staff_no doesn't exist).
            int rowsAffected = stmt.executeUpdate();

            // If rowsAffected is 1, it means the update was successful and we'll return true. Otherwise we return false.
            return (rowsAffected == 1);

        }

    }

    /**
     * Deletes the lecturer with the given staffNo from the database. Returns a value indicating whether the delete was
     * successful.
     *
     * @param staffNo the id of the lecturer to delete
     * @param conn    the DB connection to use
     * @return true if the lecturer was successfully deleted, false otherwise.
     * @throws SQLException if there's a database access error.
     */
    public static boolean deleteLecturer(int staffNo, Connection conn) throws SQLException {

        // We can use prepared statements for database deletes as well as queries.
        try (PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM unidb_lecturers WHERE staff_no = ?")) {

            stmt.setInt(1, staffNo);

            // The executeUpdate() method will run an SQL INSERT, UPDATE, or DELETE. It will return an int value
            // specifying how many rows are affected by the statement. In this case, this will be either 1 (if a row
            // was deleted) or 0 (if it wasn't because a lecturer with the given staff_no doesn't exist).
            int rowsAffected = stmt.executeUpdate();

            // If rowsAffected is 1, it means the delete was successful and we'll return true. Otherwise we return false.
            return (rowsAffected == 1);

        }

    }

    /**
     * Inserts the given {@link Lecturer} into the database.
     * <p>
     * There are two possibilities - if the lecturer's staff number is null, then we want the database to automatically
     * generate this value for us. If not, then we want to use the one we've provided. We separate these two cases into
     * their own methods, below.
     *
     * @param lecturer the lecturer to insert.
     * @param conn     the DB connection to use.
     * @return true if the lecturer was inserted, false otherwise.
     * @throws SQLException if there's a DB access error.
     */
    public static boolean insertLecturer(Lecturer lecturer, Connection conn) throws SQLException {

        if (lecturer.getStaffNo() == null) {
            return insertLecturerGenerateStaffNumber(lecturer, conn);
        } else {
            return insertLecturerUseExistingStaffNumber(lecturer, conn);
        }

    }

    /**
     * Inserts the given {@link Lecturer} with a non-null staff number into the database.
     *
     * @param lecturer the lecturer to insert.
     * @param conn     the DB connection to use.
     * @return true if the lecturer was inserted, false otherwise.
     * @throws SQLException if there's a DB access error.
     */
    private static boolean insertLecturerUseExistingStaffNumber(Lecturer lecturer, Connection conn) throws SQLException {

        // We can use prepared statements for database inserts as well as queries.
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO unidb_lecturers (staff_no, fname, lname, office) VALUES (?, ?, ?, ?)")) {

            stmt.setInt(1, lecturer.getStaffNo());
            stmt.setString(2, lecturer.getFirstName());
            stmt.setString(3, lecturer.getLastName());
            stmt.setString(4, lecturer.getOffice());

            // rowsAffected will be 1 if the insert was successful (because we're trying to insert 1 row), or 0 if it wasn't.
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected == 1);

        }
    }

    /**
     * Inserts the given {@link Lecturer} into the database, and sets its staff number to the one that was auto-generated
     * by the database (using AUTO_INCREMENT).
     *
     * @param lecturer the lecturer to insert.
     * @param conn     the DB connection to use.
     * @return true if the lecturer was inserted, false otherwise.
     * @throws SQLException if there's a DB access error.
     */
    private static boolean insertLecturerGenerateStaffNumber(Lecturer lecturer, Connection conn) throws SQLException {

        // The SQL here is the same as in insertLecturerUseExistingStaffNumber above, except that we're not providing
        // the staff number. However, we have provided an extra argument to the prepareStatement function -
        // Statement.RETURN_GENERATED_KEYS. Doing this will allow us to read the values of any primary keys that were
        // automatically generated by the database (see below).
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO unidb_lecturers (fname, lname, office) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, lecturer.getFirstName());
            stmt.setString(2, lecturer.getLastName());
            stmt.setString(3, lecturer.getOffice());

            // rowsAffected will be 1 if the insert was successful (because we're trying to insert 1 row), or 0 if it wasn't.
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
                int staffNo = keys.getInt(1);
                lecturer.setStaffNo(staffNo); // Set the lecturer's staff number to the one generated by the DB.

                return true;
            }

        }

    }

    /// ****************************************************** ///
    /// All code below this line is the same as in Example 02. ///
    /// ****************************************************** ///

    /**
     * Gets all {@link Lecturer}s in the database.
     *
     * @param conn the DB connection to use
     * @return a {@link List} of lecturer objects
     * @throws SQLException if there's an error connecting to the database, or an error with the SQL query.
     */
    public static List<Lecturer> getAllLecturers(Connection conn) throws SQLException {

        List<Lecturer> lecturers = new ArrayList<>();

        try (Statement stmt = conn.createStatement()) {

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM unidb_lecturers")) {

                while (rs.next()) {

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

        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT l.* FROM unidb_lecturers l, unidb_teach t WHERE l.staff_no = t.staff_no AND t.dept = ? AND t.num = ?")) {

            stmt.setString(1, courseDept);
            stmt.setInt(2, courseNum);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Lecturer lecturer = createLecturerFromResultSet(rs);
                    lecturers.add(lecturer);
                }

            }

        }

        return lecturers;
    }

}
