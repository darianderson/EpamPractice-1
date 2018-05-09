package ua.nure.veretelnyk.QuestionPractice.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String questionText;
    private List<Answer> answers;

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public List<Answer> getAnswers() {
        if (answers == null)
            answers =new ArrayList<>();
        return answers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(questionText).append("\n");
        for(Answer answer : answers)
            sb.append(answer).append("\n");
        return sb.toString();
    }
}
