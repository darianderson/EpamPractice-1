package ua.nure.veretelnyk.db.entity;

public class Country {
    private int id;
    private String fullName;
    private String shortName;

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
