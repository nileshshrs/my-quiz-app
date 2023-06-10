package com.school.quiz.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
// import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXLabel;
// import org.jdesktop.swingx.JXTextField;

import com.school.quiz.controller.LoginController;
import com.school.quiz.view.Theme.ModernButton;
import com.school.quiz.view.Theme.ModernPasswordField;
import com.school.quiz.view.Theme.ModernTextField;
import com.school.quiz.view.Theme.TransparentButton;

public class LoginView extends JFrame {

    private JPanel panel;

    public LoginView() {
        setTitle("Login");
        setResizable(false);
        setSize(420, 650);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setBackground(new Color(35, 178, 161));
        panel.setLayout(null); // Set layout manager to null

        // add image icon
        try {
            // Get the URL of the image
            URL imageUrl = getClass().getResource("/com/school/quiz/assets/graduation hat.png");

            // Read the image from the URL
            BufferedImage image = ImageIO.read(imageUrl);

            // Resize the image
            Image resizedImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

            // Create an ImageIcon from the resized image
            ImageIcon icon = new ImageIcon(resizedImage);

            // Create a JLabel with the ImageIcon
            JLabel imageLabel = new JLabel(icon);

            // Set the bounds of the imageLabel to center it

            imageLabel.setBounds(100, 20, imageLabel.getPreferredSize().width, imageLabel.getPreferredSize().height);

            // Add the imageLabel to the left panel
            panel.setLayout(null); // Set the layout manager to null to use absolute positioning
            panel.add(imageLabel);
        } catch (IOException e) {
            // handle the exception
            e.printStackTrace();
        }

        JXLabel TitleLabel = new JXLabel("Quizzeria");
        panel.add(TitleLabel);
        TitleLabel.setBounds(55, 180, 280, 40);
        TitleLabel.setForeground(Color.WHITE);
        TitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(new Color(255, 0, 0));
        errorLabel.setBackground(Color.PINK);
        errorLabel.setOpaque(true);
        errorLabel.setVisible(false);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setBounds(65, 230, 280, 25);
        panel.add(errorLabel);

        final JTextField username = new ModernTextField("username", Color.white,
                Color.white, Color.white, Color.white);
        username.setBounds(65, 270, 280, 35); // Set the bounds of the text field
        panel.add(username);
        // final JXTextField username = new JXTextField();
        // username.setPrompt("username");
        // username.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        // username.setOpaque(false);

        final JTextField password = new ModernPasswordField("password");
        password.setBounds(65, 340, 280, 35);
        panel.add(password);

        JButton forgotPasswordButton = new TransparentButton("Forgot Password ?");
        forgotPasswordButton.setBounds(55, 385, 150, 35);
        forgotPasswordButton.setFont(new Font("Arial", Font.BOLD, 13));
        forgotPasswordButton.setBackground(new Color(64, 144, 128, 0));
        forgotPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // close the current window
                // open the login window
            }
        });
        panel.add(forgotPasswordButton);

        JButton submitButton = new ModernButton("login");
        submitButton.setBounds(65, 450, 280, 35);
        submitButton.setBackground(new Color(64, 144, 128));
        panel.add(submitButton);
        submitButton.addActionListener(
                new LoginController(username, password, errorLabel));

        JLabel splitButtonLabel = new JLabel("OR");
        splitButtonLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        splitButtonLabel.setBounds(190, 500, 50, 25);
        splitButtonLabel.setForeground(Color.white);
        panel.add(splitButtonLabel);

        JButton navigateButton = new ModernButton("sign up");
        navigateButton.setBounds(65, 540, 280, 35);
        panel.add(navigateButton);
        navigateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // close the current window
                new RegistrationView(); // open the login window
            }
        });

        getContentPane().add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginView();
    }
}
