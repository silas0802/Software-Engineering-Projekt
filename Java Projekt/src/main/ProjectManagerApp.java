package main;

import java.util.ArrayList;

public class ProjectManagerApp {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Project> projects = new ArrayList<>();

    public void createUser(User user){
        users.add(user);
    }
    public void createProject(Project project){
        projects.add(project);
    }
    public void createActivity(Project project, Activity activity){
        project.setActivity(activity);
    }
}
