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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.school.quiz.view.Theme.Sidebar;

public class TeacherQuizPage extends JFrame {

    private JPanel currentPanel;


    public TeacherQuizPage(String username) {


        System.out.println(username);
        setTitle("Quiz Application");
        setSize(1600, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(35, 178, 161));

        JLabel applicationLabel = new JLabel("Quizzeria");
        applicationLabel.setBounds(750, 0, 500, 200);
        applicationLabel.setFont(new Font("Arial", Font.BOLD, 37));
        applicationLabel.setForeground(Color.white);
        contentPanel.add(applicationLabel);

        try {
            URL imageUrl = getClass().getResource("/com/school/quiz/assets/graduation hat.png");
            BufferedImage image = ImageIO.read(imageUrl);
            Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setBounds(650, 45, imageLabel.getPreferredSize().width, imageLabel.getPreferredSize().height);
            contentPanel.add(imageLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final QuizQuestionPanel quizQuestionPanel = new QuizQuestionPanel();
        quizQuestionPanel.setBounds(350, 170, 1100, 650);

        Sidebar sidebar = new Sidebar();
        sidebar.addButton("My Profile");
        sidebar.addButton("Quiz Questions");
        sidebar.addButton("");
        sidebar.setButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonText = ((JButton) e.getSource()).getText();
                System.out.println("Clicked: " + buttonText);

                // Hide current panel
                if (currentPanel != null) {
                    currentPanel.setVisible(false);
                }

                // Show new panel
                if (buttonText.equals("Quiz Questions")) {
                    quizQuestionPanel.setVisible(true);
                    currentPanel = quizQuestionPanel;
                } else if (buttonText.equals("My Profile")) {
                    quizQuestionPanel.setVisible(true);
                    currentPanel = quizQuestionPanel;
                }
                else{
                    currentPanel = null;
                    quizQuestionPanel.setVisible(false);
                }
            }
        });

        sidebar.setBounds(40, 40, 200, getHeight() - 200);
        contentPanel.add(sidebar);
        contentPanel.add(quizQuestionPanel);

        getContentPane().add(contentPanel);

        // Set quiz panel as the default panel
        currentPanel = quizQuestionPanel;
        currentPanel.setVisible(true);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TeacherQuizPage("nilesh");
    }

}