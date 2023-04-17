package application;

import java.util.ArrayList;
import java.util.List;

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
        activity.setProject(project);
    }
    public void setProjectName(Project project, String name)throws OperationNotAllowedException{
        if (loggedUser == project.getProjectLeader()) {
            project.setName(name);
        }
        else{
            throw new OperationNotAllowedException("User doesn't have permission");
        }
    }
    public void setProjectDescription(Project project, String des)throws OperationNotAllowedException{
        if (loggedUser == project.getProjectLeader()) {
            project.setDescription(des);
        }
        else{
            throw new OperationNotAllowedException("User doesn't have permission");
        }
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
    public boolean projectHasUsers(Project project, User[] users){
        for (User user : project.getWorkers()) {
            if (!hasUser(user)) {
                return false;
            }
        }
        return true;
    }


    public User searchByName(String name){
        for (User user : users) {
            if(user.getUserName().equals(name)){
                return user;
            }   

        }
        return null;
    }
    public  List<User> getUsers(){
        return users;
    }

    public void assignLeader(Project project, User user){

    project.setProjectLeader(user);
    }
    public void assignActivityToUser(User user, Activity activity) throws OperationNotAllowedException{
        if(user.getActivities().size()>=20){
            throw new OperationNotAllowedException("Maximum of 20 activities are already assigned to user this week");
        }
        if (userHasActivity(activity, user)) {
            throw new OperationNotAllowedException("Worker is already assigned to a project");
        }
        user.joinActivity(activity);
        activity.setActiveUser(user);
    }
    public List<Activity> getUserActivities(User user){
        return user.getActivities();
    }

    public boolean userHasActivity(Activity activity,User user){

        List<Activity> userActivities = getUserActivities(user);
        return userActivities.contains(activity);

    }

    public List<User> getUsersWithoutActivities(){
        return null;
        
    }

    public void setActiveDescription(Activity activity, String desciption){

        activity.setDescription(desciption);

    }

    public void finishActivity(Project project,Activity activity){
        project.setFinishedActivity(activity);
    }
}
