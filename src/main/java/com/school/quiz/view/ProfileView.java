package com.school.quiz.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import com.school.quiz.view.Theme.GlassPanel;


import java.util.ArrayList;

import javax.swing.JPanel;


public class ProfileView extends GlassPanel {

    private ArrayList <String []> userData;
    private JPanel userCard;

    public ProfileView(ArrayList <String[]> userdata) {
        this.userData=userdata;

        for (String data []: userData){
            System.out.println(data[5]);
        }

  
        setLayout(null);
        setBounds(250, 170, 1300, 680);

  
    }
}
