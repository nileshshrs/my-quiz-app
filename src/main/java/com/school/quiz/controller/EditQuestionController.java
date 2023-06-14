package com.school.quiz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EditQuestionController implements ActionListener {

    private JTextField QuestionField;
    private JTextField OptionField1;
    private JTextField OptionField2;
    private JTextField OptionField3;
    private JTextField OptionField4;
    private JTextField CorrectAnswerField;
    private String SelectedSubject;
    private int SelectedSubjectId;
    private JLabel ErrorLabel;
    private DefaultTableModel TableModel;

    public EditQuestionController(JTextField questionField, JTextField optionField1, JTextField optionField2,
            JTextField optionField3, JTextField optionField4, JTextField correctAnswerField, String selectedSubject,
            int selectedSubjectId, JLabel errorLabel, DefaultTableModel tableModel) {

        this.QuestionField = questionField;
        this.OptionField1 = optionField1;
        this.OptionField2 = optionField2;
        this.OptionField3 = optionField3;
        this.OptionField4 = optionField4;
        this.CorrectAnswerField = correctAnswerField;
        this.SelectedSubject = selectedSubject;
        this.SelectedSubjectId = selectedSubjectId;
        this.ErrorLabel = errorLabel;
        this.TableModel = tableModel;

    }

    public void actionPerformed(ActionEvent event) {

        
        String Question = QuestionField.getText().toLowerCase();
        String Answer1 = OptionField1.getText().toLowerCase();
        String Answer2 = OptionField2.getText().toLowerCase();
        String Answer3 = OptionField3.getText().toLowerCase();
        String Answer4 = OptionField4.getText().toLowerCase();
        String CorrectAnswer = CorrectAnswerField.getText().toLowerCase();
        int subjectID = SelectedSubjectId;

        System.out.println("Question: " + Question);
        System.out.println("Option 1: " + Answer1);
        System.out.println("Option 2: " + Answer2);
        System.out.println("Option 3: " + Answer3);
        System.out.println("Option 4: " + Answer4);
        System.out.println("Correct Answer: " + CorrectAnswer);
        System.out.println("Selected Subject: " + SelectedSubject);
        System.out.println("Selected Subject ID: " + subjectID);
    }

}
