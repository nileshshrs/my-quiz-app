package com.school.quiz.view;

import javax.swing.*;

import com.school.quiz.controller.QuizController;
import com.school.quiz.model.Quiz;
import com.school.quiz.view.Theme.GlassPanel;
import com.school.quiz.view.Theme.ModernButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QuizView extends GlassPanel {
    private JPanel subjectPanel;
    private ArrayList<JButton> quizButtons;

    private JLabel titleLabel, questionLabel, timerLabel, subjectTitleLabel; // Added timer label
    private JButton nextButton, prevButton;
    private JRadioButton optionButton1, optionButton2, optionButton3, optionButton4;
    private ButtonGroup buttonGroup;
    private int ID;

    public QuizView(int ID) {

        this.ID = ID;
        setLayout(null);
        setBounds(250, 170, 1300, 680);

        subjectPanel = new JPanel();
        subjectPanel.setLayout(null);
        subjectPanel.setBounds(0, 0, getWidth(), getHeight());
        subjectPanel.setBackground(new Color(116, 202, 192));
        subjectPanel.setVisible(true);

        add(subjectPanel);

        optionButton1 = new JRadioButton("Option 1");
        optionButton1.setBounds(300, 120, 1000, 30);
        optionButton1.setBackground(new Color(116, 202, 192));
        optionButton1.setEnabled(false);
        optionButton1.setFocusable(false);
        optionButton1.setRequestFocusEnabled(false);
        add(optionButton1);

        optionButton2 = new JRadioButton("Option 2");
        optionButton2.setBounds(300, 160, 1000, 30);
        optionButton2.setBackground(new Color(116, 202, 192));
        optionButton2.setEnabled(false);
        optionButton2.setFocusable(false);
        optionButton2.setRequestFocusEnabled(false);
        add(optionButton2);

        optionButton3 = new JRadioButton("Option 3");
        optionButton3.setBounds(300, 200, 1000, 30);
        optionButton3.setBackground(new Color(116, 202, 192));
        optionButton3.setEnabled(false);
        optionButton3.setFocusable(false);
        optionButton3.setRequestFocusEnabled(false);
        add(optionButton3);

        optionButton4 = new JRadioButton("Option 4");
        optionButton4.setBounds(300, 240, 1000, 30);
        optionButton4.setBackground(new Color(116, 202, 192));
        optionButton4.setEnabled(false);
        optionButton4.setFocusable(false);
        optionButton4.setRequestFocusEnabled(false);
        add(optionButton4);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(optionButton1);
        buttonGroup.add(optionButton2);
        buttonGroup.add(optionButton3);
        buttonGroup.add(optionButton4);

        quizButtons = new ArrayList<>();
        quizButtons.add(createQuizButton("JAVA", 200, 250));
        quizButtons.add(createQuizButton("Python", 650, 250));
        quizButtons.add(createQuizButton("JavaScript", 200, 400));
        quizButtons.add(createQuizButton("HTML and CSS", 650, 400));

        titleLabel = new JLabel("");
        titleLabel.setBounds(750, 30, 300, 40);
        add(titleLabel);

        subjectTitleLabel = new JLabel("Take Quiz !");
        subjectTitleLabel.setBounds(550, 70, 300, 40);
        subjectPanel.add(subjectTitleLabel);
        subjectTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        subjectTitleLabel.setForeground(Color.WHITE);

        questionLabel = new JLabel("This is a question label");
        questionLabel.setBounds(300, 70, 1000, 40);
        questionLabel.setBackground(Color.WHITE);
        add(questionLabel);

        timerLabel = new JLabel("Time: 20 seconds");
        timerLabel.setBounds(10, 60, 100, 20);
        add(timerLabel);

        nextButton = new ModernButton("Next");
        nextButton.setBounds(10, 10, 100, 40);
        add(nextButton);

        prevButton = new ModernButton("Previous");
        prevButton.setBounds(120, 10, 100, 40);
        add(prevButton);

        setVisible(false);
    }

    private JButton createQuizButton(String buttonText, int x, int y) {
        JButton button = new ModernButton(buttonText);
        button.setBounds(x, y, 400, 90);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JButton clickedButton = (JButton) e.getSource();
                String buttonText = clickedButton.getText();

                nextButton.setEnabled(true);
                nextButton.setText("Next");
                prevButton.setEnabled(true);
                titleLabel.setText(buttonText);

                // Retrieve quiz data based on the button text
                Quiz quiz = new Quiz(buttonText);
                if (quiz != null) {
                    ArrayList<String[]> quizData = quiz.getnewQuizData();
                    new QuizController(nextButton, prevButton, questionLabel, quizData, buttonGroup, optionButton1,
                            optionButton2, optionButton3, optionButton4, timerLabel, subjectPanel, ID);
                }
                optionButton1.setEnabled(true);
                optionButton1.setFocusable(true);
                optionButton1.setRequestFocusEnabled(true);

                optionButton2.setEnabled(true);
                optionButton2.setFocusable(true);
                optionButton2.setRequestFocusEnabled(true);

                optionButton3.setEnabled(true);
                optionButton3.setFocusable(true);
                optionButton3.setRequestFocusEnabled(true);

                optionButton4.setEnabled(true);
                optionButton4.setFocusable(true);
                optionButton4.setRequestFocusEnabled(true);

                setVisible(true);
                subjectPanel.setVisible(false);
            }
        });
        subjectPanel.add(button);
        return button;
    }

}
