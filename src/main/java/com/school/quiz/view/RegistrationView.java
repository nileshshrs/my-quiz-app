package com.school.quiz.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.school.quiz.controller.RegistrationController;
import com.school.quiz.view.Theme.ModernButton;
import com.school.quiz.view.Theme.ModernPasswordField;
import com.school.quiz.view.Theme.ModernTextField;

public class RegistrationView extends JFrame {

   private JPanel leftPanel, rightPanel;

   public RegistrationView() {

      setTitle("Registration");
      setSize(1100, 800);
      setResizable(false);
      // setting window always at the center of the screen
      setLocationRelativeTo(null);

      // create left panel
      leftPanel = new JPanel();
      leftPanel.setBackground(new Color(35, 178, 161));
      ;

      // add image icon
      try {
         // Get the URL of the image
         URL imageUrl = getClass().getResource("/com/school/quiz/assets/graduation hat.png");

         // Read the image from the URL
         BufferedImage image = ImageIO.read(imageUrl);

         // Resize the image
         Image resizedImage = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);

         // Create an ImageIcon from the resized image
         ImageIcon icon = new ImageIcon(resizedImage);

         // Create a JLabel with the ImageIcon
         JLabel imageLabel = new JLabel(icon);

         // Set the bounds of the imageLabel to center it

         imageLabel.setBounds(50, 170, imageLabel.getPreferredSize().width, imageLabel.getPreferredSize().height);

         // Add the imageLabel to the left panel
         leftPanel.setLayout(null); // Set the layout manager to null to use absolute positioning
         leftPanel.add(imageLabel);
      } catch (IOException e) {
         // handle the exception
         e.printStackTrace();
      }

      // ImageIcon imageIcon = new
      // ImageIcon(getClass().getResource("/com/school/quiz/assets/graduation
      // hat.png"));
      // JLabel imageLabel = new JLabel(imageIcon);
      // leftPanel.add(imageLabel);

      // create right panel
      rightPanel = new JPanel();
      rightPanel.setBackground(new Color(35, 178, 161));
      rightPanel.setLayout(null);

      // adding title label
      JLabel titleLabel = new JLabel("Sign Up...!");
      titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
      titleLabel.setForeground(Color.WHITE);
      titleLabel.setBounds(75, 25, 500, 100); // Increase label height to 100 pixels
      titleLabel.setPreferredSize(new Dimension(500, 100)); // Increase preferred size
      rightPanel.add(titleLabel);

      // adding fields in right panel

      // adding error label
      JLabel errorLabel = new JLabel("");
      errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
      errorLabel.setForeground(new Color(255, 0, 0));
      errorLabel.setBackground(Color.PINK);
      errorLabel.setOpaque(true);
      errorLabel.setVisible(false);
      errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
      errorLabel.setBounds(80, 120, 420, 25);
      rightPanel.add(errorLabel);

      // firsname
      final JTextField firstname = new ModernTextField("first name", Color.white, Color.white, Color.white,
            Color.white);
      firstname.setBounds(80, 175, 200, 35);
      rightPanel.add(firstname);

      // lastname
      final JTextField lastname = new ModernTextField("last name", Color.white, Color.white, Color.white, Color.white);
      lastname.setBounds(300, 175, 200, 35);
      rightPanel.add(lastname);

      // username
      final JTextField username = new ModernTextField("username", Color.white, Color.white, Color.white, Color.white);
      username.setBounds(80, 250, 200, 35);
      rightPanel.add(username);

      // email
      final JTextField email = new ModernTextField("email: example@example.com", Color.white, Color.white, Color.white,
            Color.white);
      email.setBounds(80, 325, 420, 35);
      rightPanel.add(email);

      // password
      final JTextField password = new ModernPasswordField("password");
      password.setBounds(80, 400, 420, 35);
      rightPanel.add(password);

      // confirm-password
      final JTextField confirmPassword = new ModernPasswordField("confirm-password");
      confirmPassword.setBounds(80, 475, 420, 35);
      rightPanel.add(confirmPassword);

      // role
      JLabel roleLabel = new JLabel("Select role:");
      roleLabel.setFont(new Font("Arial", Font.BOLD, 16));
      roleLabel.setForeground(Color.WHITE);
      roleLabel.setBounds(300, 265, 100, 25);
      rightPanel.add(roleLabel);

      String[] roles = { "Student", "Teacher" };
      final JComboBox<String> roleComboBox = new JComboBox<>(roles);
      roleComboBox.setBounds(400, 265, 100, 25);
      rightPanel.add(roleComboBox);

      // button

      JButton submitButton = new ModernButton("sign up");
      submitButton.setBounds(80, 550, 420, 40);
      submitButton.setBackground(new Color(64, 144, 128));
      rightPanel.add(submitButton);
      submitButton.addActionListener(
            new RegistrationController(firstname, lastname, username, email, password, confirmPassword, roleComboBox,
                  errorLabel));

      JLabel splitButtonLabel = new JLabel("OR");
      splitButtonLabel.setFont(new Font("Arial", Font.PLAIN, 16));
      splitButtonLabel.setBounds(275, 615, 50, 25);
      splitButtonLabel.setForeground(Color.white);
      rightPanel.add(splitButtonLabel);

      JButton navigateButton = new ModernButton("Login");
      navigateButton.setBounds(80, 660, 420, 40);
      rightPanel.add(navigateButton);
      navigateButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose(); // close the current window
            new LoginView(); // open the login window
         }
      });

      // splitting the panels
      JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
      splitPane.setDividerSize(0);
      splitPane.setResizeWeight(.4);

      // add panes to jframe
      getContentPane().add(splitPane);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setVisible(true);

   }
}
