package com.school.quiz.model;

import java.sql.*;

public class EditQuestion {
    private String Questions;
    private String Answers1;
    private String Answers2;
    private String Answers3;
    private String Answers4;
    private String CorrectAnswers;
    private int SubjectID;

    public EditQuestion(String question, String answer1, String answer2, String answer3, String answer4,
            String correctAnswer, int subjectId) {

        setQuestion(question);
        setAnswer1(answer1);
        setAnswer2(answer2);
        setAnswer3(answer3);
        setAnswer4(answer4);
        setCorrectAnswer(correctAnswer);
        setSubjectId(subjectId);

        updateQuestions(Questions,
                Answers1,
                Answers2,
                Answers3,
                Answers4,
                CorrectAnswers,
                SubjectID);

    }

    public void setQuestion(String question) {
        this.Questions = question;
    }

    public void setAnswer1(String answer1) {
        this.Answers1 = answer1;
    }

    public void setAnswer2(String answer2) {
        this.Answers2 = answer2;
    }

    public void setAnswer3(String answer3) {
        this.Answers3 = answer3;
    }

    public void setAnswer4(String answer4) {
        this.Answers4 = answer4;
    }

    public void setCorrectAnswer(String correctanswer) {
        this.CorrectAnswers = correctanswer;
    }

    public void setSubjectId(int subjectId) {
        this.SubjectID = subjectId;
    }

    public String getQuestion() {
        return Questions;
    }

    public String getAnswer1() {
        return Answers1;
    }

    public String getAnswer2() {
        return Answers2;
    }

    public String getAnswer3() {
        return Answers3;
    }

    public String getAnswer4() {
        return Answers4;
    }

    public String getCorrectAnswer() {
        return CorrectAnswers;
    }

    public int getSubjectId() {
        return SubjectID;
    }

    public void updateQuestions(String question, String answer1, String answer2, String answer3, String answer4,
            String correctAnswer, int subjectId) {
        // System.out.println(question);
        // System.out.println(answer1);
        // System.out.println(answer2);
        // System.out.println(answer3);
        // System.out.println(answer4);
        // System.out.println(correctAnswer);
        // System.out.println(subjectId);

        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "UPDATE questions SET quiz_id = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ?, correct_answer = ?  WHERE question_text = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quiz_application?user=root&password=SiberiaV2.0");
            statement = connection.prepareStatement(sql);
            statement.setInt(1, subjectId);
            statement.setString(2, answer1);
            statement.setString(3, answer2);
            statement.setString(4, answer3);
            statement.setString(5, answer4);
            statement.setString(6, correctAnswer);
            statement.setString(7, question);

            statement.executeUpdate();

            System.out.println("edited data");

        } catch (ClassNotFoundException exception) {
            System.out.println("MySQL JDBC driver not found");
            exception.printStackTrace();
        } catch (SQLException exception) {
            System.out.println("Failed to connect to the database");
            exception.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException exception) {
                System.out.println("Failed to close the connection");
                exception.printStackTrace();
            }
        }

    }
}