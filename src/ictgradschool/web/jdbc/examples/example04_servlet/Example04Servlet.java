package ictgradschool.web.jdbc.examples.example04_servlet;

import ictgradschool.web.jdbc.examples.Lecturer;
import ictgradschool.web.jdbc.examples.example03_modifications.Example03LecturerDAO;
import ictgradschool.web.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * An example Servlet showing how DB access can be done from a servlet.
 *
 * @author Andrew Meads
 */
@WebServlet(name = "example10", urlPatterns = { "/example" })
public class Example04Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Good idea to establish the DB connection when you need it, rather than in the init() method, for example.
        // It is bad practice to establish a connection when a servlet starts and leave it open - the connection may go
        // "stale" which leads to connection issues.

        // We can use the same method of establishing a DB connection as in examples 01 - 03.
        // You may also try copying the properties file into the WEB-INF folder, and using the getConnectionFromWebInf
        // method instead, if you like.
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            // easy to use your DAOs just like in a console application.
            List<Lecturer> allLecturers = Example03LecturerDAO.getAllLecturers(conn);

            req.setAttribute("lecturers", allLecturers);
            req.getRequestDispatcher("WEB-INF/example04-page.jsp").forward(req, resp);

        } catch (SQLException e) {

            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);

        }

    }
}
