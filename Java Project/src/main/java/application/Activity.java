package application;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    Project project;
    int startTimeWeek;
    int startTimeYear;
    int endTimeWeek;
    int endTimeYear;
    String name;
    String description; 
    int expectedDuration;
    WorkerTimeList timeWorkedList;
    List<User> activeUsers = new ArrayList<>();
    boolean activityFinished=false;
    
    public int getStartTimeWeek() {
        return startTimeWeek;
    }

    public void setStartTimeWeek(int startTimeWeek) {
        this.startTimeWeek = startTimeWeek;
    }

    public int getStartTimeYear() {
        return startTimeYear;
    }

    public void setStartTimeYear(int startTimeYear) {
        this.startTimeYear = startTimeYear;
    }

    public int getEndTimeWeek() {
        return endTimeWeek;
    }

    public void setEndTimeWeek(int endTimeWeek) {
        this.endTimeWeek = endTimeWeek;
    }

    public int getEndTimeYear() {
        return endTimeYear;
    }

    public void setEndTimeYear(int endTimeYear) {
        this.endTimeYear = endTimeYear;
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


