package com.school.quiz.controller;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.school.quiz.model.Login;

import java.sql.*;
import java.util.ArrayList;

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

        if (userName.isEmpty() || Password.isEmpty()) {
            errorLabel.setText("username or password is empty");
            errorLabel.setVisible(true);
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_application", "root",
                    "SiberiaV2.0");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM users WHERE username='" + userName + "' AND password='" + Password + "'");

            if (rs.next()) {
                // Login successful
                username.setText("");
                password.setText("");
                errorLabel.setText("login successful");
                errorLabel.setVisible(true);
                errorLabel.setBackground(new Color(230, 255, 237)); // light green color
                errorLabel.setForeground(new Color(0, 100, 0));

                rs.beforeFirst();
                ArrayList<String[]> userData = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");

                    String[] rowData = { String.valueOf(id), firstname, lastname, username, email, password, role };
                    userData.add(rowData);
                }

                Login login = new Login();
                login.setUserData(userData);
                login.loginWithRole();
   
                ((Window) ((JComponent) e.getSource()).getTopLevelAncestor()).dispose();

                // TODO: Do something after successful login, such as opening a new window
            } else {
                // Login failed
                errorLabel.setText("Incorrect username or password");
                errorLabel.setVisible(true);
            }

            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
