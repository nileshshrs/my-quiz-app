package com.school.quiz.view;

import com.school.quiz.view.Theme.GlassPanel;
import com.school.quiz.view.Theme.ModernButton;



import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class QuizQuestionPanel extends GlassPanel {



    public QuizQuestionPanel() {


        setLayout(null);
        setBounds(350, 170, 1100, 650);

        ModernButton addQuestionButton = new ModernButton("Add Questions");
        addQuestionButton.setBounds(500, 500, 150,40);
        addQuestionButton.setFont(new Font("Arial", Font.BOLD, 16));
        add(addQuestionButton);
        addQuestionButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    new addQuestionsView();
                }
            }
        );
      
    }
}
