package application;

import java.util.List;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Runner {

    static ProjectManagerApp projectManagerApp = new ProjectManagerApp();
     static Scanner scanner;
    static User user;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        logIn();
    }
    public static void logIn(){
        newPage();

        System.out.println("System log in");
        System.out.println("type in initials:");
        
        String ini = scanner.nextLine();
         user = new User(ini);
        if(projectManagerApp.hasUser(user)){
            System.out.println("User wih initials "+ini+" logged ind");
            projectManagerApp.login(user);
            mainMenu();
        }else{
            System.out.println("new User registered");
            System.out.println("User wih initials "+ini+" logged ind");
            projectManagerApp.createUser(user);
            projectManagerApp.login(user);
            mainMenu();
        }
    }
    public static void mainMenu(){
        newPage();
        System.out.println("1. see your activities");
        System.out.println("2. See your projects");
        System.out.println("3. see all projects");
        System.out.println("4. Create new project");
        System.out.println("5. log out");
        System.out.println("Type number");
        Activity ag = new Activity("jesper er sej");
        ag.setActiveUser(user);
        ag.setDescription("sex");
        ag.setExpectedDuration(19);
        user.joinActivity(ag);
        int ans = scanner.nextInt();
        scanner.nextLine();
        if(ans==1){
            seeYourActivities(user);
        }else if(ans==2){

        }else if(ans==3){

        }else if(ans==4){

        }
        else if(ans==5){
            System.out.println("Sytem logged out");
            logIn();
            scanner.nextLine();
        }
    }

    public static void seeYourActivities(User user){
        newPage();
       List<Activity> activities = user.getActivities();
       if(activities.size()==0){
        System.out.println("No assigned activites");
        System.out.println("0. Main menu");
        System.out.println("Type number:");
        int ans= scanner.nextInt();
        if(ans==0){
            mainMenu();
        }
       }
       else{
        System.out.println("0. Main menu");
        for (int i = 0; i < activities.size(); i++) {
            System.out.println((i+1)+". "+activities.get(i).getName());
        }
        System.out.println("Type number:");
        int ans= scanner.nextInt();
        if(ans==0){
            mainMenu();
        }else{
        Activity activity = activities.get(ans-1);
        viewActivity(activity);
        }
       }
    }

    public static void viewActivity(Activity activity){
        newPage();
        System.out.println("Activity details on "+activity.getName()+":");
        System.out.println("Name: "+activity.getName());
        System.out.println("Belongs to project: "+activity.getProject());
        System.out.println("Description: "+activity.getDescription());
        System.out.println("number of workers on activity: "+activity.getUsersOnActivity().size());
        System.out.println("Expected Work time: "+activity.getExpectedDuration());
        System.out.println("Start date: "+activity.getStartTime());
        System.out.println("Set end date: "+activity.getEndTime());
        System.out.println("");
        System.out.println("");
        System.out.println("0. main menu");
        System.out.println("1. Edit activity");
        System.out.println("2. back");
        System.out.println("type number:");
        int ans = scanner.nextInt();
        if(ans==0){
            mainMenu();
        }else if(ans==1){
            editActivity(activity);
        }else if(ans==2){
            seeYourActivities(user);
        }
    }

    public static boolean yesno(String String){
        System.out.println(String);
        char ans =scanner.next().charAt(0);
        scanner.nextLine();
        if(ans=='y'){
            return true;
        }
        else{
            return false;
        }
    }
    public static void newPage(){
        System.out.println("");
        System.out.println("");
        System.out.println("----------------------------------------------------------------");
    }
    
    public static void editActivity(Activity activity){
        if(yesno("Set expected duration? (y/n)")){
            String weekString;
            int weekInt;
            while(true){
                System.out.println("What is the expected number of weeks? (int)");
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
                System.out.println("DD-MM-YYYY");
                String time = scanner.nextLine();
                String date[] = time.split("-");
                try {
                    day = Integer.parseInt(date[0]);
                    month = Integer.parseInt(date[1]);
                    year = Integer.parseInt(date[2]); 
                    activity.setStartTime(new GregorianCalendar(year, month, day));           
                } catch (Exception e) {
                    continue;
                }
                break;
            }
            
        }
        
    }
}
