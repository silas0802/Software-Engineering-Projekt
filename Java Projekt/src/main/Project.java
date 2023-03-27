package main;

import java.util.Calendar;

public class Project {
    //Class specific varribles
    private String name;
    private String description;
    private Calendar startTime;
    private Calendar endTime;
    private int expTime;
    private int timeWorked;

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

    public int getTimeWorked() {
        return this.timeWorked;
    }

    public void setTimeWorked(int timeWorked) {
        this.timeWorked = timeWorked;
    }

    
    public void finishProject(){
        //do somthing 
    }

    public void giveRapport(){
        //tbd
    }

}
