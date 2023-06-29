package com.school.quiz.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Quiz {

    private ArrayList<String[]> quizData;
    private ArrayList<String[]> javaQuizData;
    private ArrayList<String[]> pythonQuizData;
    private ArrayList<String[]> javascriptQuizData;
    private ArrayList<String[]> htmlCssQuizData;

    public Quiz() {
        this.quizData = new ArrayList<>();
        this.javaQuizData = new ArrayList<>();
        this.pythonQuizData = new ArrayList<>();
        this.javascriptQuizData = new ArrayList<>();
        this.htmlCssQuizData = new ArrayList<>();
        retrieveData();
    }

    public void setQuizData(ArrayList<String[]> quizData) {
        this.quizData = quizData;
    }

    public ArrayList<String[]> getQuizData() {
        return quizData;
    }

    public ArrayList<String[]> getJavaQuizData() {
        return javaQuizData;
    }

    public ArrayList<String[]> getPythonQuizData() {
        return pythonQuizData;
    }

    public ArrayList<String[]> getJavaScriptQuizData() {
        return javascriptQuizData;
    }

    public ArrayList<String[]> getHtmlCssQuizData() {
        return htmlCssQuizData;
    }

    public void retrieveData() {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT q.quiz_name, qu.question_text, qu.option1, qu.option2, qu.option3, qu.option4, qu.correct_answer "
                + "FROM quiz q "
                + "JOIN questions qu ON q.quiz_id = qu.quiz_id";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_application?user=root&password=SiberiaV2.0");
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String quizName = rs.getString("quiz_name");
                String question = rs.getString("question_text");
                String answer1 = rs.getString("option1");
                String answer2 = rs.getString("option2");
                String answer3 = rs.getString("option3");
                String answer4 = rs.getString("option4");
                String correctAnswer = rs.getString("correct_answer");

                String[] rowData = {question, answer1, answer2, answer3, answer4, correctAnswer};
                quizData.add(rowData);

                // Check the quiz name and add the question data to the appropriate variables
                if (quizName.equalsIgnoreCase("Java")) {
                    javaQuizData.add(rowData);
                } else if (quizName.equalsIgnoreCase("Python")) {
                    pythonQuizData.add(rowData);
                } else if (quizName.equalsIgnoreCase("JavaScript")) {
                    javascriptQuizData.add(rowData);
                } else if (quizName.equalsIgnoreCase("HTML & CSS")) {
                    htmlCssQuizData.add(rowData);
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        ArrayList<String[]> javaQuestions = quiz.getJavaQuizData();
        ArrayList<String[]> pythonQuestions = quiz.getPythonQuizData();
        ArrayList<String[]> javascriptQuestions = quiz.getJavaScriptQuizData();
        ArrayList<String[]> htmlCssQuestions = quiz.getHtmlCssQuizData();

        System.out.println("Java Questions:");
        for (String[] question : javaQuestions) {
            System.out.println("Question: " + question[0]);
            System.out.println("Option 1: " + question[1]);
            System.out.println("Option 2: " + question[2]);
            System.out.println("Option 3: " + question[3]);
            System.out.println("Option 4: " + question[4]);
            System.out.println("Correct Answer: " + question[5]);
            System.out.println();
        }

        System.out.println("Python Questions:");
        for (String[] question : pythonQuestions) {
            System.out.println("Question: " + question[0]);
            System.out.println("Option 1: " + question[1]);
            System.out.println("Option 2: " + question[2]);
            System.out.println("Option 3: " + question[3]);
            System.out.println("Option 4: " + question[4]);
            System.out.println("Correct Answer: " + question[5]);
            System.out.println();
        }

        System.out.println("JavaScript Questions:");
        for (String[] question : javascriptQuestions) {
            System.out.println("Question: " + question[0]);
            System.out.println("Option 1: " + question[1]);
            System.out.println("Option 2: " + question[2]);
            System.out.println("Option 3: " + question[3]);
            System.out.println("Option 4: " + question[4]);
            System.out.println("Correct Answer: " + question[5]);
            System.out.println();
        }

        System.out.println("HTML & CSS Questions:");
        for (String[] question : htmlCssQuestions) {
            System.out.println("Question: " + question[0]);
            System.out.println("Option 1: " + question[1]);
            System.out.println("Option 2: " + question[2]);
            System.out.println("Option 3: " + question[3]);
            System.out.println("Option 4: " + question[4]);
            System.out.println("Correct Answer: " + question[5]);
            System.out.println();
        }
    }

}
