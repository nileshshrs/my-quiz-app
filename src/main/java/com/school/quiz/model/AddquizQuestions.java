package com.school.quiz.model;

import java.sql.*;

public class AddQuizQuestions {
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;
    private int subjectId;

    public AddQuizQuestions(String question, String answer1, String answer2, String answer3, String answer4,
            String correctAnswer, int subjectId) {
        setQuestion(question);
        setAnswer1(answer1);
        setAnswer2(answer2);
        setAnswer3(answer3);
        setAnswer4(answer4);
        setCorrectAnswer(correctAnswer);
        setSubjectId(subjectId);
        addQuestions(getQuestion(), getAnswer1(), getAnswer2(), getAnswer3(), getAnswer4(), getCorrectAnswer(),
                getSubjectId());
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

 public void addQuestions(String question, String answer1, String answer2, String answer3, String answer4,
                         String correctAnswer, int subjectID) {

    Connection connection = null;
    PreparedStatement statement = null;

    String sql = "INSERT INTO questions (quiz_id, question_text, option1, option2, option3, option4, correct_answer) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quiz_application?user=root&password=SiberiaV2.0");

        statement = connection.prepareStatement(sql);
        statement.setInt(1, subjectID);
        statement.setString(2, question);
        statement.setString(3, answer1);
        statement.setString(4, answer2);
        statement.setString(5, answer3);
        statement.setString(6, answer4);
        statement.setString(7, correctAnswer);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Question added successfully.");
        } else {
            System.out.println("Failed to add the question.");
        }

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