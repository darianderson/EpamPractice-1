package ua.nure.veretelnyk.constant;

public enum XML {
    GUNS("Guns"), GUN("Gun"), MODEL("Model"), HANDY("Handy"), ORIGIN("Origin"),
    TTC("TTC"), DISTANCE("Distance"), SHORT("short"), MIDDLE("middle"), LONG("long"),
    SIGHTING_RANGE("SightingRange"), COLLAR("Collar"), OPTICS("Optics"), MATERIAL("Material");

    private String value;

    XML(String value) { this.value = value; }

    public boolean equalsTo(String name) { return value.equals(name); }
    public String value() { return value; }
}
