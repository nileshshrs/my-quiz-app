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
import javax.swing.*;

import com.school.quiz.view.Theme.ModernButton;

public class PassFailView extends JFrame {

    private JPanel contentPanel;
    private JLabel correctAnswerLabel, statusLabel, titleLabel;
    private JButton closeButton;

    public PassFailView(double percentage, int score, int totalQuestions, String passFailStatus) {

        System.out.println(score);
        setTitle("Quiz Status");
        setResizable(false);
        setSize(390, 590);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(35, 178, 161));
        contentPanel.setLayout(null);

        if (passFailStatus.equals("PASS")) {
            try {
                // Get the URL of the image
                URL imageUrl = getClass().getResource("/com/school/quiz/assets/Pass.png");

                // Read the image from the URL
                BufferedImage image = ImageIO.read(imageUrl);

                // Resize the image
                Image resizedImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);

                // Create an ImageIcon from the resized image
                ImageIcon icon = new ImageIcon(resizedImage);

                // Create a JLabel with the ImageIcon
                JLabel imageLabel = new JLabel(icon);

                // Set the bounds of the imageLabel to center it

                imageLabel.setBounds(65, 160, imageLabel.getPreferredSize().width,
                        imageLabel.getPreferredSize().height);

                // Add the imageLabel to the left panel
                contentPanel.setLayout(null); // Set the layout manager to null to use absolute positioning
                contentPanel.add(imageLabel);
            } catch (IOException e) {
                // handle the exception
                e.printStackTrace();
            }
        } else {
            try {
                // Get the URL of the image
                URL imageUrl = getClass().getResource("/com/school/quiz/assets/Fail.png");

                // Read the image from the URL
                BufferedImage image = ImageIO.read(imageUrl);

                // Resize the image
                Image resizedImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);

                // Create an ImageIcon from the resized image
                ImageIcon icon = new ImageIcon(resizedImage);

                // Create a JLabel with the ImageIcon
                JLabel imageLabel = new JLabel(icon);

                // Set the bounds of the imageLabel to center it

                imageLabel.setBounds(85, 160, imageLabel.getPreferredSize().width,
                        imageLabel.getPreferredSize().height);

                // Add the imageLabel to the left panel
                contentPanel.setLayout(null); // Set the layout manager to null to use absolute positioning
                contentPanel.add(imageLabel);
            } catch (IOException e) {
                // handle the exception
                e.printStackTrace();
            }
        }

        titleLabel = new JLabel("RESULT");
        titleLabel.setBounds(10, 30, 370, 40);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        contentPanel.add(titleLabel);

        correctAnswerLabel = new JLabel();
        correctAnswerLabel.setBounds(10, 400, 370, 40);
        correctAnswerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        correctAnswerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        correctAnswerLabel.setForeground(Color.WHITE);
        contentPanel.add(correctAnswerLabel);

        statusLabel = new JLabel("PASS: 75%");
        statusLabel.setBounds(10, 100, 370, 60);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 30));
        statusLabel.setForeground(Color.WHITE);
        contentPanel.add(statusLabel);

        closeButton = new ModernButton("OK");
        closeButton.setBounds(50, 475, 280, 35);
        closeButton.setBackground(new Color(64, 144, 128));
        contentPanel.add(closeButton);

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

        correctAnswerLabel.setText("you got " + score + " out of " + totalQuestions + " correct");
        statusLabel.setText(passFailStatus + ": " + percentage + "%");

        getContentPane().add(contentPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new PassFailView(75.0, 15, 20, "PASS");

    }
}
