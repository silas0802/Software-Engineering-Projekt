package main;

import java.util.ArrayList;
import java.util.List;

public class User {
    String userName;
    String timeWorked;
    List<Activity> activities = new ArrayList<Activity>();




    public String user (String userName){

        this.userName = userName;
        return this.userName;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getTimeWorked() {
        return timeWorked;
    }
    public void setTimeWorked(String timeWorked) {
        this.timeWorked = timeWorked;
    }


    void registerTimeWorked(Activity active, int workingHours){
        // ToDO activity.workload(int)
        activities.add(active);
        timeWorked += workingHours;
    }


    
}
