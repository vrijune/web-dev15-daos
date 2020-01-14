package ictgradschool.web.jdbc.ex03;

import ictgradschool.Keyboard;
import ictgradschool.web.util.DBConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Ex03Main {

    public static final int INFO_BY_ACTOR = 1, INFO_BY_FILM = 2, INFO_BY_GENRE = 3, EXIT = 4;

    private void start() throws IOException, SQLException {

        boolean done = false;

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            while (!done) {

                int command = getCommand();
                switch (command) {
                    case INFO_BY_ACTOR:
                        informationByActor(conn);
                        break;

                        // TODO Exercise three step 4: Some code here

                    case EXIT:
                        done = true;
                        break;

                    default:
                        System.out.println("Unknown command, please try again!");
                        break;
                }

                System.out.println();

            }
        }

    }

    private int getCommand() {

        System.out.println("Please choose an option:");
        System.out.println("1. Information by actor");
        System.out.println("2. Information by film");
        // System.out.println("3. Information by genre");
        System.out.println("4. Exit");
        System.out.print("> ");

        String input = Keyboard.readInput();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }

    }

    private void informationByActor(Connection conn) throws SQLException {

        System.out.print("Please enter the actor's name (press ENTER to return to main menu): > ");
        String search = Keyboard.readInput();

        if (search.isEmpty()) {
            return;
        }

        // TODO Exercise three step 2: Complete this


    }

    // TODO Exercise three step 4: Some code here


    public static void main(String[] args) throws IOException, SQLException {
        new Ex03Main().start();
    }
}
