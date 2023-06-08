package com.school.quiz.view;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;

import com.school.quiz.controller.addQuestionController;
import com.school.quiz.view.Theme.ModernButton;
import com.school.quiz.view.Theme.ModernTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class addQuestionsView extends JFrame {

    private Map<String, Integer> subjectMap; // Map to store subjects and their IDs

    public addQuestionsView() {

        Color color = new Color(245, 245, 245, 200);
        setTitle("Add Quiz Questions");
        setSize(500, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JXPanel contentPanel = new JXPanel();
        contentPanel.setBackground(new Color(35, 178, 161));
        contentPanel.setLayout(null);

        JXLabel titleLabel = new JXLabel("Add Quiz Questions");
        titleLabel.setBounds(100, 20, 300, 60);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        contentPanel.add(titleLabel);

        final JTextField questionTextField = new ModernTextField("Enter the question you want to add...", color, color,
                color, color);
        questionTextField.setBounds(50, 120, 400, 40);
        contentPanel.add(questionTextField);

        String[] subjects = { "Java", "Python", "JavaScript", "HTML & CSS" };
        Integer[] subjectIds = { 1, 2, 3, 4 };

        subjectMap = new HashMap<>(); // Create a new map to store subjects and IDs
        for (int i = 0; i < subjects.length; i++) {
            subjectMap.put(subjects[i], subjectIds[i]); // Map subject name to its ID
        }

        final JComboBox<String> subjectComboBox = new JComboBox<>(subjects);
        subjectComboBox.setBounds(50, 190, 400, 40);
        contentPanel.add(subjectComboBox);

        final JTextField optionTextField1 = new ModernTextField("Enter the first option for the question", color, color,
                color, color);
        optionTextField1.setBounds(50, 270, 400, 40);
        contentPanel.add(optionTextField1);

        final JTextField optionTextField2 = new ModernTextField("Enter the second option for the question", color, color,
                color, color);
        optionTextField2.setBounds(50, 340, 400, 40);
        contentPanel.add(optionTextField2);

        final JTextField optionTextField3 = new ModernTextField("Enter the third option for the question", color, color,
                color, color);
        optionTextField3.setBounds(50, 410, 400, 40);
        contentPanel.add(optionTextField3);

        final JTextField optionTextField4 = new ModernTextField("Enter the fourth option for the question", color, color,
                color, color);
        optionTextField4.setBounds(50, 480, 400, 40);
        contentPanel.add(optionTextField4);

        final JTextField correctAnswerTextField = new ModernTextField("Enter the correct answer for the question",
                color, color, color, color);
        correctAnswerTextField.setBounds(50, 550, 400, 40);
        contentPanel.add(correctAnswerTextField);

        ModernButton addButton = new ModernButton("Add Question");
        addButton.setBounds(150, 610, 150, 35);
        contentPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String selectedSubject = (String) subjectComboBox.getSelectedItem();
                int selectedSubjectId = subjectMap.get(selectedSubject);
                addQuestionController controller = new addQuestionController(questionTextField, optionTextField1,
                        optionTextField2, optionTextField3, optionTextField4, correctAnswerTextField, selectedSubject,
                        selectedSubjectId);
                controller.actionPerformed(event);
            }
        });

        getContentPane().add(contentPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new addQuestionsView();
    }
}
