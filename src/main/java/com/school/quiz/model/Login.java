package com.school.quiz.model;

import java.util.ArrayList;

import com.school.quiz.view.StudentQuizPage;
import com.school.quiz.view.TeacherQuizPage;

public class Login {
    private ArrayList<String[]> userData;
    
    public Login() {
        this.userData = new ArrayList<>();
    }

    public ArrayList<String[]> getUserData() {
        return userData;
    }

    public void setUserData(ArrayList<String[]> userData) {
        this.userData = userData;
    }

    public void loginWithRole(){
        for(String[] data : userData){
            String username = data[3];
            String role = data[6];
            if(role.equals("student")){
                System.out.println(username);
                // new StudentQuizPage();
                new TeacherQuizPage(username);
            }else if(role.equals("teacher")){
                System.out.println(username);
                new TeacherQuizPage(username);
            }
        }
    }
}
