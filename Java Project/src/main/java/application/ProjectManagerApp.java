package application;

import java.util.ArrayList;

public class ProjectManagerApp {
    User loggedUser;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Project> projects = new ArrayList<>();

    public boolean isLoggedIn(){
        return loggedUser != null;
    }

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

    public void assignLeader(Project project, User user){

    project.setProjectLeader(user);
    }

    
}
