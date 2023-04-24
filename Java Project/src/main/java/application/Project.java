package application;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

public class Project {
    //Class specific varribles
    private static int idCounter =1;
    private static int idYear = Year.now().getValue();
    private int id;
    private String name;
    private String description;
    private Calendar startTime;
    private Calendar endTime;
    private boolean isFinished;
    private ArrayList<Activity> activities = new ArrayList<>();
    private ArrayList<Activity> finishedActivities = new ArrayList<>();
    private User projectLeader;
    private ArrayList<User> workers = new ArrayList<>();

    public ArrayList<User> getWorkers() {
        return workers;
    }
    public Project(String name){
        this.name = name;
        if (idYear != Year.now().getValue()) {
            idCounter=1;
        }
        this.id = (Year.now().getValue() % 100)*1000+idCounter;
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
        int sum = 0;
        for (Activity activity : activities) {
            sum += activity.getExpectedDuration();
        }
        return sum;
    }

    public double getTimeWorked() {
        int sum = 0;
        for (Activity activity : activities) {
            sum += activity.timeWorkedList.totalTimeWorked();
        }
        return sum;
    }
    public boolean isFinished() {
        return isFinished;
    }
    
    public void finishProject(){
        isFinished = true;
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

    public Activity searchActivity(String name){
        ArrayList<Activity> activ = getActivities();
        for(int i = 0; i < activ.size(); i++){
            if(activ.get(i).getName().contains(name)){
                return activ.get(i);
            }
        }
        return null;
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

    
    

}
