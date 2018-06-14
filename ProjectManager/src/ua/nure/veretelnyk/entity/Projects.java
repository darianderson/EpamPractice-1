package ua.nure.veretelnyk.entity;

import java.util.ArrayList;
import java.util.List;

public class Projects {
    private List<Project> projects;
    private int carriage = -1;
    public List<Project> getProjects(){
        if (projects == null)
            projects = new ArrayList<>();
        return projects;
    }

    private void correct(){
        if(carriage >= projects.size())
            carriage = 0;
        if(carriage < 0)
            carriage = projects.size() - 1;
    }

    public Project next(){
        carriage++;
        correct();
        return projects.get(carriage);
    }
    public Project previous(){
        carriage--;
        correct();
        return projects.get(carriage);
    }
}
