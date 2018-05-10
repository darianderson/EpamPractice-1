package ua.nure.veretelnyk.ProjectManager.constants;

public enum XML {
    PROJECTS("projects"), PROJECT("project"), CUSTOMER("customer"),
    ID("id"), NAME("name"), PRICE("price"), DESCRIPTION("description"),
    PROJECT_NAME("ProjectName");

    private String value;

    XML(String value) { this.value = value; }
    public String value() { return value; }
}
