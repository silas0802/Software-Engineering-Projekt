package application;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    Project project;
    StartEndTime startTime;
    StartEndTime endTime;
    String name;
    String description;
    int expectedDuration;
    WorkerTimeList timeWorkedList = new WorkerTimeList();
    List<User> activeUsers = new ArrayList<>();
    boolean activityFinished=false;
    
    //Silas Thule
    public double getWorkedTime(){
        return timeWorkedList.totalTimeWorked();
    }
    //jesper pedersen
    public StartEndTime getStartTime() {
        return startTime;
    }
    //jesper pedersen
    public void setStartTime(StartEndTime startTime) {
        this.startTime = startTime;
    }
    //jesper pedersen
    public StartEndTime getEndTime() {
        return endTime;
    }
    //jesper pedersen
    public void setEndTime(StartEndTime endTime) {
        this.endTime = endTime;
    }
    //jesper pedersen
    public int getExpectedDuration() {
        return expectedDuration;
    }
    //jesper pedersen
    public void setExpectedDuration(int expectedDuration) {
        this.expectedDuration = expectedDuration;
    }
    //jesper pedersen
    public String getDescription() {
        return description;
    }
    //jesper pedersen
    public void setDescription(String description) {
        this.description = description;
    }
    //jesper
    public void setProject(Project project){
        this.project=project;
    }
    //jesper pedersen
    public Project getProject(){
        return project;
    }
    //jesper pedersen
    public void setActiveUser(User user){
        activeUsers.add(user);
    }
    //jesper pedersen
    public List<User> getUsersOnActivity(){
        return activeUsers;
    }
    //jesper pedersen
    public void setName(String name){
        this.name=name;
    }
    //jesper pedersen
    public String getName(){
        return name;
    }
    //jesper pedersen
    public Activity(String name){
        this.name=name;
    }   
    //jesper pedersen
    public void finishActivity(){
        activityFinished=true;
    }
    //jesper pedersen
    public boolean isActivityfinished(){
        return activityFinished;
    }

}


