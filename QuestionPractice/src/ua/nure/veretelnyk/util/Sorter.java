package ua.nure.veretelnyk.util;

import ua.nure.veretelnyk.entity.Answer;
import ua.nure.veretelnyk.entity.Question;
import ua.nure.veretelnyk.entity.Test;

import java.util.Collections;
import java.util.Comparator;

public class Sorter {

    /////////////////////////////////////// Comparator

    public static final Comparator<Question> SORT_QUESTIONS_BY_QUESTION_TEXT = new Comparator<Question>() {
        @Override
        public int compare(Question o1, Question o2) {
            return o1.getQuestionText().compareTo(o2.getQuestionText());
        }
    };
    public static final Comparator<Question> SORT_QUESTIONS_BY_ANSWER_NUMBER = new Comparator<Question>() {
        @Override
        public int compare(Question o1, Question o2) { return o1.getAnswers().size() - o2.getAnswers().size(); }
    };
    public static final Comparator<Answer> SORT_ANSWERS_BY_CONTENT = new Comparator<Answer>() {
        @Override
        public int compare(Answer o1, Answer o2) { return o1.getContent().compareTo(o2.getContent()); }
    };
    /////////////////////////////////////// Sorts
    public static final void sortQuestionsByQuestionText(Test test){
        Collections.sort(test.getQuestions(), SORT_QUESTIONS_BY_QUESTION_TEXT);
    }

    public static final void sortQuestionsByAnswersNumber(Test test){
        Collections.sort(test.getQuestions(), SORT_QUESTIONS_BY_ANSWER_NUMBER);
    }

    public static final void sortAnswersByContent(Test test){
        for(Question question : test.getQuestions())
            Collections.sort(question.getAnswers(), SORT_ANSWERS_BY_CONTENT);
    }
}
