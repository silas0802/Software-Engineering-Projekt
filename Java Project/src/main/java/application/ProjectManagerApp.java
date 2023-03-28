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
    public void createActivity(Project project, Activity activity) throws OperationNotAllowedException{
        if(project.getActivities().size()>=100){
            throw new OperationNotAllowedException("Too many activities");
        }
        project.setActivity(activity);
    }
    public boolean hasProject(Project project){
        return projects.contains(project);
    }
    public boolean hasActivity(Project project,Activity activity){
        ArrayList<Activity> a = project.getActivities();
        return a.contains(activity);
    }

    public boolean hasUser(User user){
        return users.contains(user);
    }


    public User searchByName(String name){
        for (User user : users) {
            if(user.getUserName().equals(name)){
                return user;
            }   
        }
        return null;
    }

    public void assignLeader(Project project, User user){

    project.setProjectLeader(user);
    }

    
}
