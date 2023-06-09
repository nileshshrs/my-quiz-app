package com.school.quiz.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.school.quiz.model.AddQuizQuestions;
import com.school.quiz.view.LoginView;

public class AddQuestionController implements ActionListener {
    private JTextField questionField;
    private JTextField optionField1;
    private JTextField optionField2;
    private JTextField optionField3;
    private JTextField optionField4;
    private JTextField correctAnswerField;
    private String selectedSubject;
    private int selectedSubjectId;
    private JLabel ErrorLabel;

    public AddQuestionController(JTextField questionField, JTextField optionField1, JTextField optionField2,
            JTextField optionField3, JTextField optionField4, JTextField correctAnswerField, String selectedSubject,
            int selectedSubjectId, JLabel errorLabel) {
        this.questionField = questionField;
        this.optionField1 = optionField1;
        this.optionField2 = optionField2;
        this.optionField3 = optionField3;
        this.optionField4 = optionField4;
        this.correctAnswerField = correctAnswerField;
        this.selectedSubject = selectedSubject;
        this.selectedSubjectId = selectedSubjectId;
        this.ErrorLabel = errorLabel;
    }

    // recieving data for validation
    public void actionPerformed(ActionEvent event) {
        // System.out.println("Question: " + questionField.getText());
        // System.out.println("Option 1: " + optionField1.getText());
        // System.out.println("Option 2: " + optionField2.getText());
        // System.out.println("Option 3: " + optionField3.getText());
        // System.out.println("Option 4: " + optionField4.getText());
        // System.out.println("Correct Answer: " + correctAnswerField.getText());
        // System.out.println("Selected Subject: " + selectedSubject);
        // System.out.println("Selected Subject ID: " + selectedSubjectId);

        String Question = questionField.getText().toLowerCase();
        String Answer1 = questionField.getText().toLowerCase();
        String Answer2 = questionField.getText().toLowerCase();
        String Answer3 = questionField.getText().toLowerCase();
        String Answer4 = questionField.getText().toLowerCase();
        String CorrectAnswer = questionField.getText().toLowerCase();
        int subjectID = selectedSubjectId;

        System.out.println(Question);
        System.out.println(Answer1);
        System.out.println(Answer2);
        System.out.println(Answer3);
        System.out.println(Answer4);
        System.out.println(CorrectAnswer);
        System.out.println(subjectID);

        
        final Timer timer = new Timer();
        final Component sourceComponent = (Component) event.getSource();

        if (Question.isEmpty() || Answer1.isEmpty() || Answer2.isEmpty() || Answer3.isEmpty() || Answer4.isEmpty()
                || CorrectAnswer.isEmpty()) {
            ErrorLabel.setText("form fields cannot be empty");
            ErrorLabel.setVisible(true);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ErrorLabel.setVisible(false);
                    timer.cancel();
                }
            }, 3000);
        }
        // add database validation for questions
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_application", "root",
                    "SiberiaV2.0");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // do the sql validation here when the database is created

            //

            // do this in else block
            ErrorLabel.setText("Question has been added.");
            ErrorLabel.setVisible(true);
            ErrorLabel.setBackground(new Color(230, 255, 237)); // light green color
            ErrorLabel.setForeground(new Color(0, 100, 0));

            // Close the view after a delay
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ErrorLabel.setText("");
                    ErrorLabel.setVisible(false);
                    timer.cancel();
                    // Close the registration view

                    Window window = SwingUtilities.getWindowAncestor(sourceComponent);
                    window.dispose();
                }
            }, 3000);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
