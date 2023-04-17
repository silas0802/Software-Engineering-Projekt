package application;

import java.util.Scanner;

public class Runner {

    ProjectManagerApp projectManagerApp = new ProjectManagerApp();
     static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
    }

    public boolean yesno(String String){
        System.out.println(String);
        char ans =scanner.next().charAt(0);
        if(ans=='y'){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void editActivity(Activity activity){
        if(yesno("Change expected duration? (y/n)")){
           int a = scanner.nextint;
           activity.setExpectedDuration(a);
        }
        else if(yesno("Change start time? (y/n)")){
            System.out.println("DD-MM-YYYY");
            activity.setStartTime(null);
        }
        
    }
}
