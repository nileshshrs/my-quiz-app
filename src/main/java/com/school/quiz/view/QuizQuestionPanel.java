package com.school.quiz.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.school.quiz.controller.AddQuestionController;
import com.school.quiz.controller.DeleteQuestionController;
import com.school.quiz.controller.EditQuestionController;
import com.school.quiz.view.Theme.GlassPanel;
import com.school.quiz.view.Theme.ModernButton;
import com.school.quiz.view.Theme.ModernTextField;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuizQuestionPanel extends GlassPanel {

    private ArrayList<String[]> QuestionData;
    private Map<String, Integer> subjectMap;

    public QuizQuestionPanel(ArrayList<String[]> questionData) {

        Color color = new Color(245, 245, 245, 200);
        this.QuestionData = questionData;
        setLayout(null);
        setBounds(250, 170, 1300, 680);

        // Create a DefaultTableModel to hold the data for the JTable
        final DefaultTableModel tableModel = new DefaultTableModel();
        final JTable table = new JTable(tableModel);
        table.setBackground(new Color(75, 159, 150));
        table.setGridColor(new Color(54, 69, 79));
        table.setForeground(new Color(248, 248, 255));
        // Set the header background color
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(57, 121, 114));
        tableHeader.setForeground(new Color(248, 248, 255));
        tableHeader.setFont(new Font("Arial", Font.BOLD, 13));

        // Define the column names
        String[] columnNames = { "Quiz Name", "Question", "Option 1", "Option 2", "Option 3", "Option 4",
                "Correct Answer" };
        tableModel.setColumnIdentifiers(columnNames);

        // Add the question data to the table model
        for (String[] rowData : QuestionData) {
            tableModel.addRow(rowData);
        }

        // Create a scroll pane to contain the table
        JScrollPane scrollPane = new JScrollPane(table);
        int tableHeight = getHeight() - 150; // Subtract 100 for padding
        int scrollPaneHeight = tableHeight;
        int scrollPaneWidth = 800;
        int scrollPaneX = 20; // Set the X position to 50 for left alignment
        int scrollPaneY = (getHeight() - scrollPaneHeight) / 2; // Calculate the Y position
        scrollPane.setBounds(scrollPaneX, scrollPaneY, scrollPaneWidth, scrollPaneHeight); // Set the bounds dynamically
        scrollPane.getViewport().setBackground(new Color(75, 159, 150));
        // Create a custom border with white color
        Border whiteBorder = BorderFactory.createLineBorder(new Color(54, 69, 79));
        // Set the viewport border
        scrollPane.setViewportBorder(whiteBorder);
        add(scrollPane);

        // table view ending here

        final JLabel errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        errorLabel.setForeground(new Color(255, 0, 0));
        errorLabel.setBackground(Color.PINK);
        errorLabel.setOpaque(true);
        errorLabel.setVisible(false);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setBounds(850, 75, 400, 25);
        add(errorLabel);

        final JTextField questionTextField = new ModernTextField("Enter the question you want to add...", color,
                color,
                color, color);
        questionTextField.setBounds(850, 110, 400, 40);
        add(questionTextField);

        String[] subjects = { "Java", "Python", "JavaScript", "HTML & CSS" };
        Integer[] subjectIds = { 1, 2, 3, 4 };

        subjectMap = new HashMap<>(); // Create a new map to store subjects and IDs
        for (int i = 0; i < subjects.length; i++) {
            subjectMap.put(subjects[i], subjectIds[i]); // Map subject name to its ID
        }

        final JComboBox<String> subjectComboBox = new JComboBox<>(subjects);
        subjectComboBox.setBounds(850, 170, 400, 20);
        add(subjectComboBox);

        final JTextField optionTextField1 = new ModernTextField("Enter the first option for the question",
                color, color,
                color, color);
        optionTextField1.setBounds(850, 200, 400, 40);
        add(optionTextField1);

        final JTextField optionTextField2 = new ModernTextField("Enter the second option for the question",
                color, color,
                color, color);
        optionTextField2.setBounds(850, 255, 400, 40);
        add(optionTextField2);

        final JTextField optionTextField3 = new ModernTextField("Enter the third option for the question",
                color, color,
                color, color);
        optionTextField3.setBounds(850, 310, 400, 40);
        add(optionTextField3);

        final JTextField optionTextField4 = new ModernTextField("Enter the fourth option for the question",
                color, color,
                color, color);
        optionTextField4.setBounds(850, 365, 400, 40);
        add(optionTextField4);

        final JTextField correctAnswerTextField = new ModernTextField(
                "Enter the correct answer for the question",
                color, color, color, color);
        correctAnswerTextField.setBounds(850, 420, 400, 40);
        add(correctAnswerTextField);

        ModernButton addButton = new ModernButton("Add Question");
        addButton.setBounds(850, 500, 400, 35);
        add(addButton);

        ModernButton editButton = new ModernButton("Edit Question");
        editButton.setBounds(850, 555, 195, 35);
        add(editButton);

        ModernButton deleteButton = new ModernButton("Delete Question");
        deleteButton.setBounds(1055, 555, 195, 35);
        add(deleteButton);

        // ActionListener for the "Add Question" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedSubject = (String) subjectComboBox.getSelectedItem();
                int selectedSubjectId = subjectMap.get(selectedSubject);

                AddQuestionController controller = new AddQuestionController(questionTextField,
                        optionTextField1,
                        optionTextField2, optionTextField3, optionTextField4,
                        correctAnswerTextField, selectedSubject,
                        selectedSubjectId, errorLabel, tableModel);
                controller.actionPerformed(e);

            }
        });

        // fetching data from table part

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Check if the selection is not adjusting (to avoid duplicate events)
                if (!e.getValueIsAdjusting()) {
                    // Get the selected row index
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Retrieve the values from the selected row
 
                        String question = table.getValueAt(selectedRow, 1).toString();
                        String option1 = table.getValueAt(selectedRow, 2).toString();
                        String option2 = table.getValueAt(selectedRow, 3).toString();
                        String option3 = table.getValueAt(selectedRow, 4).toString();
                        String option4 = table.getValueAt(selectedRow, 5).toString();
                        String correctAnswer = table.getValueAt(selectedRow, 6).toString();

                        // Do something with the retrieved values

                        questionTextField.setText(question);
                        optionTextField1.setText(option1);
                        optionTextField2.setText(option2);
                        optionTextField3.setText(option3);
                        optionTextField4.setText(option4);
                        correctAnswerTextField.setText(correctAnswer);

                    }
                }
            }
        });

        // delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteQuestionController deleteController=new DeleteQuestionController(table, tableModel, errorLabel);
                deleteController.actionPerformed(e);

                // int selectedRow = table.getSelectedRow();
                // if (selectedRow != -1) {
                //     // Remove the selected row from the table model
                //     tableModel.removeRow(selectedRow);
                //     System.out.println("Row deleted: " + selectedRow);

                questionTextField.setText("");
                optionTextField1.setText("");
                optionTextField2.setText("");
                optionTextField3.setText("");
                optionTextField4.setText("");
                correctAnswerTextField.setText("");
                // }
            }
        });

        // ActionListener for the "Edit Question" button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSubject = (String) subjectComboBox.getSelectedItem();
                int selectedSubjectId = subjectMap.get(selectedSubject);
                EditQuestionController editQuestionController = new EditQuestionController(questionTextField,
                        optionTextField1, optionTextField2, optionTextField3, optionTextField4, correctAnswerTextField,
                        selectedSubject, selectedSubjectId, errorLabel, tableModel);
                editQuestionController.actionPerformed(e);

                // // Iterate over the rows of the table model
                // for (int i = 0; i < tableModel.getRowCount(); i++) {
                // String questionValue = (String) tableModel.getValueAt(i, 1); // Assuming the
                // question is in the
                // // second column (index 1)

                // // Check if the question value matches the desired value
                // if (questionValue.equals(question)) {
                // // Retrieve the existing values from the table
                // // String existingSubject = (String) tableModel.getValueAt(i, 0);
                // // String existingOption1 = (String) tableModel.getValueAt(i, 2);
                // // String existingOption2 = (String) tableModel.getValueAt(i, 3);
                // // String existingOption3 = (String) tableModel.getValueAt(i, 4);
                // // String existingOption4 = (String) tableModel.getValueAt(i, 5);
                // // String existingCorrectAnswer = (String) tableModel.getValueAt(i, 6);

                // // Update the values of the matching row
                // tableModel.setValueAt(selectedSubject, i, 0); // Assuming subject is in the
                // first column (index
                // // 0)
                // tableModel.setValueAt(question, i, 1);
                // tableModel.setValueAt(optionTextField1.getText(), i, 2);
                // tableModel.setValueAt(optionTextField2.getText(), i, 3);
                // tableModel.setValueAt(optionTextField3.getText(), i, 4);
                // tableModel.setValueAt(optionTextField4.getText(), i, 5);
                // tableModel.setValueAt(correctAnswerTextField.getText(), i, 6);

                // System.out.println("Row edited: " + i);
                // break; // Exit the loop after editing the row
                // }
                // }
            }
        });

    }
}
