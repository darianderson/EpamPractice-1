package ua.nure.veretelnyk.entity;

public class Distance {
    private String type;
    private int distance;

    public Distance(String type, int distance) {
        this.type = type;
        this.distance = distance;
    }
    public Distance() {}

    public String getType() {
        return type;
    }

    public int getDistance() {
        return distance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
