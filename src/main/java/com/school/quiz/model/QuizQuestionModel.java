package com.school.quiz.model;

import java.sql.*;
import java.util.ArrayList;



public class QuizQuestionModel {
    private ArrayList<String[]> quizData;

    public QuizQuestionModel() {
        quizData = new ArrayList<>();
        System.out.println(getQuizData());

        questionData();
        // new QuizQuestionPanel(quizData);
        // System.out.println(quizData);
    }

    public ArrayList<String[]> getQuizData() {
        return quizData;
    }

    public void setQuestionData(ArrayList<String[]> quizData) {
        this.quizData = quizData;
    }

    public void questionData() {

        Connection connection = null;
        PreparedStatement statement = null;
        String SQL = "SELECT q.quiz_name, " +
                "qu.question_id, qu.question_text, qu.option1, qu.option2, qu.option3, qu.option4, qu.correct_answer " +
                "FROM quiz q " +
                "JOIN questions qu ON q.quiz_id = qu.quiz_id " +
                "ORDER BY qu.question_id ASC;";

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quiz_application?user=root&password=SiberiaV2.0");

            statement = connection.prepareStatement(SQL);

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
            setQuestionData(quizData);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new QuizQuestionModel();
        // List<String[]> quizData = model.getQuizData();

        // for (String[] data : quizData) {
        // System.out.println(Arrays.toString(data));
        // }

        // String userStringData = Arrays.deepToString(quizData.toArray());
        // System.out.println(userStringData);
    }
}
