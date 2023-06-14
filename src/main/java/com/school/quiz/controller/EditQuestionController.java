package com.school.quiz.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.school.quiz.model.EditQuestion;

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
            int selectedSubjectId, JLabel errorLabel, DefaultTableModel TableModel) {

        this.QuestionField = questionField;
        this.OptionField1 = optionField1;
        this.OptionField2 = optionField2;
        this.OptionField3 = optionField3;
        this.OptionField4 = optionField4;
        this.CorrectAnswerField = correctAnswerField;
        this.SelectedSubject = selectedSubject;
        this.SelectedSubjectId = selectedSubjectId;
        this.ErrorLabel = errorLabel;
        this.TableModel = TableModel;

    }

    public void actionPerformed(ActionEvent event) {

        String Question = QuestionField.getText().toLowerCase();
        String Answer1 = OptionField1.getText().toLowerCase();
        String Answer2 = OptionField2.getText().toLowerCase();
        String Answer3 = OptionField3.getText().toLowerCase();
        String Answer4 = OptionField4.getText().toLowerCase();
        String CorrectAnswer = CorrectAnswerField.getText().toLowerCase();
        int subjectID = SelectedSubjectId;

        // System.out.println("Question: " + Question);
        // System.out.println("Option 1: " + Answer1);
        // System.out.println("Option 2: " + Answer2);
        // System.out.println("Option 3: " + Answer3);
        // System.out.println("Option 4: " + Answer4);
        // System.out.println("Correct Answer: " + CorrectAnswer);
        // System.out.println("Selected Subject: " + SelectedSubject);
        // System.out.println("Selected Subject ID: " + subjectID);
        final Timer timer = new Timer();
        if (Question.isEmpty() || Answer1.isEmpty() || Answer1.isEmpty() || Answer2.isEmpty()
                || Answer3.isEmpty() || Answer4.isEmpty() || CorrectAnswer.isEmpty()) {
            ErrorLabel.setText("Fields cannot be empty");
            ErrorLabel.setVisible(true);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ErrorLabel.setText("");
                    ErrorLabel.setVisible(false);
                    timer.cancel();
                }
            }, 3000);

        } else {
            new EditQuestion(Question, Answer1, Answer2, Answer3, Answer4, CorrectAnswer, subjectID);

            for (int i = 0; i < TableModel.getRowCount(); i++) {
                String questionValue = (String) TableModel.getValueAt(i, 1); // Assuming the

                if (questionValue.equals(Question)) {
                    // Retrieve the existing values from the table

                    // Update the values of the matching row
                    TableModel.setValueAt(SelectedSubject, i, 0); // Assuming subject is in the
                    // first column (index
                    // // 0)
                    TableModel.setValueAt(Question, i, 1);
                    TableModel.setValueAt(Answer1, i, 2);
                    TableModel.setValueAt(Answer2, i, 3);
                    TableModel.setValueAt(Answer3, i, 4);
                    TableModel.setValueAt(Answer4, i, 5);
                    TableModel.setValueAt(CorrectAnswer, i, 6);

                    System.out.println("Row edited: " + i);
                    break; // Exit the loop after editing the row
                }
            }

            ErrorLabel.setText("Question sucessfully edited.");
            ErrorLabel.setVisible(true);
            ErrorLabel.setBackground(new Color(230, 255, 237)); // light green color
            ErrorLabel.setForeground(new Color(0, 100, 0));

            QuestionField.setText("");
            OptionField1.setText("");
            OptionField2.setText("");
            OptionField3.setText("");
            OptionField4.setText("");
            CorrectAnswerField.setText("");

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ErrorLabel.setText("");
                    ErrorLabel.setVisible(false);
                    timer.cancel();
                }
            }, 3000);
        }
    }

}
