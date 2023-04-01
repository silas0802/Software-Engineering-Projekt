package application;

import java.util.ArrayList;
import java.util.List;

public class User {
    String userName;
    double timeWorked;
    List<Activity> activities = new ArrayList<Activity>();




    public User(String userName){

        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public double getTimeWorked() {
        return timeWorked;
    }

    public void joinActivity(Activity activity){
        activities.add(activity);
    }
    public List<Activity> getActivities(){
        return activities;
    }

    public void registerTimeWorked(Double workingHours){
        timeWorked += workingHours;
    }


    
}
