package application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProjectManagerApp {
    User loggedUser;
    static ArrayList<User> users = new ArrayList<>();
    ArrayList<Project> projects = new ArrayList<>();

    public boolean isLoggedIn(){
        return loggedUser != null;
    }

    public void login(User user){
        loggedUser = user;
    }
    public static void createUser(User user){
        users.add(user);
    }
    public void createProject(Project project){
        projects.add(project);
    }
    public static void createActivity(Project project, Activity activity) throws OperationNotAllowedException{
        if(project.getActivities().size()>=100){
            throw new OperationNotAllowedException("Too many activities");
        }
        project.setActivity(activity);
        activity.setProject(project);
    }
    public boolean hasProject(Project project){
        return projects.contains(project);
    }
    public static boolean hasActivity(Project project,Activity activity){
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
    public static List<User> getUsers(){
        return users;
    }

    public void assignLeader(Project project, User user){

    project.setProjectLeader(user);
    }
    public static void assignActivityToUser(User user, Activity activity) throws OperationNotAllowedException{
        if(user.getActivities().size()>=20){
            throw new OperationNotAllowedException("Maximum of 20 activities are already assigned to user this week");
        }
        if (userHasActivity(activity, user)) {
            throw new OperationNotAllowedException("Worker is already assigned to a project");
        }
        user.joinActivity(activity);
        activity.setActiveUser(user);
    }
    public static List<Activity> getUserActivities(User user){
        return user.getActivities();
    }

    public static boolean userHasActivity(Activity activity,User user){

        List<Activity> userActivities = getUserActivities(user);
        return userActivities.contains(activity);

    }

    public List<User> getUsersWithoutActivities(){
        return null;
        
    }

    public void setActiveDescription(Activity activity, String desciption){

        activity.setDescription(desciption);

    }
    public static void finishActivity(Project project,Activity activity){
        project.setFinishedActivity(activity);
    }
    
}
