package ua.nure.veretelnyk.db.entity;

public class Carriage {
    private int id;
    private CarriageType type;
    private int totalPlaces;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarriageType getType() {
        return type;
    }

    public void setType(CarriageType type) {
        this.type = type;
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }
}
