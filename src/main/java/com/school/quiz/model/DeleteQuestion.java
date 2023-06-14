package com.school.quiz.model;

public class DeleteQuestion {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String CorrectOption;

    public DeleteQuestion(String Question, String Option1, String Option2, String Option3, String Option4,
            String CorrectOption) {
                System.out.println(Question);
                System.out.println(Option1);
                System.out.println(Option2);
                System.out.println(Option3);
                System.out.println(Option4);
                System.out.println(CorrectOption);
    }
}
