package application;

import java.util.List;
import java.util.Scanner;


public class Runner {

    static ProjectManagerApp projectManagerApp = new ProjectManagerApp();
    static Scanner scanner;
    static User user;
    public static void main(String[] args){
        scanner = new Scanner(System.in);
        logIn();
        
    }

    
    public static void dummies(int workers, int projects, int activities){
        for (int i = 1; i <= workers; i++) {
            
            projectManagerApp.createUser(new User("Worker "+i));
        }
        for (int i = 1; i <= projects; i++) {
            projectManagerApp.createProject(new Project("Project "+i));
        }
        List<Project> proj = projectManagerApp.getProjects();
        
            for(Project p: proj){
                for (int i = 1; i <= activities; i++) {
                    try {
                        projectManagerApp.createActivity(p,new Activity("Activity "+p.getName()+" "+i));
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                       
                    }
                        
                }
                
            }
        
    }

    public static void logIn(){
        newPage();

        System.out.println("SYSTEM LOGIN");
        System.out.println("Please enter your initials:");
        
        String ini;
        
        while(true){
            ini = scanner.nextLine();

            if(ini.length() < 1 || ini.length() > 4){
                System.out.println("Please enter up to 4 initials:");
                continue;
            }
            else if(projectManagerApp.hasUserByName(ini)){
                System.out.println("User with initials "+ini+" logged ind");
                
                projectManagerApp.login(projectManagerApp.getUserByName(ini));
                mainMenu();
            }
            else if(yesno("Do you wanna create a new user with initials " + ini + "?")){
                user = new User(ini);
                System.out.println("New User registered");
                System.out.println("User with initials "+ini+" logged ind");
                projectManagerApp.createUser(user);
                projectManagerApp.login(user);
                mainMenu();
            }
            else{
                System.out.println("Please enter your initials:");
            }
        }
    }

