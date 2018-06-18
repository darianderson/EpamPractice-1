package ua.nure.veretelnyk.db.entity;

import java.util.Date;

public class Train {
    private int id;
    private Model model;
    private Date since;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }
    public void setModel(Model model) {
        this.model = model;
    }

    public Date getSince() {
        return since;
    }
    public void setSince(Date since) {
        this.since = since;
    }
}
