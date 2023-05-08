package application;

import java.time.Year;
import java.util.ArrayList;
/**
 * @author Silas Thule
 */
public class Project {
    //Class specific varribles
    private static int idCounter =1;
    private static int idYear = Year.now().getValue();
    private int id;
    private String name;
    private String description;
    private StartEndTime startTime;
    private StartEndTime endTime;
    private boolean isFinished;
    private ArrayList<Activity> activities = new ArrayList<>();
    private ArrayList<Activity> finishedActivities = new ArrayList<>();
    private User projectLeader;
    private ArrayList<User> workers = new ArrayList<>();

    public ArrayList<User> getWorkers() {
        return workers;
    }
    //Silas Thule
    public Project(String name){
        this.name = name;
        if (idYear != Year.now().getValue()) {
            idCounter=1;
        }
        this.id = (Year.now().getValue() % 100)*1000+idCounter;
        idCounter++;
    }
    //get and set for all varribles 
    //Anton Ekman
    public String getName() {
        return this.name;
    }
    //Anton Ekman
    public void setName(String name) {
        this.name = name;
    }
    //Anton Ekman
    public String getDescription() {
        return this.description;
    }
    //Anton Ekman
    public void setDescription(String description) {
        this.description = description;
    }
    //Anton Ekman
    public StartEndTime getStartTime() {
        return startTime;
    }
    //Anton Ekman
    public void setStartTime(StartEndTime startTime) {
        this.startTime = startTime;
    }
    //Anton Ekman
    public StartEndTime getEndTime() {
        return endTime;
    }
    //Anton Ekman
    public void setEndTime(StartEndTime endTime) {
        this.endTime = endTime;
    }
    //Silas Thule
    public int getExpTime() {
        int sum = 0;
        for (Activity activity : activities) {
            sum += activity.getExpectedDuration();
        }
        return sum;
    }
    public int getId() {
        return id;
    }
    //Silas Thule
    public double getTimeWorked() {
        int sum = 0;
        for (Activity activity : activities) {
            sum += activity.timeWorkedList.totalTimeWorked();
        }
        return sum;
    }
    //jesper pedersen
    public boolean isFinished() {
        return isFinished;
    }
    //jesper pedersen
    public void finishProject(){
        isFinished = true;
    }
    
    //Silas Thule
    public void assignWorker(User user) throws OperationNotAllowedException{
        if (user.getAssignedProject()!=null) {
            workers.add(user);
        } else {
            throw new OperationNotAllowedException("Worker is already assigned to a project");
        }
        
    }
    //Silas Thule
    public void assignWorkers(User[] users) throws OperationNotAllowedException{
        for (int i = 0; i < users.length; i++) {
            if (users[i].getAssignedProject()!=null) {
                workers.add(users[i]);
            } else {
                throw new OperationNotAllowedException("Worker is already assigned to a project");
            }
        }
    }
    //Anton Ekman
    public void setActivity(Activity activity){
        activities.add(activity);
    }
    //Anton Ekman
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
    //jesper pedersen
    public void setFinishedActivity(Activity activity){
        finishedActivities.add(activity);
        activities.remove(activity);
    }
    //jesper pedersen
    public ArrayList<Activity> getFinishedActivities(){
        return finishedActivities;
    }
    //jesper pedersen
    public User getProjectLeader() {
        return projectLeader;
    }
    //jesper pedersen
    public void setProjectLeader(User projectLeader) {
        this.projectLeader = projectLeader;
    }

    
    public static void setIdCounter(int nb){
        idCounter = nb;
    }
    public static void setIdYearCounter(int nb){
        idYear = nb;
    }

}
