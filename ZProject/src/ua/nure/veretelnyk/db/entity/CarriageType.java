package ua.nure.veretelnyk.db.entity;

public enum CarriageType {
    compartment("compartment"), berth("berth"), common("common");
    private String name;

    CarriageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
