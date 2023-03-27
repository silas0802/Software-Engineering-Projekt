package application;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Activity {
    Project project;
    GregorianCalendar startTime;
    GregorianCalendar endTime;
    String name;
    int expectedDuration;
    
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

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    String describtion; 

    public void setProject(Project project){
        this.project=project;
    }

    public Project getProject(){
        return project;
    }

    public Activity(String name){
        this.name=name;
        

    }   


}


