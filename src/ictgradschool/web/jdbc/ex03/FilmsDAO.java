package ictgradschool.web.jdbc.ex03;

import ictgradschool.web.jdbc.ex01.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.tools.doclint.Entity.or;

public class FilmsDAO {

    public static List<RoleInfo> getRoleInfoByActor(String search, Connection conn) throws SQLException {

        List<RoleInfo> infos = new ArrayList<>();

        // TODO Exercise three step 1: Complete this

//        infos = "%" + infos + "%";
//        "SELECT  p.film_id, a.actor_fname, a.actor_lname, p.role_id " +
//                "from pfilms_actor as a, pfilms_participates_in as p, pfilms_film as f ,pfilms_role as r  " +
//                "where a.actor_id = p.actor_id. f.film_id = p.film_id" +
        try (PreparedStatement statement = conn.prepareStatement(
                "SELECT  f.film_title, a.actor_fname, a.actor_lname, r.role_name " +
                        "from pfilms_actor as a, pfilms_participates_in as p,  pfilms_film as f ,pfilms_role as r " +
                        "where a.actor_id = p.actor_id " +
                        "or  f.film_id = p.film_id " +
                        "or  r.role_id = p.role_id " +
                        "AND  a.actor_fname = ? " +
                        "or a.actor_lname = ? "
        )) {
            statement.setString(1, search);
            statement.setString(2, search);


            try (ResultSet rs = statement.executeQuery()) {
                while (!rs.next()) {
                    return null;
                }
                RoleInfo roleInfo = createRoleInfoFromResultSet(rs);
                infos.add(roleInfo);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return infos;

    }


    private static RoleInfo createRoleInfoFromResultSet(ResultSet resultSet) throws SQLException {

        RoleInfo roleInfo = new RoleInfo(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4)


        );

        return roleInfo;

    }


    // TODO Exercise three step 3: Write a getRoleInfoByFilm method


    public static List<RoleInfo> getRoleInfoByFilm(String search, Connection conn) throws SQLException {

        List<RoleInfo> infos = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(
                "SELECT  f.film_title, a.actor_fname, a.actor_lname, r.role_name " +
                        "from pfilms_actor as a, pfilms_participates_in as p,  pfilms_film as f ,pfilms_role as r " +
                        "where a.actor_id = p.actor_id " +
                        "or  f.film_id = p.film_id " +
                        "or  r.role_id = p.role_id " +
                        "AND  f.film_title = ? "

        )) {
            statement.setString(1, search);


            try (ResultSet rs = statement.executeQuery()) {
                while (!rs.next()) {
                    return null;
                }
                RoleInfo roleInfo = createRoleInfoForMovieTitle(rs);
                infos.add(roleInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return infos;


    }


    private static RoleInfo createRoleInfoForMovieTitle(ResultSet resultSet) throws SQLException {

        RoleInfo roleInfo2 = new RoleInfo(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4)


        );

        return roleInfo2;

    }

}

