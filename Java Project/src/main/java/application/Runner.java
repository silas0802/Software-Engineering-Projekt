package application;

import java.time.Year;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Runner {

    ProjectManagerApp projectManagerApp = new ProjectManagerApp();
     static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Project project = new Project("TestTest");
        project.setActivity(new Activity("test"));
        Activity ac = project.searchActivity("test");
        editActivity(ac);
    }

    public static boolean yesno(String String){
        System.out.println(String);
        char ans =scanner.next().charAt(0);
        if(ans=='y'){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void editActivity(Activity activity){
        if(yesno("Set expected duration? (y/n)")){
            String weekString;
            int weekInt;
            System.out.println("What is the expected number of weeks? (int)");
            while(true){
                try {
                weekString = scanner.nextLine();
                weekInt = Integer.parseInt(weekString);
                } catch (Exception e) {
                    continue;
                }
                break;
            }
            activity.setExpectedDuration(weekInt);
        }
        if(yesno("Set start time? (y/n)")){
            int day;
            int month;
            int year;
            while(true){
                String time = scanner.nextLine();
                String date[] = time.split("-");
                try {
                    day = Integer.parseInt(date[0]);
                    month = Integer.parseInt(date[1]);
                    year = Integer.parseInt(date[2]); 
                    activity.setStartTime(new GregorianCalendar(year, month, day));           
                } catch (Exception e) {
                    System.out.println("DD-MM-YYYY");
                    continue;
                }
                break;
            }
            
        }
        
    }
}
