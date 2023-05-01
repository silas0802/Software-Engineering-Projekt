package application;

import java.util.ArrayList;

public class WorkerTimeList {
    
    ArrayList<WorkerTime> list;

    public WorkerTimeList(){
        list = new ArrayList<>();
    }
    public void registerTime(User user, double time){
        for (WorkerTime workerTime : list) {
            if (workerTime.user.equals(user)) {
                workerTime.time += time;
                return;
            }
        }
        list.add(new WorkerTime(user, time));
    }
    public WorkerTime[] getWorkedTimeList(){
        return list.toArray(new WorkerTime [0]);
    }
    public double getTimeWorked(User user){
        for (WorkerTime workerTime : list) {
            if (workerTime.user.equals(user)) {
                return workerTime.time;
            }
        }
        throw new NullPointerException("User doesn't exist");
    }
    public double totalTimeWorked(){
        double sum = 0;
        for (WorkerTime workerTime : list) {
            sum += workerTime.time;
        }
        return sum;
    }
    public String toString(){
        WorkerTime[] workertimes = getWorkedTimeList();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < workertimes.length; i++) {
            User user = workertimes[i].user;
            double time = workertimes[i].time;
            str.append(i+". "+user.getUserName()+": "+time+"\n");
        }


        return str.toString();
    }
}
