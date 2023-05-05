package application;

import java.util.ArrayList;
import java.util.List;
    /**
     * @author Niclas
     * @param User
     * @return
     */
public class User {
    String userName;
    double timeWorked;
    Project assignedProject;
    List<Activity> activities = new ArrayList<Activity>();
    
    ProjectManagerApp project = new ProjectManagerApp();


    
    public User(String userName){

        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public double getTimeWorked() {
        return timeWorked;
    }

    public void joinActivity(Activity activity){
        activities.add(activity);
    }
    public List<Activity> getActivities(){
        return activities;
    }

    public void registerTimeWorked(Double workingHours){
        timeWorked += workingHours;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }
    
}
