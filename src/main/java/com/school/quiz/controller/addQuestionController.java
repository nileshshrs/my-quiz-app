package com.school.quiz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class addQuestionController implements ActionListener {
    private JTextField questionField;
    private JTextField optionField1;
    private JTextField optionField2;
    private JTextField optionField3;
    private JTextField optionField4;
    private JTextField correctAnswerField;
    private String selectedSubject;
    private int selectedSubjectId;

    public addQuestionController(JTextField questionField, JTextField optionField1, JTextField optionField2,
            JTextField optionField3, JTextField optionField4, JTextField correctAnswerField, String selectedSubject,
            int selectedSubjectId) {
        this.questionField = questionField;
        this.optionField1 = optionField1;
        this.optionField2 = optionField2;
        this.optionField3 = optionField3;
        this.optionField4 = optionField4;
        this.correctAnswerField = correctAnswerField;
        this.selectedSubject = selectedSubject;
        this.selectedSubjectId = selectedSubjectId;
    }

    public void actionPerformed(ActionEvent event) {
        System.out.println("Question: " + questionField.getText());
        System.out.println("Option 1: " + optionField1.getText());
        System.out.println("Option 2: " + optionField2.getText());
        System.out.println("Option 3: " + optionField3.getText());
        System.out.println("Option 4: " + optionField4.getText());
        System.out.println("Correct Answer: " + correctAnswerField.getText());
        System.out.println("Selected Subject: " + selectedSubject);
        System.out.println("Selected Subject ID: " + selectedSubjectId);
    }
}
