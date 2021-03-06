package ua.nure.veretelnyk.entity;

public class User extends DBEntry {
    public User(String login) {
        this.login = login;
    }
    public User() {}

    public static User createUser(String login) { return new User(login); }
}
