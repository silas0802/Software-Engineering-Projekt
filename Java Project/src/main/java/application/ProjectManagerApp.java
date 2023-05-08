package application;

import java.util.ArrayList;
import java.util.List;
import java.time.*;


public class ProjectManagerApp {
    User loggedUser;
    Boolean registeredHours = false;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Project> projects = new ArrayList<>();
    ArrayList<Project> finishedProjects = new ArrayList<>(); 
    
    
    /**
     * @author Niclas Schæffer
     */
    public boolean isLoggedIn(){
        return loggedUser != null;
    }
    //Niclas Schæffer
    public void login(User user){
        loggedUser = user;
    }
    //Niclas Schæffer
    public void logout() throws OperationNotAllowedException{
        if(hasRegisteredHours()){
            loggedUser = null;
        }else {
            throw new OperationNotAllowedException("Has not registered hours");
        }
    }
    //Niclas Schæffer
    public void createUser(User user){
        users.add(user);
    }

    public void createProject(Project project){
        projects.add(project);
    }
    public List<Project> getProjects(){
        return projects;
    }

    public void createActivity(Project project, Activity activity) throws OperationNotAllowedException{
        if(project.getActivities().size()>=100){
            throw new OperationNotAllowedException("Too many activities");
        }
        project.setActivity(activity);
        activity.setProject(project);
    }
    public Boolean hasRegisteredHours() {
        return registeredHours;
    }
    /**
     * @author Silas Thule
     * @param activity
     * @param time
     * @throws OperationNotAllowedException
     */
    public void RegisterHours(Activity activity, double time) throws OperationNotAllowedException {
        if (time % 0.5 == 0) {
            activity.timeWorkedList.registerTime(loggedUser, time);
            loggedUser.registerTimeWorked(time);
            registeredHours=true;
        }
        else{
            throw new OperationNotAllowedException("Time not rounded to nearst half hour");
        }
    }
    /**
     * @author Silas Thule
     * @param project
     * @param name
     * @throws OperationNotAllowedException
     */
    public void setProjectName(Project project, String name)throws OperationNotAllowedException{
        if (loggedUser == project.getProjectLeader() || project.getProjectLeader() == null) {
            project.setName(name);
        }
        else{
            throw new OperationNotAllowedException("User doesn't have permission");
        }
    }
    public void setProjectDescription(Project project, String des)throws OperationNotAllowedException{
        if (loggedUser == project.getProjectLeader() || project.getProjectLeader() == null) {
            project.setDescription(des);
        }
        else{
            throw new OperationNotAllowedException("User doesn't have permission");
        }
    }

    public boolean hasProject(Project project){
        return projects.contains(project);
    }

