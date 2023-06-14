package com.school.quiz.controller;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.school.quiz.model.DeleteQuestion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class DeleteQuestionController implements ActionListener {

    private JTable Table;
    private DefaultTableModel TableModel;
    private JLabel ErrorLabel;

    public DeleteQuestionController(JTable table, DefaultTableModel tableModel, JLabel errorLabel) {
        this.Table = table;
        this.TableModel = tableModel;
        this.ErrorLabel = errorLabel;
    }

    public void actionPerformed(ActionEvent event) {

        int selectedRow = Table.getSelectedRow();

        final Timer timer = new Timer();
        // System.out.println(selectedRow);

        if (selectedRow != -1) {
            String question = Table.getValueAt(selectedRow, 1).toString();
            String option1 = Table.getValueAt(selectedRow, 2).toString();
            String option2 = Table.getValueAt(selectedRow, 3).toString();
            String option3 = Table.getValueAt(selectedRow, 4).toString();
            String option4 = Table.getValueAt(selectedRow, 5).toString();
            String correctOption = Table.getValueAt(selectedRow, 6).toString();

            new DeleteQuestion(question, option1, option2, option3, option4, correctOption);

            ErrorLabel.setText("Deleted Sucessfully.");
            ErrorLabel.setVisible(true);
            ErrorLabel.setBackground(new Color(230, 255, 237)); // light green color
            ErrorLabel.setForeground(new Color(0, 100, 0));
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ErrorLabel.setText("");
                    ErrorLabel.setVisible(false);
                    timer.cancel();
                }
            }, 3000);
            TableModel.removeRow(selectedRow);
        }

    }
}
