package com.school.quiz.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.school.quiz.model.QuizQuestionModel;
import com.school.quiz.view.Theme.Sidebar;

public class TeacherQuizPage extends JFrame {

    private JPanel currentPanel;
    private QuizQuestionPanel quizQuestionPanel; // Moved outside the constructor
  

    public TeacherQuizPage(String username) {
        // System.out.println(username);
        setTitle("Quiz Application");
        setSize(1600, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(35, 178, 161));

        JLabel applicationLabel = new JLabel("Quizzeria");
        applicationLabel.setBounds(750, 0, 500, 200);
        applicationLabel.setFont(new Font("Arial", Font.BOLD, 37));
        applicationLabel.setForeground(Color.white);
        contentPanel.add(applicationLabel);

        ImageIcon icon = null;
        try {
            URL imageUrl = getClass().getResource("/com/school/quiz/assets/graduation hat.png");
            BufferedImage image = ImageIO.read(imageUrl);
            Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setBounds(650, 45, imageLabel.getPreferredSize().width, imageLabel.getPreferredSize().height);
            contentPanel.add(imageLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        QuizQuestionModel quizQuestionModel = new QuizQuestionModel();
        ArrayList<String[]> quizData = quizQuestionModel.getQuizData();

        quizQuestionPanel = new QuizQuestionPanel(quizData); // Initialize the quizQuestionPanel

        // profile panel
        final ProfileView profile = new ProfileView();

        Sidebar sidebar = new Sidebar();
        sidebar.addButton("My Profile");
        sidebar.addButton("Quiz Questions");
        sidebar.addButton("Take Quiz");
        sidebar.addButton("Your Scores");
        sidebar.addButton("All Scores");
        sidebar.addButton("logout");

        sidebar.setTitle("Quizzeria");

        sidebar.setButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonText = ((JButton) e.getSource()).getText();
                System.out.println("Clicked: " + buttonText);

                // Remove current panel from its parent container
                if (currentPanel != null && currentPanel.getParent() != null) {
                    currentPanel.getParent().remove(currentPanel);
                }

                // Show new panel
                if (buttonText.equals("Quiz Questions")) {
                    contentPanel.add(quizQuestionPanel); // Add the panel back to the parent container
                    quizQuestionPanel.setVisible(true);
                    currentPanel = quizQuestionPanel;
                } else if (buttonText.equals("My Profile")) {
                    // Add code for My Profile panel
                    currentPanel = profile;
                    contentPanel.add(currentPanel);

                } else {
                    currentPanel = null;
                    // Add code for other panels
                }

                // Repaint the parent container to reflect the changes
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });

        sidebar.setBounds(20, 170, 200, getHeight() - 320);
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
