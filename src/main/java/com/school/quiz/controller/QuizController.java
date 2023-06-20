// QuizController.java
package com.school.quiz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class QuizController {
    private JButton nextButton, prevButton;
    private JLabel questionLabel, timerLabel;
    private ArrayList<String[]> quizData;
    private JRadioButton optionButton1, optionButton2, optionButton3, optionButton4;
    private ButtonGroup buttonGroup;
    private int score, remainingTime, currentIndex, ID;
    private Timer timer;
    private JPanel subjectPanel;

    public QuizController(JButton nextButton, JButton prevButton, JLabel questionLabel, ArrayList<String[]> quizData,
            ButtonGroup buttonGroup, JRadioButton optionButton1, JRadioButton optionButton2, JRadioButton optionButton3,
            JRadioButton optionButton4, JLabel timerLabel, JPanel subjectPanel, int ID) {
        this.nextButton = nextButton;
        this.prevButton = prevButton;
        this.questionLabel = questionLabel;
        this.quizData = quizData;
        this.currentIndex = 0;
        this.buttonGroup = buttonGroup;
        this.optionButton1 = optionButton1;
        this.optionButton2 = optionButton2;
        this.optionButton3 = optionButton3;
        this.optionButton4 = optionButton4;
        this.score = 0;
        this.timerLabel = timerLabel;
        this.remainingTime = 20;
        this.subjectPanel = subjectPanel;
        this.ID = ID;

        updateQuizView();
        setButtonActions();
        startTimer(); // Start the timer
    }

    private void updateQuizView() {
        if (currentIndex >= 0 && currentIndex < quizData.size()) {
            String[] currentQuestion = quizData.get(currentIndex);
            questionLabel.setText(currentQuestion[1]);
            optionButton1.setText(currentQuestion[2]);
            optionButton2.setText(currentQuestion[3]);
            optionButton3.setText(currentQuestion[4]);
            optionButton4.setText(currentQuestion[5]);
            clearSelection();
            // System.out.println(ID);
        }
    }

    private void clearSelection() {
        buttonGroup.clearSelection();
    }

    private void setButtonActions() {
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentIndex++;
                if (currentIndex >= quizData.size()) {

                    showScore();
                    remainingTime= 20;
                    subjectPanel.setVisible(true);
                    currentIndex = 0;
                    timer.restart();

                } else {
                    updateQuizView();
                }
            }
        });

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex--;
                if (currentIndex < 0) {
                    currentIndex = 0;
                }
                updateQuizView();

            }
        });
    }

    private void checkAnswer() {
        String[] currentQuestion = quizData.get(currentIndex);
        String correctAnswer = currentQuestion[6];
        String selectedAnswer = getSelectedAnswer();
        if (correctAnswer.equals(selectedAnswer)) {
            score++;
            System.out.println(score);
        }
    }

    private String getSelectedAnswer() {
        Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        while (buttons.hasMoreElements()) {
            JRadioButton button = (JRadioButton) buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private void showScore() {
        System.out.println("Quiz Completed! Score: " + score + "/" + quizData.size());
        questionLabel.setText("Quiz Completed! Score: " + score + "/" + quizData.size());
        nextButton.setEnabled(false);
        prevButton.setEnabled(false);
        optionButton1.setEnabled(false);
        optionButton1.setFocusable(false);
        optionButton1.setRequestFocusEnabled(false);

        optionButton2.setEnabled(false);
        optionButton2.setFocusable(false);
        optionButton2.setRequestFocusEnabled(false);

        optionButton3.setEnabled(false);
        optionButton3.setFocusable(false);
        optionButton3.setRequestFocusEnabled(false);

        optionButton4.setEnabled(false);
        optionButton4.setFocusable(false);
        optionButton4.setRequestFocusEnabled(false);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                if (remainingTime >= 0) {
                    timerLabel.setText("Time: " + remainingTime + " seconds");

                } else {
                    showScore();

                    subjectPanel.setVisible(true);
                    currentIndex = 0;
                    timer.stop();
                    timerLabel.setText("Time's up!");

                    // Call any method or perform any action when the timer ends
                }
            }
        });
        timer.start();
    }

}
