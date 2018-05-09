package ua.nure.veretelnyk.QuestionPractice.util;

import ua.nure.veretelnyk.QuestionPractice.entity.Question;
import ua.nure.veretelnyk.QuestionPractice.entity.Test;

import java.util.Collection;
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
    /////////////////////////////////////// Sorts
    public static final void sortQuestionsByQuestionText(Test test){
        Collections.sort(test.getQuestions(), SORT_QUESTIONS_BY_QUESTION_TEXT);
    }

    public static final void setSortQuestionsByAnswersNumber(Test test){
        Collections.sort(test.getQuestions(), SORT_QUESTIONS_BY_ANSWER_NUMBER);
    }
}