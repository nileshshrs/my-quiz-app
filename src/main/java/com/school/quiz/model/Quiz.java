package com.school.quiz.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Quiz {

    private String QuizName;

    private ArrayList<String[]> quizData;

    public Quiz(String quizname) {

        quizData = new ArrayList<>();
        setQuizName(quizname);

        retrieveData(quizname);

    }

    public void setQuizName(String quizname) {
        this.QuizName = quizname;
    }

    public String getQuizName() {
        return QuizName;
    }

    public void setQuizData(ArrayList<String[]> newQuizData) {
        this.quizData = newQuizData;
    }

    public ArrayList<String[]> getnewQuizData() {
        return quizData;
    }

    public void retrieveData(String quizname) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT  q.quiz_name, qu.question_id, qu.question_text, qu.option1, qu.option2, qu.option3, qu.option4, qu.correct_answer "
                +
                "FROM quiz q " +
                "JOIN questions qu ON q.quiz_id = qu.quiz_id " +
                "WHERE q.quiz_name = ? " +
                "ORDER BY qu.question_id ASC;";

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quiz_application?user=root&password=SiberiaV2.0");

            statement = connection.prepareStatement(sql);
            statement.setString(1, quizname);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String quiz_name = rs.getString("quiz_name");
                String question = rs.getString("question_text");
                String answer1 = rs.getString("option1");
                String answer2 = rs.getString("option2");
                String answer3 = rs.getString("option3");
                String answer4 = rs.getString("option4");
                String correct_answer = rs.getString("correct_answer");

                String[] rowData = { quiz_name, question, answer1, answer2, answer3, answer4, correct_answer };

                quizData.add(rowData);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }



}
