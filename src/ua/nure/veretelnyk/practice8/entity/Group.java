package ua.nure.veretelnyk.practice8.entity;

public class Group {
    private int id;
    private String name;

    public Group(String name) {
        this.name = name;
    }
    public Group() {}

    public static Group createGroup(String name) { return new Group(name); }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }
}
