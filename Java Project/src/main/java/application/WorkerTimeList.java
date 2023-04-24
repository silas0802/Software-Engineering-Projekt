package application;

import java.util.ArrayList;

public class WorkerTimeList {
    
    ArrayList<WorkerTime> list;

    public WorkerTimeList(){
        list = new ArrayList<>();
    }
    public void registerTime(User user, int time){
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
    public int getTimeWorked(User user){
        for (WorkerTime workerTime : list) {
            if (workerTime.user.equals(user)) {
                return workerTime.time;
            }
        }
        throw new NullPointerException("User doesn't exist");
    }
    public int totalTimeWorked(){
        int sum = 0;
        for (WorkerTime workerTime : list) {
            sum += workerTime.time;
        }
        return sum;
    }
}
