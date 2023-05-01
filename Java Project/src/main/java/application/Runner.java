package application;

import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Runner {

    static ProjectManagerApp projectManagerApp = new ProjectManagerApp();
    static Scanner scanner;
    static User user;
    public static void main(String[] args) throws OperationNotAllowedException {
        scanner = new Scanner(System.in);
        logIn();
    }
    public static void logIn() throws OperationNotAllowedException{
        newPage();

        System.out.println("System log in");
        System.out.println("type in initials:");
        
        String ini = scanner.nextLine();
        
        
        if(ini.length()==0){
            System.out.println("Please type in initials");
            logIn();
        }
        else if(projectManagerApp.haserUserByName(ini)){
            System.out.println("User wih initials "+ini+" logged ind");
            
            projectManagerApp.login(projectManagerApp.getUserByName(ini));
            mainMenu();
        }else{
            user = new User(ini);
            System.out.println("new User registered");
            System.out.println("User wih initials "+ini+" logged ind");
            projectManagerApp.createUser(user);
            projectManagerApp.login(user);
            mainMenu();
        }
    }
    public static void mainMenu() throws OperationNotAllowedException{
        
            
        
        newPage();
        System.out.println("1. see your activities");
        System.out.println("2. See your projects");
        System.out.println("3. see all projects");
        System.out.println("4. Create new project");
        System.out.println("5. log out");
        System.out.println("6. close app");
        while (true) {
        System.out.println("Type number");
        

        String ans1 = scanner.nextLine();
        int ans;
        try {
            ans=Integer.parseInt(ans1);
        } catch (Exception e) {
            continue;
        }
        //scanner.nextLine();
        if(ans==1){
            seeYourActivities(user);
        }else if(ans==2){

        }else if(ans==3){
            seeAllProjects();
        }else if(ans==4){
            createProject();
        }
        else if(ans==5){
            System.out.println("System logged out");
            logIn();
            
        }else if(ans==6){
            System.out.println("System shutting down");
            System.exit(0);
        }
    }
    }

    public static void seeYourActivities(User user) throws OperationNotAllowedException{
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

    public static void viewActivity(Activity activity) throws OperationNotAllowedException{
        newPage();
        activityDetails(activity);
        System.out.println("");
        System.out.println("");
        System.out.println("0. main menu");
        System.out.println("1. Edit activity");
        System.out.println("2. Register time worked");
        System.out.println("3. Back");
        
        if (activity.getProject().getProjectLeader()==user&&activity.isActivityfinished()==false) {
            System.out.println("4. See total time worked");
            System.out.println("5. Finish project");
        }
        System.out.println("Type number:");
        int ans = scanner.nextInt();
        scanner.nextLine();
        
        if(ans==0){
            mainMenu();
        }else if(ans==1){
            editActivity(activity);
        }else if(ans==2){
            System.out.println("Register time: ex 7.0");
            String time1 =scanner.nextLine();
            double time = Double.parseDouble(time1);
            projectManagerApp.RegisterHours(activity, time);
            System.out.println("Time is Registered");
            viewActivity(activity);
        
        }else if(ans==3){
            seeYourActivities(user);

        }else if(activity.getProject().getProjectLeader()==user&&activity.activityFinished==false){
            if(ans==4){
                seeTimeWorked(activity);
            }else if(ans==5){
                if(yesno("Would you like to finish the activity?")){
                    System.out.println("Activity is now finished");
                    projectManagerApp.finishActivity(activity.getProject(), activity);
                }else{
                    System.out.println("Activity remains unfinished");
                }
            }
        }
        viewActivity(activity);
    }

    public static void seeTimeWorked(Activity activity) throws OperationNotAllowedException{
        newPage();
        System.out.println("Time worked per User");
        System.out.println(activity.timeWorkedList.toString());

        System.out.println("Expected time: "+activity.getExpectedDuration());
        System.out.println("Total time Used: "+activity.timeWorkedList.totalTimeWorked());
        System.out.println("1. Back to activity");
        int ans = scanner.nextInt();
        if (ans==1) {
            viewActivity(activity);
        }
        

    }

    
    public static void activityDetails(Activity activity){
        System.out.println("Activity details on "+activity.getName()+":");
        if(activity.isActivityfinished()){
            System.out.println("***FINISHED***");
        }
        System.out.println("Name: "+activity.getName());
        System.out.println("Belongs to project: "+activity.getProject().getName());
        System.out.println("Description: "+activity.getDescription());
        System.out.println("number of workers on activity: "+activity.getUsersOnActivity().size());
        System.out.println("Expected Work time: "+activity.getExpectedDuration());
        GregorianCalendar calen = activity.getStartTime();
        GregorianCalendar calen2 = activity.getEndTime();
        if(!(calen==null)){
        System.out.println("Start date: "+calen.get(Calendar.DATE)+"-"+calen.get(Calendar.MONTH)+"-"+calen.get(Calendar.YEAR));
        }else{
            System.out.println("Start date: unknown");
        }
        if(!(calen2==null)){
        System.out.println("Set end date: "+calen2.get(Calendar.DATE)+"-"+calen2.get(Calendar.MONTH)+"-"+calen2.get(Calendar.YEAR));
        }else{
            System.out.println("end date: unknown");
        }
    }



    public static void createProject() throws OperationNotAllowedException{
        newPage();
        System.out.println("Creating project");
        System.out.println("Type in project name:");
        String name = scanner.nextLine();
        Project project = new Project(name);
        projectManagerApp.createProject(project);
        
        newPage();
        System.out.println("project succesfully created");
        if(yesno("Create activity under project?")){
            createActivity(project);
        }else{
            System.out.println("returning to main menu");
            mainMenu();
        }
        
    }

    public static void createActivity(Project project) throws OperationNotAllowedException{
        newPage();
        System.out.println("Type in Activity name:");
        String name = scanner.nextLine();
        Activity activity = new Activity(name);
        
        projectManagerApp.createActivity(project, activity);
        newPage();
        System.out.println("Activity succesfulle created");
        
        if(yesno("assign "+user.getUserName()+" to activity?")){
            projectManagerApp.assignActivityToUser(user, activity);
            System.out.println("User assigned to activity");
            mainMenu();
        }else{
            System.out.println("User not assigned");
            mainMenu();
        }
    }

    public static void seeAllProjects() throws OperationNotAllowedException{
        newPage();
        List<Project> projects = projectManagerApp.getProjects();
        if(projects.size()==0){
         System.out.println("no active projects");
         System.out.println("0. Main menu");
         System.out.println("Type number:");
         int ans= scanner.nextInt();
         if(ans==0){
             mainMenu();
         }
        }
        else{
         System.out.println("0. Main menu");
         for (int i = 0; i < projects.size(); i++) {
             System.out.println((i+1)+". "+projects.get(i).getName());
         }
         System.out.println("Type number:");
         int ans= scanner.nextInt();
         if(ans==0){
             mainMenu();
         }else{
         Project project = projects.get(ans-1);
            viewProject(project);
         }
        }
    }

    public static void viewProject(Project project) throws OperationNotAllowedException{
        newPage();
        projectDetails(project);

        System.out.println("");
        System.out.println("");
        System.out.println("0. main menu");
        if(project.getProjectLeader()==null){
            System.out.println("1. Edit project");
            System.out.println("2. Set project leader");
            System.out.println("3. See active activities");
            System.out.println("4. See finished activities");
            System.out.println("5. Workers assigned to project");
        }else{
        System.out.println("1. Edit project");
        System.out.println("2. See active activities");
        System.out.println("3. See finished activities");
        System.out.println("4. Workers assigned to project");
        }

        System.out.println("Type number:");
        int ans = scanner.nextInt();
       if (project.getProjectLeader()==null) {
        if (ans==0) {
            mainMenu();
        }else if(ans==1){
            editProject(project);
        }else if(ans==2){
            setProjectLeaderToProject(project);
        }else if(ans==3){
            seeActiveActivities(project);
        }else if(ans==4){
            seeFinishedActivities(project);
        }else if(ans==5){
            //workers assigned to project
        }
       }else if(project.getProjectLeader()!=null){
        if (ans==0) {
            mainMenu();
        }else if(ans==1){
            editProject(project);
        }else if(ans==2){
            seeActiveActivities(project);
        }else if(ans==3){
            seeFinishedActivities(project);
        }else if(ans==4){
            //workers assigned to project
        }

       }
            
        
    }
    public static void seeActiveActivities(Project project) throws OperationNotAllowedException{
        newPage();
        
        System.out.println("Active activities on: "+project.getName());
        
        
        List<Activity> activities = project.getActivities();
        if (activities.size()==0) {
            System.out.println("no active activites");
            System.out.println("0. Back to project Details");
            System.out.println("Type number:");
            int ans= scanner.nextInt();
        if(ans==0){
             viewProject(project);
         }
        }else{
            System.out.println("0. Back to project Details");
            System.out.println("1. create new activity");
            for (int i = 0; i < activities.size(); i++) {
                System.out.println(i+2+". "+activities.get(i).getName());
            }
        
            System.out.println("Type number to view activity:");
            int ans = scanner.nextInt();
            if (ans==0) {
                viewProject(project);
            }else if(ans==1){
                createActivity(project);
            }else{
                Activity activity = activities.get(ans-2);
                viewActivity(activity);
            }
    }
    }

    public static void seeFinishedActivities(Project project) throws OperationNotAllowedException{
        newPage();
        System.out.println("Finished activities on: "+project.getName());
        
        
        List<Activity> activities = project.getFinishedActivities();
        if (activities.size()==0) {
            System.out.println("no Finished activites");
            System.out.println("0. Back to project Details");
            System.out.println("Type number:");
            int ans= scanner.nextInt();
        if(ans==0){
             viewProject(project);
         }
        }else{
            System.out.println("0. Back to project Details");
        for (int i = 0; i < activities.size(); i++) {
            System.out.println(i+1+". "+activities.get(i).getName());
        }
        
        System.out.println("");
        System.out.println("Type number to view activity:");
        int ans = scanner.nextInt();
        if (ans==0) {
            viewProject(project);
        }else{
            Activity activity = activities.get(ans-1);
            viewActivity(activity);
        }
    }
    }

    public static void setProjectLeaderToProject(Project project) throws OperationNotAllowedException{
        newPage();
        System.out.println("Assign ProjectLeader to: "+project.getName());
        List<User> users = projectManagerApp.getUsers();
        System.out.println("0. Back to Project details");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i+1+". "+users.get(i).getUserName());
        }
        System.out.println("Type Number:");
        int ans = scanner.nextInt();

        if (ans==0) {
            viewProject(project);
        }else{
            User user = users.get(ans-1);
            project.setProjectLeader(user);
            System.out.println("Project Leader is now set to "+user.getUserName());
            viewProject(project);
        }
    }


    public static void projectDetails(Project project){
        System.out.println("Project details on "+project.getName());
        System.out.println("Name: "+project.getName());
        if (project.getProjectLeader()==null) {
            System.out.println("Project Leader: "+project.getProjectLeader());    
        }else{
            System.out.println("Project Leader: "+project.getProjectLeader().getUserName());
        }
        
        
        System.out.println("ID: "+project.getId());
        System.out.println("Description: "+project.getDescription());
        boolean active = project.isFinished();
        if (active) {
            System.out.println("Project finished: yes");
        }else{
            System.out.println("Project finished: No");
        }
        Calendar calen = project.getStartTime();
        Calendar calen2 = project.getEndTime();
        if(!(calen==null)){
            System.out.println("Start date: "+calen.get(Calendar.DATE)+"-"+calen.get(Calendar.MONTH)+"-"+calen.get(Calendar.YEAR));
            }else{
                System.out.println("Start date: unknown");
            }
            if(!(calen2==null)){
            System.out.println("Set end date: "+calen2.get(Calendar.DATE)+"-"+calen2.get(Calendar.MONTH)+"-"+calen2.get(Calendar.YEAR));
            }else{
                System.out.println("end date: unknown");
            }


    }
    public static boolean yesno(String String){
        System.out.println(String+ " (y/n)");
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
    
    //takes input and checks if it is in valid format for date
    public static int[] calenderInputCheck(){
        int[] dato = new int[3];
        while(true){
            System.out.println("DD-MM-YYYY");
            String time = scanner.nextLine();
            String date[] = time.split("-");
            try {
                dato[0] = Integer.parseInt(date[0]);
                dato[1] = Integer.parseInt(date[1]);
                dato[2] = Integer.parseInt(date[2]); 
            } catch (Exception e) {
                continue;
            }
            break;
        }
        return dato;
    }

    public static void editActivity(Activity activity){
        //change name
        if(yesno("Set name?")){
            System.out.println("Name:");
            activity.setName(scanner.nextLine());
        }
        //Description
        if(yesno("Set description?")){
            System.out.println("Description:");
            activity.setDescription(scanner.nextLine());
        }
        //exp dur
        if(yesno("Set expected duration?")){
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
        // start time
        if(yesno("Set start time?")){
            int[] dato = calenderInputCheck();
            activity.setStartTime(new GregorianCalendar(dato[2],dato[1], dato[0]));
        }
        //end time
        if(yesno("Set end time?")){
            int[] dato = calenderInputCheck();
            activity.setEndTime(new GregorianCalendar(dato[2], dato[1], dato[0]));
        }
    }
    
    public static void editProject(Project project) throws OperationNotAllowedException {
        //Name of project
        if(yesno("Set name of project?")){
            System.out.println("Name of project:");
            project.setName(scanner.nextLine());
        }
        //discription of project
        if(yesno("Set project description")){
            System.out.println("Description:");
            project.setDescription(scanner.nextLine());
        }
        // start time
        if(yesno("Set start time?")){
            int[] dato = calenderInputCheck();
            project.setStartTime(new GregorianCalendar(dato[2],dato[1], dato[0]));
        }
        //end time
        if(yesno("Set end time?")){
            int[] dato = calenderInputCheck();
            project.setEndTime(new GregorianCalendar(dato[2], dato[1], dato[0]));
        }
        viewProject(project);
    }
}
