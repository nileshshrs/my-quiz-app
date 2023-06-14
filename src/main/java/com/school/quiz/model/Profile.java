package com.school.quiz.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Profile {
     private ArrayList<String[]> userData;

    private String USERNAME;

    public Profile(String username) {

        userData = new ArrayList<>()    ;
        setusername(username);

        getProfile(USERNAME);
    }

    public void setusername(String username) {
        this.USERNAME = username;
    }

    public String username() {
        return USERNAME;
    }

        public ArrayList<String[]> getUserData() {
        return userData;
    }

    public void setUserData(ArrayList<String[]> userData) {
        this.userData = userData;
    }

    public void getProfile(String username) {

        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * from users where username = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quiz_application?user=root&password=SiberiaV2.0");
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("id");
                String first_name = rs.getString("firstname");
                String last_name = rs.getString("lastname");
                String user_name = rs.getString("username");
                String email = rs.getString("email");
                String role = rs.getString("role");
                String password = rs.getString("password");

                String Data[] = {
                        String.valueOf(ID),
                        first_name,
                        last_name,
                        user_name,
                        email,
                        role,
                        password
                };

                userData.add(Data);
                setUserData(userData);
            }


        } catch (ClassNotFoundException exception) {
            System.out.println("MySQL JDBC driver not found");
            exception.printStackTrace();
        } catch (SQLException exception) {
            System.out.println("Failed to connect to the database");
            exception.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException exception) {
                System.out.println("Failed to close the connection");
                exception.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Profile("avarittia");
    }
}
