package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Activity {
    Project project;
    GregorianCalendar startTime;
    GregorianCalendar endTime;
    String name;
    String description; 
    int expectedDuration;
    WorkerTimeList timeWorkedList;
    List<User> activeUsers = new ArrayList<>();
    
    public GregorianCalendar getStartTime() {
        Calendar.getInstance();
        return startTime;
    }

    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }

    public void setEndTime(GregorianCalendar endTime) {
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
}


