package com.school.quiz.controller;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.school.quiz.model.Login;

public class LoginController implements ActionListener {
    private JTextField username;
    private JTextField password;
    private JLabel errorLabel;

    public LoginController(JTextField username, JTextField password, JLabel errorLabel) {
        this.username = username;
        this.password = password;
        this.errorLabel = errorLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = username.getText().toLowerCase();
        String Password = password.getText();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_application", "root",
                    "SiberiaV2.0");
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, Password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Login successful
                username.setText("");
                password.setText("");
                errorLabel.setText("Login successful");
                errorLabel.setVisible(true);
                errorLabel.setBackground(new Color(230, 255, 237)); // light green color
                errorLabel.setForeground(new Color(0, 100, 0));

                ArrayList<String[]> userData = new ArrayList<>(); // Create a new ArrayList

                do {
                    int id = rs.getInt("id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String retrievedUsername = rs.getString("username");
                    String email = rs.getString("email");
                    String retrievedPassword = rs.getString("password");
                    String role = rs.getString("role");

                    String[] rowData = { String.valueOf(id), firstname, lastname, retrievedUsername, email, retrievedPassword, role };
                    userData.add(rowData);
                } while (rs.next());

                new Login(userData);
                ((Window) ((JComponent) e.getSource()).getTopLevelAncestor()).dispose();


                // TODO: Do something after successful login, such as opening a new window
            } else {
                // Login failed
                errorLabel.setText("Invalid username or password");
                errorLabel.setVisible(true);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
