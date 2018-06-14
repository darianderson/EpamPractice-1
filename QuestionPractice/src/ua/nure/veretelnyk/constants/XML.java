package ua.nure.veretelnyk.constants;

public enum XML {

    // elements
    TEST("Test"), QUESTIOIN("Question"), QUESTION_TEXT("QuestionText"), ANSWER("Answer"),

    //attributes
    CORRECT("correct");

    private String value;

    XML(String value) { this.value = value; }

    public boolean equalsTo(String name) { return value.equals(name); }
    public String value() { return value; }
}
