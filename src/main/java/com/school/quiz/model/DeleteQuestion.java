package com.school.quiz.model;

import java.sql.*;

public class DeleteQuestion {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctOption;

    public DeleteQuestion(String Question, String Option1, String Option2, String Option3, String Option4,
            String CorrectOption) {
        setQuestion(Question);
        setOption1(Option1);
        setOption2(Option2);
        setOption3(Option3);
        setOption4(Option4);
        setCorrectOption(CorrectOption);

        deleteQuestion(question, option1, option2, option3, option4, correctOption);

    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption4() {
        return option4;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void deleteQuestion(String question, String option1, String option2, String option3, String option4,
            String correctoption) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM questions WHERE question_text = ? AND option1 = ? AND option2 = ? AND option3 = ? AND option4 = ? AND correct_answer = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quiz_application?user=root&password=SiberiaV2.0");
            statement = connection.prepareStatement(sql);
            statement.setString(1, question);
            statement.setString(2, option1);
            statement.setString(3, option2);
            statement.setString(4, option3);
            statement.setString(5, option4);
            statement.setString(6, correctoption);

            statement.executeUpdate();
            System.out.println("Data Deleted.");
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
