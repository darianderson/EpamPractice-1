package ua.nure.veretelnyk.entity;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private List<Question> questions;

    public List<Question> getQuestions() {
        if (questions == null)
            questions = new ArrayList<>();

        return questions;
    }

    @Override
    public String toString() {
        if (questions == null || questions.size() == 0)
            return "Test contains no questions";

        StringBuilder sb = new StringBuilder();
        for(Question question : questions)
            sb.append(question).append("\n");

        return sb.toString();
    }
}
