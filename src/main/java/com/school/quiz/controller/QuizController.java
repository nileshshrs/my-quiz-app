package com.school.quiz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.Timer;

import com.school.quiz.view.PassFailView;

public class QuizController {

    JButton nextButton, prevButton;
    JLabel questionLabel, timerLabel;
    int CurrentIndex, ID, remainingTime, score;
    JRadioButton optionButton1, optionButton2, optionButton3, optionButton4;
    ButtonGroup buttonGroup;
    ArrayList<String[]> quizData;
    JPanel subjectPanel;
    Timer timer;
    double percentage;
    JProgressBar progressBar;
    String[] currentQuestion;

    public QuizController(JButton nextButton, JButton prevButton, JLabel questionLabel, ArrayList<String[]> quizData,
            ButtonGroup buttonGroup, JRadioButton optionButton1, JRadioButton optionButton2, JRadioButton optionButton3,
            JRadioButton optionButton4, JLabel timerLabel, JPanel subjectPanel, int ID, JProgressBar progressBar) {

        Collections.shuffle(quizData);
        this.nextButton = nextButton;
        this.prevButton = prevButton;
        this.questionLabel = questionLabel;
        this.quizData = quizData;
        this.buttonGroup = buttonGroup;
        this.optionButton1 = optionButton1;
        this.optionButton2 = optionButton2;
        this.optionButton3 = optionButton3;
        this.optionButton4 = optionButton4;
        this.timerLabel = timerLabel;
        this.subjectPanel = subjectPanel;
        this.ID = ID;
        CurrentIndex = 0;
        remainingTime = 600;
        score = 0;
        this.progressBar = progressBar;

        updateQuizView();
        setButtonActions();
        startTimer();

    }

    public void updateQuizView() {

        if (CurrentIndex >= 0 && CurrentIndex < quizData.size()) {
            currentQuestion = quizData.get(CurrentIndex);
            questionLabel.setText("<html>" + currentQuestion[0] + "</html>");
            optionButton1.setText(currentQuestion[1]);
            optionButton2.setText(currentQuestion[2]);
            optionButton3.setText(currentQuestion[3]);
            optionButton4.setText(currentQuestion[4]);
            clearSelection();

        }

        if (CurrentIndex == quizData.size() - 1) {
            nextButton.setText("Submit");
        } else {
            nextButton.setText("Next");
        }

        // Update the progress bar
        int progress = calculateProgress();
        progressBar.setValue(progress);
    }

    private void clearSelection() {
        buttonGroup.clearSelection();
    }

    private void setButtonActions() {
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                if (CurrentIndex < quizData.size() - 1) {
                    CurrentIndex++;
                    updateQuizView();
                } else if (CurrentIndex == quizData.size() - 1) {
                    showScore();

                    subjectPanel.setVisible(true);
                    CurrentIndex = 0;
                    timer.stop();

                }
            }
        });
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentIndex--;
                if (CurrentIndex < 0) {
                    CurrentIndex = 0;
                }
                updateQuizView();
            }
        });
    }

    private void checkAnswer() {
        // Check if quizData is not empty
        if (CurrentIndex >= 0 && CurrentIndex < quizData.size()) {
            String[] currentQuestion = quizData.get(CurrentIndex);
            String correctAnswer = currentQuestion[5];
            String selectedAnswer = getSelectedAnswer();
            if (correctAnswer.equals(selectedAnswer)) {
                score++;
            }
        } else {
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

        double percentage = (score * 1.0 / quizData.size()) * 100;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedPercentage = decimalFormat.format(percentage);

        String passFailStatus = (percentage >= 40.0 ? "PASS" : "FAIL");

        new PassFailView(Double.parseDouble(formattedPercentage), score, quizData.size(), passFailStatus);
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

        quizData.clear();

    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                if (remainingTime >= 0) {
                    String formattedTime = formatTime(remainingTime);
                    timerLabel.setText("Time: " + formattedTime);
                } else {
                    checkAnswer();
                    showScore();
                    subjectPanel.setVisible(true);
                    CurrentIndex = 0;
                    timer.stop();
                    timerLabel.setText("Time's up!");
                    // Call any method or perform any action when the timer ends
                }
            }
        });
        timer.start();
    }

    private String formatTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private int calculateProgress() {
        double progress = (CurrentIndex + 1.0) / quizData.size() * 100;
        return (int) progress;
    }

    // timer
}