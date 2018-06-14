package ua.nure.veretelnyk.entity;

public class Answer {
    private String content;
    private boolean correct;

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public boolean isCorrect() { return correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }

    @Override
    public String toString() {
        return content + (correct ? " [correct=true] " : "");
    }
}