    public static void mainMenu(){
        newPage();
        System.out.println("MAIN MENU");
        System.out.println("1. See your activities");
        System.out.println("2. See all projects");
        System.out.println("3. Create new project");
        System.out.println("4. Log out");
        System.out.println("5. Close app");
        String ansString;
        int ans;

        while (true) {
            System.out.println("Enter a number:");
            ansString = scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }
            if(ans==1){
                seeYourActivities(user);
            }
            else if(ans==2){
                seeAllProjects();
            }
            else if(ans==3){
                createProject();
            }
            else if(ans==4){
                System.out.println("User logged out");
                logIn();
            }
            else if(ans==5){
                System.out.println("System shutting down");
                System.exit(0);
            }
        }
    }

    public static void seeYourActivities(User user){
        String ansString;
        int ans;
        Activity activity;
        List<Activity> activities = user.getActivities();

        newPage();
        System.out.println("MY ACTIVITIES");
        if(activities.size()==0){
            System.out.println("No assigned activites");
            System.out.println("0. Main menu");
            while(true){
                System.out.println("Enter a number:");
                ansString = scanner.nextLine();
                try {
                    ans=Integer.parseInt(ansString);
                } catch (Exception e) {
                    continue;
                }
                if(ans==0){
                    mainMenu();
                }
            }
        }
        System.out.println("0. Main menu");
        System.out.println();
        System.out.println("---Activities---");
        for (int i = 0; i < activities.size(); i++) {
            System.out.println((i+1)+". "+activities.get(i).getName());
        }
        while(true){
            System.out.println("Enter a number:");
            ansString = scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }
            if(ans==0){
                mainMenu();
            } 
            try {
                activity = activities.get(ans-1);
            } catch (Exception e) {
                System.out.println("Please select an activity from the list");
                continue;
            }
            viewActivity(activity);
        }
    }

    public static void viewActivity(Activity activity){
        String ansString;
        int ans;

        newPage();
        activityDetails(activity);
        System.out.println();
        System.out.println();
        System.out.println("0. Main menu");
        System.out.println("1. Back");
        System.out.println("2. Edit activity");
        System.out.println("3. Register time worked");
        if (activity.getProject().getProjectLeader() == user && activity.isActivityfinished() == false) {
            System.out.println("4. Users assigned to activity");   
            System.out.println("5. See total time worked");
            System.out.println("6. Finish activity");
        }

        while(true){
            System.out.println("Enter a number:");
            ansString = scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }

            if(ans==0){
                mainMenu();
            } else if(ans==1){
                seeYourActivities(user);
            } else if(ans==2){
                editActivity(activity);
            } else if(ans==3){
                System.out.println("Register time: ex 7.0");
                String timeString;
                double time;
    
                while(true){
                    timeString = scanner.nextLine();
                    try {
                        time = Double.parseDouble(timeString);
                    } catch (Exception e) {
                        System.out.println("Please enter a valid number:");
                        continue;
                    }
                    try {
                        projectManagerApp.registerHours(activity, time);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                }
                System.out.println("Time is Registered");
                viewActivity(activity);
            
            } else if(activity.getProject().getProjectLeader() == user && activity.activityFinished == false){
                if(ans==4){
                    usersAssignedToActivity(activity, activity.getProject());
                }
                if(ans==5){
                    seeTimeWorked(activity);
                }else if(ans==6){
                    if(yesno("Would you like to finish the activity?")){
                        System.out.println("Activity is now finished");
                        projectManagerApp.finishActivity(activity.getProject(), activity);
                        viewActivity(activity);
                    }
                    System.out.println("Activity remains unfinished");
                    viewActivity(activity);
                }
            }
        } 
    }

    public static void usersAssignedToActivity(Activity activity,Project project){
        String ansString;
        int ans;
        newPage();
        System.out.println("Users assigned to activity "+activity.getName());

        if(activity.getUsersOnActivity().size()==0){
            System.out.println("***No users assigned to activity***");
        }
        System.out.println("0. back");
        System.out.println("1. assign Users to activity");

        if(activity.getUsersOnActivity().size()>0){
            System.out.println("Number of people assigned to activity: "+activity.getUsersOnActivity().size());
            for (int i = 0; i < activity.getUsersOnActivity().size(); i++) {
                System.out.println("*  "+activity.getUsersOnActivity().get(i).getUserName());
            }
        }
        while (true) {
            System.out.println("Enter a number:");
            ansString = scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }    
        
        
            if(ans==0){
                viewActivity(activity);
            }
            else if(ans==1){
                assignUserToActivity(activity,activity.getProject());
            }
        }
    }

    public static void assignUserToActivity(Activity activity,Project project){
        String ansString;
        int ans;
        newPage();
        System.out.println("Assign Users to activity: "+activity.getName());
        System.out.println("0. back");
        for (int i = 0; i < project.getWorkers().size(); i++) {
            if(projectManagerApp.userHasActivity(activity, project.getWorkers().get(i))){
                continue;
            }
            System.out.println(i+1+". "+project.getWorkers().get(i).getUserName());            
        }


        while (true) {
            
            System.out.println("Type number to assign User:");
            ansString = scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }
        
        
        if(ans==0){
            usersAssignedToActivity(activity, activity.getProject());
        }
        else{
            try {
                
                projectManagerApp.assignActivityToUser(project.getWorkers().get(ans-1), activity);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            System.out.println("***User assigned to Activity***");
            assignUserToActivity(activity,activity.getProject());
        }
    }
    }

    public static void seeTimeWorked(Activity activity){
        String ansString;
        int ans;

        newPage();
        System.out.println("Time worked per User");
        System.out.println(activity.timeWorkedList.toString());

        System.out.println("Expected time: "+activity.getExpectedDuration());
        System.out.println("Total time Used: "+activity.timeWorkedList.totalTimeWorked());
        System.out.println("0. Main menu");
        System.out.println("1. Back to activity");
        while(true){
            System.out.println("Enter a number:");
            ansString = scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }
            if(ans==0){
                mainMenu();
            }
            if (ans==1) {
                viewActivity(activity);
            }
        }
    }

    
    public static void activityDetails(Activity activity){
        if(activity.isActivityfinished()){
            System.out.println("***FINISHED***");
        }
        System.out.println("Name: "+activity.getName());
        System.out.println("Belongs to project: "+activity.getProject().getName());
        System.out.println("Description: "+activity.getDescription());
        System.out.println("Number of workers on activity: "+activity.getUsersOnActivity().size());
        System.out.println("Expected Work time: "+activity.getExpectedDuration());
        StartEndTime startTime = activity.getStartTime();
        StartEndTime endTime = activity.getEndTime();
        
        if(!(startTime == null)){
            System.out.println("Start date: W" + startTime.getWeek() + "-" + startTime.getYear());
        } else{
            System.out.println("Start date: unknown");
        }

        if(!(endTime == null)){
            System.out.println("End date: W" + endTime.getWeek() + "-" + endTime.getYear());
        } else{
            System.out.println("End date: unknown");
        }
    }

    public static void createProject(){
        newPage();
        System.out.println("Creating project");
        System.out.println("Enter project name:");
        String name = scanner.nextLine();
        Project project = new Project(name);
        projectManagerApp.createProject(project);
        
        newPage();
        System.out.println("Project succesfully created");
        if(yesno("Create activity in project?")){
            createActivity(project);
        } else{
            System.out.println("Returning to main menu");
            mainMenu();
        }
    }

    public static void createActivity(Project project){
        newPage();
        System.out.println("Enter activity name:");
        String name = scanner.nextLine();
        Activity activity = new Activity(name);
        
        try {
            projectManagerApp.createActivity(project, activity);
        } catch (OperationNotAllowedException e) {
            System.out.println(e.getMessage());
            seeActiveActivities(project);
        }
        
        newPage();
        System.out.println("Activity created succesfully");
        
        if(yesno("Assign "+user.getUserName()+" to activity?")){
            try {
                projectManagerApp.assignActivityToUser(user, activity);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
                seeActiveActivities(project);
            }
            System.out.println("User assigned to the activity");
            seeActiveActivities(project);
        } else{
            System.out.println("User not assigned");
            seeActiveActivities(project);
        }
    }

    public static void seeAllProjects(){
        List<Project> projects = projectManagerApp.getProjects();
        String ansString;
        int ans;
        Project project;

        newPage();
        System.out.println("ALL PROJECTS");
        if(projects.size()==0){
            System.out.println("No active projects");
            System.out.println("0. Main menu");

            while(true){
                System.out.println("Enter a number:");
                ansString = scanner.nextLine();
                try {
                    ans=Integer.parseInt(ansString);
                } catch (Exception e) {
                    continue;
                }
                if(ans==0){
                    mainMenu();
                }
            }
        }
        System.out.println("0. Main menu");
        System.out.println();
        System.out.println("---Projects---");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i+1)+". "+projects.get(i).getName());
        }
        while(true){
            System.out.println("Enter a number:");
            ansString = scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }
            if(ans==0){
                mainMenu();
            } else{
                try {
                    project = projects.get(ans-1);
                } catch (Exception e) {
                    System.out.println("Please select a project from the list");
                    continue;
                }
                viewProject(project);
            }
        }
            
        
    }

    public static void viewProject(Project project){
        String ansString;
        int ans;

        newPage();
        projectDetails(project);
        System.out.println();
        System.out.println();
        System.out.println("0. Main menu");
        System.out.println("1. Back");
        if(project.getProjectLeader() == null){
            System.out.println("2. Edit project");
            System.out.println("3. Set project leader");
            System.out.println("4. See active activities");
            System.out.println("5. See finished activities");
            System.out.println("6. Workers assigned to project");
        } else{
            System.out.println("2. Edit project");
            System.out.println("3. See active activities");
            System.out.println("4. See finished activities");
            System.out.println("5. Workers assigned to project");
            if(project.getProjectLeader() == user && project.isFinished() == false){
                System.out.println("6. Finish project");
            }
        }
        while(true){
            System.out.println("Enter a number:");
            ansString = scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }
            if (project.getProjectLeader()==null) {
                if (ans==0) {
                    mainMenu();
                }
                else if(ans==1){
                    seeAllProjects();
                }
                else if(ans==2){
                    editProject(project);
                }
                else if(ans==3){
                    setProjectLeaderToProject(project);
                }
                else if(ans==4){
                    seeActiveActivities(project);
                }
                else if(ans==5){
                    seeFinishedActivities(project);
                }
                else if(ans==6){
                    workersAssignedToProject(project);
                }
            }else if(project.getProjectLeader()!=null){
                if (ans==0) {
                    mainMenu();
                }
                else if(ans==1){
                    editProject(project);
                }
                else if(ans==2){
                    seeAllProjects();
                }
                else if(ans==3){
                    seeActiveActivities(project);
                }
                else if(ans==4){
                    seeFinishedActivities(project);
                }
                else if(ans==5){
                    workersAssignedToProject(project);
                }
                else if (ans==6 && project.isFinished()==false){
                    if(yesno("Do you wanne mark the project as finished?")){
                        try {
                            projectManagerApp.finishProject(project);
                            viewProject(project);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                            viewProject(project);
                        }   
                    } else{
                        System.out.println("Project remains unfinished");
                        viewProject(project);
                    }
                }
            }
        }
    }


    public static void workersAssignedToProject(Project project){
        String ansString;
        int ans;
        newPage();
        System.out.println("Workers assigned to project "+project.getName());
        if (project.getWorkers().size()==0) {
            System.out.println("No workers assigned to project");
        }else{
            for (int i = 0; i < project.getWorkers().size(); i++) {
                System.out.println("*  "+project.getWorkers().get(i).getUserName());
            }
        }

        System.out.println("0. back to project");
        System.out.println("1. assign workers to project");
        while (true) {
            System.out.println("Type number:");
            
            ansString=scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }

            if (ans==0) {
                viewProject(project);
            }
            else if(ans==1){
                assignUserToProject(project);
            }
        }
    }

    public static void assignUserToProject(Project project){
        String ansString;
        int ans;
        newPage();
        System.out.println("Assign Users to activity: "+project.getName());
        
        if(projectManagerApp.users.size()==0){
            System.out.println("no workers registered");
            System.out.println("0. back");
        }
        else{
            System.out.println("0. back");
            for (int i = 0; i < projectManagerApp.getUsers().size(); i++) {
                
                if(projectManagerApp.userHasProject(projectManagerApp.getUsers().get(i), project)||project.getProjectLeader()==projectManagerApp.getUsers().get(i)){
                    continue;
                }
                System.out.println(i+1+". "+projectManagerApp.getUsers().get(i).getUserName());            
            }
        }
        User user;
        while (true) {
            
            System.out.println("Type number to assign User:");
            ansString = scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }
        
            
        if(ans==0){
            workersAssignedToProject(project);
        }
        else{
            user=projectManagerApp.users.get(ans-1);
            try {
                user.setAssignedProject(project);
                project.assignWorker(user);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            
            System.out.println("***User assigned to Project***");
            assignUserToProject(project);
        }
    }
    }
    public static void seeActiveActivities(Project project){
        String ansString;
        int ans;
        Activity activity;
        List<Activity> activities = project.getActivities();

        newPage();
        System.out.println("ACTIVE ACTIVITIES - " + project.getName());
        if (activities.size()==0) {
            System.out.println("No active activites");
            System.out.println("0. Main menu");
            System.out.println("1. Back to project details");
            System.out.println("2. Create new activity");
            while(true){
                System.out.println("Enter a number:");
                ansString = scanner.nextLine();
                try {
                    ans=Integer.parseInt(ansString);
                } catch (Exception e) {
                    continue;
                }
                if(ans==0){
                    mainMenu();
                }
                else if(ans==1){
                    viewProject(project);
                }
                else if(ans==2){
                    createActivity(project);
                }
            }   
        }
        System.out.println("0. Main menu");
        System.out.println("1. Back to project Details");
        System.out.println("2. Create new activity");
        System.out.println();
        System.out.println("---Activities---");
        for (int i = 0; i < activities.size(); i++) {
            System.out.println(i+3+". "+activities.get(i).getName());
        }
        System.out.println();
        while(true){
            System.out.println("Enter a number:");
            ansString = scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }
            if(ans==0){
                mainMenu();
            } else if (ans==1) {
                viewProject(project);
            } else if(ans==2){
                createActivity(project);
            }
            try {
                activity = activities.get(ans-3);
            } catch (Exception e) {
                System.out.println("Please select an activity from the list");
                continue;
            }
            viewActivity(activity);
        }
    }

    public static void seeFinishedActivities(Project project){
        String ansString;
        int ans;
        Activity activity;
        List<Activity> activities = project.getFinishedActivities();

        newPage();
        System.out.println("FINISHED ACTIVITIES - " + project.getName());
        if (activities.size()==0) {
            System.out.println("No Finished activites");
            System.out.println("0. Main menu");
            System.out.println("1. Back to project details");
            while(true){
                System.out.println("Enter a number:");
                ansString = scanner.nextLine();
                try {
                    ans=Integer.parseInt(ansString);
                } catch (Exception e) {
                    continue;
                }
                if(ans==0){
                    mainMenu();
                } else if(ans==1){
                    viewProject(project);
                }
            }   
        }
            System.out.println("0. Main menu");
            System.out.println("1. Back to project details");
            System.out.println();
            System.out.println("---Activities---");
            for (int i = 0; i < activities.size(); i++) {
                System.out.println(i+2+". "+activities.get(i).getName());
            }
            System.out.println();
            while(true){
                System.out.println("Enter a number:");
                ansString = scanner.nextLine();
                try {
                    ans=Integer.parseInt(ansString);
                } catch (Exception e) {
                    continue;
                }
                if (ans==0) {
                    mainMenu();
                }else if(ans==1){
                    viewProject(project);
                } else{
                    activity = activities.get(ans-2);
                    viewActivity(activity);
            }
        }
    }

    public static void setProjectLeaderToProject(Project project){
        String ansString;
        int ans;
        List<User> users = projectManagerApp.getUsers();
        User user;

        newPage();
        System.out.println("ASSIGN PROJECT MANAGER - " + project.getName());
        System.out.println("0. Main menu");
        System.out.println("1. Back to Project details");
        System.out.println();
        System.out.println("---Users---");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i+2+". "+users.get(i).getUserName());
        }
        while(true){
            System.out.println("Enter a number:");
            ansString = scanner.nextLine();
            try {
                ans=Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }
            if (ans==0) {
                mainMenu();
            } else if(ans==1){
                viewProject(project);
            }
            user = users.get(ans-2);
            if(yesno("Do you want to assign " + user.getUserName() + " as the project manager for " + project.getName() +"?")){
                projectManagerApp.assignLeader(project, user);
                System.out.println(user.getUserName() + " is now the project manager");
                viewProject(project);
            }
            viewProject(project);
        }
    }

    public static void projectDetails(Project project){
        if(project.isFinished()){
            System.out.println("***FINISHED***");
        }
        System.out.println("Project name: "+project.getName());
        if (project.getProjectLeader()==null) {
            System.out.println("Project manager: "+project.getProjectLeader());    
        }
        else{
            System.out.println("Project manager: "+project.getProjectLeader().getUserName());
        }
        
        System.out.println("ID: "+project.getId());
        System.out.println("Description: "+project.getDescription());
        StartEndTime startTime = project.getStartTime();
        StartEndTime endTime = project.getEndTime();

        if(!(startTime == null)){
            System.out.println("Start date: W" + startTime.getWeek() + "-" + startTime.getYear());
        } else{
            System.out.println("Start date: unknown");
        }

        if(!(endTime == null)){
            System.out.println("End date: W" + endTime.getWeek() + "-" + endTime.getYear());
        } else{
            System.out.println("End date: unknown");
        }
    }
    
    public static boolean yesno(String String){
        System.out.println(String+ " (y/n)");
        char ans = scanner.nextLine().charAt(0);
        if(ans=='y'){
            return true;
        } else{
            return false;
        }
    }

    public static void newPage(){
        System.out.println("");
        System.out.println("");
        System.out.println("----------------------------------------------------------------");
    }

    public static void editActivity(Activity activity){
        newPage();
        System.out.println("EDIT ACTIVITY");
        System.out.println("0. Main menu");
        System.out.println("1. Back to activity details");
        System.out.println("2. Edit name");
        System.out.println("3. Edit description");
        System.out.println("4. Edit duration");
        System.out.println("5. Edit start time");
        System.out.println("6. Edit end time");
        String ansString;
        int ans;
        while(true){
            System.out.println("Enter a number:");  
            ansString = scanner.nextLine();
            try {
                ans = Integer.parseInt(ansString);
            } catch (Exception e) {
                continue;
            }
            if(ans == 0){
                mainMenu();
            }
            else if(ans == 1){
                viewActivity(activity);
            }
            //change name
            else if(ans == 2){
                System.out.println("Name:");
                activity.setName(scanner.nextLine());
                System.out.println("Name updated");
                editActivity(activity);
            }
            //Description
            else if(ans == 3){
                System.out.println("Description:");
                activity.setDescription(scanner.nextLine());
                System.out.println("Description updated");
                editActivity(activity);
            }
            //exp dur
            else if(ans == 4){
                String weekString;
                int weekInt;
                System.out.println("Enter expected number of hours? ex 2");
                while(true){
                    weekString = scanner.nextLine();
                    try {
                        weekInt = Integer.parseInt(weekString);
                    } catch (Exception e) {
                        System.out.println("Please enter a whole number:");
                        continue;
                    }
                    break;
                }
                activity.setExpectedDuration(weekInt);
                System.out.println("Duration updated");
                editActivity(activity);
            }
            // start time
            else if(ans == 5){
                System.out.println("Please enter time on the form:");
                while(true){
                    System.out.println("WW-YYYY");
                    try {
                        projectManagerApp.setActivityStartTime(activity, projectManagerApp.timeInputChecker(scanner.nextLine()));
                    } catch (OperationNotAllowedException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                }
                System.out.println("Start date updated");
                editActivity(activity);
            }
            //end time
            else if(ans == 6){
                System.out.println("Please enter time on the form:");
                while(true){
                    System.out.println("WW-YYYY");
                    try {
                        projectManagerApp.setActivityEndTime(activity, projectManagerApp.timeInputChecker(scanner.nextLine()));
                    } catch (OperationNotAllowedException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                }
                System.out.println("End date updated");
                editActivity(activity);
            }   
        }
    }
    
    public static void editProject(Project project){
        newPage();
        System.out.println("EDIT PROJECT");
        System.out.println("0. Main menu");
        System.out.println("1. Back to project details");
        System.out.println("2. Edit name");
        System.out.println("3. Edit description");
        System.out.println("4. Edit start time");
        System.out.println("5. Edit end time");
        int ans;
        while(true){
            System.out.println("Enter a number:");
            String ans1 = scanner.nextLine();
            try {
                ans = Integer.parseInt(ans1);
            } catch (Exception e) {
                continue;
            }
            if(ans == 0){
                mainMenu();
            }
            else if(ans == 1){
                viewProject(project);
            }
            //Name of project
            else if(ans == 2){
                System.out.println("Name of project:");
                try {
                    projectManagerApp.setProjectName(project, scanner.nextLine());
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                    editProject(project);
                }
                System.out.println("Name updated");
                editProject(project);
            }
            //discription of project
            else if(ans == 3){
                System.out.println("Description:");
                try {
                    projectManagerApp.setProjectDescription(project, scanner.nextLine());
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                    editProject(project);
                }
                System.out.println("Discription updated");
                editProject(project);
            }
            // start time
            else if(ans == 4){
                System.out.println("Please enter time on the form:");
                while(true){
                    System.out.println("WW-YYYY");
                    try {
                        projectManagerApp.setProjectStartTime(project, projectManagerApp.timeInputChecker(scanner.nextLine()));
                    } catch (OperationNotAllowedException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                }
                System.out.println("Start time updated");
                editProject(project);
            }
            //end time
            else if(ans == 5){
                System.out.println("Please enter time on the form:");
                while(true){
                    System.out.println("WW-YYYY");
                    try {
                        projectManagerApp.setProjectEndTime(project, projectManagerApp.timeInputChecker(scanner.nextLine()));
                    } catch (OperationNotAllowedException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                }
                System.out.println("End time updated");
                editProject(project);
            }
        }
    }
}
