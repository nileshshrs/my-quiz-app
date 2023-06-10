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
            String answer5, int ID) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Establish the database connection
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/quiz_application?user=root&password=SiberiaV2.0");

            // Create the 'questions' table if it doesn't exist
            String createTableSql = "CREATE TABLE IF NOT EXISTS questions (question_id INT PRIMARY KEY AUTO_INCREMENT, quiz_id INT, question_text VARCHAR(255), "
                    + "option1 VARCHAR(255), option2 VARCHAR(255), option3 VARCHAR(255), option4 VARCHAR(255), " +
                    "correct_answer VARCHAR(255), FOREIGN KEY (quiz_id) REFERENCES quiz(quiz_id));";
            statement = connection.prepareStatement(createTableSql);
            statement.executeUpdate();

            // Insert data into the 'questions' table
            String insertSql = "INSERT INTO questions (quiz_id, question_text, option1, option2, option3, option4, correct_answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(insertSql);
            statement.setInt(1, ID);
            statement.setString(2, question);
            statement.setString(3, answer1);
            statement.setString(4, answer2);
            statement.setString(5, answer3);
            statement.setString(6, answer4);
            statement.setString(7, answer5);

            statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Failed to connect to the database or execute SQL statements");
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
