package ua.nure.veretelnyk.practice8.entity;

public abstract class DBEntry {
    protected int id;
    protected String login;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    @Override
    public String toString() {
        return "[id=" + id + ", login=" + login + "]";
    }

}
