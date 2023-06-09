package com.school.quiz.model;

import java.sql.*;

public class AddQuizQuestions {

    private String Question;
    private String Answer1;
    private String Answer2;
    private String Answer3;
    private String Answer4;
    private String CorrectAnswer;
    private int subjectID;
    // getters and setters

    public AddQuizQuestions() {

    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getQuestion() {
        return Question;
    }

    public void setAnswer1(String Answer1) {
        this.Answer1 = Answer1;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public void setAnswer2(String Answer2) {
        this.Answer2 = Answer2;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer3(String Answer3) {
        this.Answer3 = Answer3;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer4(String Answer4) {
        this.Answer4 = Answer4;
    }

    public String getAnswer4() {
        return Answer4;
    }

    public void setCorrectAnswer(String CorrectAnswer) {
        this.CorrectAnswer = CorrectAnswer;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getSubjectID() {
        return subjectID;
    }



}
