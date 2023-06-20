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
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.WindowConstants;

import com.school.quiz.model.Profile;

import com.school.quiz.model.QuizQuestionModel;
import com.school.quiz.view.Theme.Sidebar;

public class TeacherQuizPage extends JFrame {

    private JPanel currentPanel;
    private QuizQuestionPanel quizQuestionPanel;
    private String USERNAME;
    private int id;

    public TeacherQuizPage(String username, int ID) {
        this.id = ID;
        this.USERNAME = username;
        setTitle("Quiz Application");
        setSize(1600, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(35, 178, 161));

        JLabel applicationLabel = new JLabel("Quizzeria");
        applicationLabel.setBounds(800, 0, 500, 200);
        applicationLabel.setFont(new Font("Arial", Font.BOLD, 37));
        applicationLabel.setForeground(Color.WHITE);
        contentPanel.add(applicationLabel);

        ImageIcon icon = null;
        try {
            URL imageUrl = getClass().getResource("/com/school/quiz/assets/graduation hat.png");
            BufferedImage image = ImageIO.read(imageUrl);
            Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setBounds(700, 45, imageLabel.getPreferredSize().width, imageLabel.getPreferredSize().height);
            contentPanel.add(imageLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // getting quiz data

        QuizQuestionModel quizQuestionModel = new QuizQuestionModel();
        final ArrayList<String[]> quizData = quizQuestionModel.getQuizData();

        // getting profile data

        Profile profileData = new Profile(USERNAME);
        final ArrayList<String[]> userData = profileData.getUserData();

        quizQuestionPanel = new QuizQuestionPanel(quizData);

        // quiz panel

        final QuizView quiz = new QuizView(id);

        Sidebar sidebar = new Sidebar();
        sidebar.addButton("My Profile");
        sidebar.addButton("Quiz Questions");
        sidebar.addButton("Take Quiz");
        sidebar.addButton("Your Scores");
        sidebar.addButton("All Scores");
        sidebar.addButton("Log Out");

        sidebar.setTitle("Quizzeria");

        sidebar.setButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonText = ((JButton) e.getSource()).getText();
                System.out.println("Clicked: " + buttonText);

                if (currentPanel != null && currentPanel.getParent() != null) {
                    currentPanel.getParent().remove(currentPanel);
                }

                if (buttonText.equals("Quiz Questions")) {

                    contentPanel.add(quizQuestionPanel);
                    quizQuestionPanel.setVisible(true);
                    currentPanel = quizQuestionPanel;
                } else if (buttonText.equals("My Profile")) {
                    ProfileView profile = new ProfileView(userData);
                    currentPanel = profile;
                    contentPanel.add(currentPanel);
                } else if (buttonText.equals("Take Quiz")) {
                    currentPanel = quiz;
                    contentPanel.add(currentPanel);
                } else if (buttonText.equals("Log Out")) {
                    disposeWindow();
                    return;
                } else {
                    currentPanel = null;
                }

                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });

        sidebar.setBounds(20, 170, 200, getHeight() - 320);
        contentPanel.add(sidebar);
        contentPanel.add(quiz);

        getContentPane().add(contentPanel);

        currentPanel = quiz;
        currentPanel.setVisible(true);

        setVisible(true);
    }

    // logout
    private void disposeWindow() {
        new LoginView();
        this.dispose();
    }

    public static void main(String[] args) {
        new TeacherQuizPage("nilesh", 2);
    }
}
