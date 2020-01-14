package ictgradschool.web.jdbc.examples.example03_modifications;

import ictgradschool.web.jdbc.examples.Lecturer;
import ictgradschool.web.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Example03Main {

    private void start() {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            // Add a new lecturer with a custom ID
            Lecturer andrew = new Lecturer(12345, "Andrew", "Meads", "303-520");
            boolean success = Example03LecturerDAO.insertLecturer(andrew, conn);
            System.out.println("Added Andrew as a lecturer? " + success);

            // Add a new lecturer with an ID that was generated by the database.
            Lecturer yuCheng = new Lecturer("Yu-Cheng", "Tu", "303-517");
            success = Example03LecturerDAO.insertLecturer(yuCheng, conn);
            System.out.println("Added Yu-Cheng as a lecturer? " + success);
            System.out.println("Yu-Cheng's staff number (generated by DB): " + yuCheng.getStaffNo());

            System.out.println();

            // Print all the lecturers so we can see Andrew and Yu-Cheng in the database
            printAllLecturers(conn);

            System.out.println();

            // Modify the details of a lecturer, and update the database
            andrew.setOffice("International Space Station");
            success = Example03LecturerDAO.updateLecturer(andrew, conn);
            System.out.println("Updated Andrew's office? " + success);

            // Print all the lecturers so we can see Andrew's new office has been stored in the DB
            printAllLecturers(conn);

            System.out.println();

            // Delete Andrew and Yu-Cheng from the database
            success = Example03LecturerDAO.deleteLecturer(andrew.getStaffNo(), conn);
            System.out.println("Deleted Andrew? " + success);
            success = Example03LecturerDAO.deleteLecturer(yuCheng.getStaffNo(), conn);
            System.out.println("Deleted Yu-Cheng? " + success);

            System.out.println();

            // Print all the lecturers so we can see the lecturers have been deleted from the DB
            printAllLecturers(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Utility method which prints all lecturers in the database.
     */
    private void printAllLecturers(Connection conn) throws SQLException {
        List<Lecturer> allLecturers = Example03LecturerDAO.getAllLecturers(conn);
        System.out.println("All lecturers:");
        for(Lecturer l : allLecturers) {
            System.out.println(l);
        }
    }

    public static void main(String[] args) {
        new Example03Main().start();
    }

}