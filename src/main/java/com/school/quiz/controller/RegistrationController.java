package com.school.quiz.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.school.quiz.model.Registration;
import com.school.quiz.view.LoginView;

public class RegistrationController implements ActionListener {
    private JTextField firstname;
    private JTextField lastname;
    private JTextField username;
    private JTextField email;
    private JTextField password;
    private JTextField confirmPassword;
    private JComboBox<String> roleComboBox;
    private JLabel errorLabel;

    public RegistrationController(JTextField firstname, JTextField lastname, JTextField username, JTextField email,
            JTextField password, JTextField confirmPassword, JComboBox<String> roleComboBox, JLabel errorLabel) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.roleComboBox = roleComboBox;
        this.errorLabel = errorLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Retrieve the values from the registration form
        String firstName = firstname.getText().toLowerCase();
        String lastName = lastname.getText().toLowerCase();
        String userName = username.getText().toLowerCase();
        String email = this.email.getText().toLowerCase();
        String password = this.password.getText();
        String confirmPassword = this.confirmPassword.getText();
        String role = roleComboBox.getSelectedItem().toString().toLowerCase();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quiz_application?user=root&password=SiberiaV2.0");

            statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            //creating a timer to remove errorlabel visibility
            final Timer timer = new Timer();
            if (rs.next()) {
                // Username already exists
                errorLabel.setText("Username already exists");
                errorLabel.setVisible(true);

                //timer function
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        errorLabel.setVisible(false);
                        timer.cancel();
                    }
                }, 7500);
                return;
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

        // Call the registration method with the collected data
        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || email.isEmpty() || password.isEmpty()
                || confirmPassword.isEmpty()) {
            System.out.println("Input fields cannot be empty");

            errorLabel.setText("All fields are required");
            errorLabel.setVisible(true);

            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    errorLabel.setVisible(false);
                    timer.cancel();
                }
            }, 7500);

        } else if (firstName.length() < 4 || lastName.length() < 4 || userName.length() < 4) {
            System.out.println("First name, last name, or username should be at least 4 characters");

            errorLabel.setText("First name, last name, or username should be at least 4 characters");
            errorLabel.setVisible(true);

            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    errorLabel.setVisible(false);
                    timer.cancel();
                }
            }, 7500);

        } else if (!isValidEmail(email)) {
            System.out.println("Email is invalid");

            errorLabel.setText("Invalid email address");
            errorLabel.setVisible(true);

            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    errorLabel.setVisible(false);
                    timer.cancel();
                }
            }, 7500);

        } else if (password.length() < 6) {
            System.out.println("Password should be at least 6 characters");

            errorLabel.setText("Password is too short, should be at least 6 characters");
            errorLabel.setVisible(true);

            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    errorLabel.setVisible(false);
                    timer.cancel();
                }
            }, 7500);

        } else if (!password.equals(confirmPassword)) {
            System.out.println("Password does not match");

            errorLabel.setText("Password does not match");
            errorLabel.setVisible(true);

            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    errorLabel.setVisible(false);
                    timer.cancel();
                }
            }, 7500);

        } else {

            try {
                // Create a connection to the database
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/quiz_application?user=root&password=SiberiaV2.0");

                statement = connection.prepareStatement(
                        "INSERT INTO users (firstname, lastname, username, email, password, role) VALUES (?, ?, ?, ?, ?, ?)");
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, userName);
                statement.setString(4, email);
                statement.setString(5, password);
                statement.setString(6, role);
                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0) {
                    // Registration successful
                    new Registration(firstName, lastName, userName, email, password, confirmPassword, role);

                    // Reset the form fields
                    firstname.setText("");
                    lastname.setText("");
                    username.setText("");
                    this.email.setText("");
                    this.password.setText("");
                    this.confirmPassword.setText("");
                    roleComboBox.setSelectedIndex(0);

                    // Show success message
                    errorLabel.setText("Registration successful");
                    errorLabel.setVisible(true);
                    errorLabel.setBackground(new Color(230, 255, 237)); // light green color
                    errorLabel.setForeground(new Color(0, 100, 0));

                    // Close the registration view after a delay
                    final Timer timer = new Timer();
                    final Component sourceComponent = (Component) e.getSource();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            errorLabel.setVisible(false);
                            timer.cancel();
                            // Close the registration view
                            new LoginView();
                            Window window = SwingUtilities.getWindowAncestor(sourceComponent);
                            window.dispose();
                        }
                    }, 5000);
                }
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
    }

    public static boolean isValidEmail(String email) {
        // Regular expression for validating email addresses
        String regex = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-z]{2,}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(email);

        // Check if the email matches the regular expression
        return matcher.matches();
    }
}