    public boolean hasActivity(Project project,Activity activity){
        ArrayList<Activity> a = project.getActivities();
        return a.contains(activity);
    }
    /**
     * @author Silas Thule
     * @param project
     * @return
     */
    public boolean projectIsFinished(Project project){
        for (Project project2 : finishedProjects) {
            if (project.equals(project2)&&project.isFinished()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasUser(User user){
        return users.contains(user);
    }
    public boolean haserUserByName(String name){
        for(User person: users){
            if (person.getUserName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    public User getUserByName(String name){
        for(User person: users){
            if (person.getUserName().equals(name)) {
                return person;
            }
        }
        return null;

    }
    /**
     * @author Silas Thule
     * @param project
     * @param users
     * @return
     */
    public boolean projectHasUsers(Project project, User[] users){
        for (User user : project.getWorkers()) {
            if (!hasUser(user)) {
                return false;
            }
        }
        return true;
    }


    public User searchByName(String name){
        for (User user : users) {
            if(user.getUserName().equals(name)){
                return user;
            }   

        }
        return null;
    }
    public  List<User> getUsers(){
        return users;
    }

    public void assignLeader(Project project, User user){

    project.setProjectLeader(user);
    }
    
    public void assignActivityToUser(User user, Activity activity) throws OperationNotAllowedException{
        if(user.getActivities().size()>=20){
            throw new OperationNotAllowedException("Maximum of 20 activities are already assigned to user this week");
        }
        if (userHasActivity(activity, user)) {
            throw new OperationNotAllowedException("Worker is already assigned to a project");
        }
        user.joinActivity(activity);
        activity.setActiveUser(user);
    }

    public List<Activity> getUserActivities(User user){
        return user.getActivities();
    }

    public boolean userHasActivity(Activity activity,User user){

        List<Activity> userActivities = getUserActivities(user);
        return userActivities.contains(activity);

    }

    public List<User> getUsersWithoutActivities(){
        return null;
        
    }

    public void setActiveDescription(Activity activity, String desciption){

        activity.setDescription(desciption);
        
    }

    public void finishActivity(Project project,Activity activity){
        project.setFinishedActivity(activity);
        activity.finishActivity();
        
    }
    /**
     * @author Silas Thule
     * @param project
     * @throws OperationNotAllowedException
     */
    public void finishProject(Project project)throws OperationNotAllowedException{
        if (loggedUser == project.getProjectLeader()) {
            if (project.getActivities().isEmpty()) {
                project.finishProject();
                projects.remove(project);
                finishedProjects.add(project);
                
            }
            else{
                throw new OperationNotAllowedException("Project contains unfinished activities");
            }
        }
        else{
            throw new OperationNotAllowedException("User doesn't have permission");
        }
    }

    public void setActivityStartTime (Activity activity, StartEndTime startTime) throws OperationNotAllowedException {
        LocalDate firstDay = LocalDate.of(startTime.getYear(), 1, 1);        
        
        if(!(Year.of(startTime.getYear()).isLeap() && firstDay.getDayOfWeek().equals(DayOfWeek.WEDNESDAY) || firstDay.getDayOfWeek().equals(DayOfWeek.THURSDAY)) && (startTime.getWeek() > 52 || startTime.getWeek() < 1)){
            throw new OperationNotAllowedException("Please enter a week between 1 and 52");
        } else if (startTime.getWeek() > 53 || startTime.getWeek() < 1){
            throw new OperationNotAllowedException("Please enter a week between 1 and 53");
        } else {
            activity.setStartTime(startTime);;
        }
    }

    public void setActivityEndTime (Activity activity, StartEndTime endTime) throws OperationNotAllowedException {
        LocalDate firstDay = LocalDate.of(endTime.getYear(), 1, 1);
        
        if(!(Year.of(endTime.getYear()).isLeap() && firstDay.getDayOfWeek().equals(DayOfWeek.WEDNESDAY) || firstDay.getDayOfWeek().equals(DayOfWeek.THURSDAY)) && (endTime.getWeek() > 52 || endTime.getWeek() < 1)){
            throw new OperationNotAllowedException("Please enter a week between 1 and 52");
        } else if (endTime.getWeek() > 53 || endTime.getWeek() < 1){
            throw new OperationNotAllowedException("Please enter a week between 1 and 53");
        } else if (activity.getStartTime().getYear() >= endTime.getYear() || (activity.getStartTime().getWeek() >= endTime.getWeek() && activity.getStartTime().getYear() == endTime.getYear())) {
            throw new OperationNotAllowedException("End time comes before Start time");
        } else {
            activity.setEndTime(endTime);
        }
    }

    public void setProjectStartTime (Project project, StartEndTime startTime) throws OperationNotAllowedException {
        LocalDate firstDay = LocalDate.of(startTime.getYear(), 1, 1);

        if (!(loggedUser == project.getProjectLeader() || project.getProjectLeader() == null)) {
            throw new OperationNotAllowedException("User doesn't have permission");
        }

        if(!(Year.of(startTime.getYear()).isLeap() && firstDay.getDayOfWeek().equals(DayOfWeek.WEDNESDAY) || firstDay.getDayOfWeek().equals(DayOfWeek.THURSDAY)) && (startTime.getWeek() > 52 || startTime.getWeek() < 1)){
            throw new OperationNotAllowedException("Please enter a week between 1 and 52");
        } else if (startTime.getWeek() > 53 || startTime.getWeek() < 1){
            throw new OperationNotAllowedException("Please enter a week between 1 and 53");
        } else {
            project.setStartTime(startTime);;
        }

        
    }

    public void setProjectEndTime (Project project, StartEndTime endTime) throws OperationNotAllowedException {
        LocalDate firstDay = LocalDate.of(endTime.getYear(), 1, 1);

        if (!(loggedUser == project.getProjectLeader() || project.getProjectLeader() == null)) {
            throw new OperationNotAllowedException("User doesn't have permission");
        }

        if(!(Year.of(endTime.getYear()).isLeap() && firstDay.getDayOfWeek().equals(DayOfWeek.WEDNESDAY) || firstDay.getDayOfWeek().equals(DayOfWeek.THURSDAY)) && (endTime.getWeek() > 52 || endTime.getWeek() < 1)){
            throw new OperationNotAllowedException("Please enter a week between 1 and 52");
        } else if (endTime.getWeek() > 53 || endTime.getWeek() < 1){
            throw new OperationNotAllowedException("Please enter a week between 1 and 53");
        } else if (project.getStartTime().getYear() >= endTime.getYear() || (project.getStartTime().getWeek() >= endTime.getWeek() && project.getStartTime().getYear() == endTime.getYear())) {
            throw new OperationNotAllowedException("End time comes before Start time");
        } else {
            project.setEndTime(endTime);
        }
        
            
        
    }

    public StartEndTime timeInputChecker(String timeInput) throws OperationNotAllowedException{
        StartEndTime time = null;
        if(!timeInput.contains("-")){
            throw new OperationNotAllowedException("The week and year has to be seperated by \"-\"");
        }
        String weekYear[] = timeInput.split("-");

        if(!(weekYear[1].length() == 4 && (weekYear[0].length() == 1 || weekYear[0].length() == 2))){
            throw new OperationNotAllowedException("The week has to contain 2 digits and the year has to contain 4");                  
        }

        try {
            time = new StartEndTime(Integer.parseInt(weekYear[0]), Integer.parseInt(weekYear[1]));
        } catch (Exception e) {
            throw new OperationNotAllowedException("The input has to be two numbers seperated by \"-\"");
        }  
        return time;
    }

}
