package com.school.quiz.view;

import java.awt.Color;

import javax.swing.JFrame;

import org.jdesktop.swingx.JXPanel;

public class StudentQuizPage extends JFrame {
    public StudentQuizPage() {
        setTitle("Quiz Application");
        setSize(1600, 1000);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create a new JXPanel instance
        JXPanel panel = new JXPanel();
        panel.setBackground(new Color(35, 178, 161));
        panel.setLayout(null);

        // Create a SidebarMenu instance and add it to the panel


        getContentPane().add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentQuizPage();
    }
    
}
