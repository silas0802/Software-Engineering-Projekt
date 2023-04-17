package application;

public class Runner {

    ProjectManagerApp projectManagerApp = new ProjectManagerApp();

    public static void main(String[] args) {
        
    }

    public boolean yesno(String String){
        System.out.println(String);
        return true;
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
