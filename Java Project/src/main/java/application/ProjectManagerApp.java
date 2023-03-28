package application;

import java.util.ArrayList;

public class ProjectManagerApp {
    User loggedUser;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Project> projects = new ArrayList<>();

    public void login(User user){
        loggedUser = user;
    }
    public void createUser(User user){
        users.add(user);
    }
    public void createProject(Project project){
        projects.add(project);
    }
    public void createActivity(Project project, Activity activity){
        project.setActivity(activity);
    }
    public boolean hasProject(Project project){
        return projects.contains(project);
    }
    public boolean hasActivity(Project project,Activity activity){
        ArrayList<Activity> a = project.getActivities();
        return a.contains(activity);
    }

    public void assignLeader(Project project, User user){

    project.setProjectLeader(user);
    }

    
}
