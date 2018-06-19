package ua.nure.veretelnyk.db.entity;

public enum CarriageType {
    COMPARTMENT("compartment"), BERTH("berth"), COMMON("common");
    private String name;

    CarriageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
