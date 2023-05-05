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
    
    public StartEndTime getStartTime() {
        return startTime;
    }

    public void setStartTime(StartEndTime startTime) {
        this.startTime = startTime;
    }

    public StartEndTime getEndTime() {
        return endTime;
    }

    public void setEndTime(StartEndTime endTime) {
        this.endTime = endTime;
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(int expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProject(Project project){
        this.project=project;
    }

    public Project getProject(){
        return project;
    }
    public void setActiveUser(User user){
        activeUsers.add(user);
    }
    
    public List<User> getUsersOnActivity(){
        return activeUsers;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public Activity(String name){
        this.name=name;
    }   
    public void finishActivity(){
        activityFinished=true;
    }
    public boolean isActivityfinished(){
        return activityFinished;
    }

}


