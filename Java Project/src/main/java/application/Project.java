package application;

import java.util.ArrayList;
import java.util.Calendar;

public class Project {
    //Class specific varribles
    private String name;
    private String description;
    private Calendar startTime;
    private Calendar endTime;
    private int expTime;
    private Double timeWorked;
    private ArrayList<Activity> activities = new ArrayList<>();
    private ArrayList<Activity> finishedActivities = new ArrayList<>();
    private User projectLeader;
    private ArrayList<User> workers = new ArrayList<>();

    public ArrayList<User> getWorkers() {
        return workers;
    }
    public Project(String name){
        this.name = name;
    }
    //get and set for all varribles
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public int getExpTime() {
        return this.expTime;
    }

    public void setExpTime(int expTime) {
        this.expTime = expTime;
    }

    public double getTimeWorked() {
        return this.timeWorked;
    }

    
    public void finishProject(){
        //do somthing 
    }

    public void giveRapport(){
        //tbd
    }
    public void assignWorker(User user) throws OperationNotAllowedException{
        if (user.getAssignedProject()!=null) {
            workers.add(user);
        } else {
            throw new OperationNotAllowedException("Worker is already assigned to a project");
        }
        
    }
    public void assignWorkers(User[] users) throws OperationNotAllowedException{
        for (int i = 0; i < users.length; i++) {
            if (users[i].getAssignedProject()!=null) {
                workers.add(users[i]);
            } else {
                throw new OperationNotAllowedException("Worker is already assigned to a project");
            }
        }
    }

    public void setActivity(Activity activity){
        activities.add(activity);
    }
    public ArrayList<Activity> getActivities(){
        return activities;
    }
    public void setFinishedActivity(Activity activity){
        finishedActivities.add(activity);
        activities.remove(activity);
    }
    public ArrayList<Activity> getFinishedActivities(){
        return finishedActivities;
    }
    public User getProjectLeader() {
        return projectLeader;
    }
    public void setProjectLeader(User projectLeader) {
        this.projectLeader = projectLeader;
    }

    public void registerTimeWorked(Double timeWorked){
        this.timeWorked += timeWorked;
    }
    

}
