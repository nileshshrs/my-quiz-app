package com.school.quiz.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registration {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String role;

    public Registration(String firstName, String lastName, String username, String email, String password, String confirmPassword, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;

        registerUser(firstName, lastName, username, email, confirmPassword, role);
    }

    // Getters and setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    public void registerUser(String firstName, String lastName, String username, String email, String password, String role) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quiz_application?user=root&password=SiberiaV2.0");

            // Create table if it doesn't exist
            statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users("
                    + "id INT(11) NOT NULL AUTO_INCREMENT,"
                    + "firstname VARCHAR(255) NOT NULL,"
                    + "lastname VARCHAR(255) NOT NULL,"
                    + "username VARCHAR(255) NOT NULL UNIQUE,"
                    + "email VARCHAR(255) NOT NULL,"
                    + "role VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "PRIMARY KEY (id)"
                    +")"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;"
            );
            statement.executeUpdate();

            // Insert data into table
            statement = connection.prepareStatement(
                    "INSERT INTO users (firstname, lastname, username, email, password, role) "
                            + "VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.setString(6, role);
            statement.executeUpdate();

            System.out.println("Inserted data into the database!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close the connection");
                e.printStackTrace();
            }
        }
    }
}
