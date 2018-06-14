package ua.nure.veretelnyk.entity;

public class Group extends DBEntry{
    public Group(String login) {
        this.login = login;
    }
    public Group(int id, String login){
        this.id = id;
        this.login = login;
    }
    public Group() {}
    public void setName(String name) {this.login = name;}
    public String getName() {return login;}

    public static Group createGroup(String name) { return new Group(name); }
}
