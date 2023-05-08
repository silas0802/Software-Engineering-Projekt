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
    //jesper pedersen
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
    public void registerHours(Activity activity, double time) throws OperationNotAllowedException {
        if (time % 0.5 != 0) {
            throw new OperationNotAllowedException("Time not rounded to nearst half hour");
        }
            assert time % 0.5 == 0 && activity != null;
            double currentWorkedTime = loggedUser.getTimeWorked();
            double currentWorkedTimeActivity = activity.timeWorkedList.totalTimeWorked();
            activity.timeWorkedList.registerTime(loggedUser, time);
            loggedUser.registerTimeWorked(time);
            registeredHours=true;
            assert loggedUser.getTimeWorked() == currentWorkedTime+time && activity.timeWorkedList.totalTimeWorked() == currentWorkedTimeActivity+time;
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


    public boolean userHasProject(User user, Project project){
        return project.getWorkers().contains(user);
    }

    public boolean hasProject(Project project){
        return projects.contains(project);
    }


    //jesper pedersen
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

    //jesper pedersen
    public boolean hasUser(User user){
        return users.contains(user);
    }


    //jesper pedersen
    public boolean hasUserByName(String name){
        for(User person: users){
            if (person.getUserName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    //jesper pedersen
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
    //jesper pedersen
    public  List<User> getUsers(){
        return users;
    }

    //jesper pedersen
    public void assignLeader(Project project, User user){
        project.setProjectLeader(user);
    }
    
    //jesper pedersen
    public void assignActivityToUser(User user, Activity activity) throws OperationNotAllowedException{
        if(user.getActivities().size()>=20){
            throw new OperationNotAllowedException("Maximum of 20 activities are already assigned to user this week");
        }
        if (userHasActivity(activity, user)) {
            throw new OperationNotAllowedException("User is already assigned to the activity");
        }
        user.joinActivity(activity);
        activity.setActiveUser(user);
    }
    //jesper pedersen
    public List<Activity> getUserActivities(User user){
        return user.getActivities();
    }
    //jesper pedersen
    public boolean userHasActivity(Activity activity,User user){

        List<Activity> userActivities = getUserActivities(user);
        return userActivities.contains(activity);

    }   
    //jesper pedersen
    public List<User> getUsersWithoutActivities(){
        return null;
        
    }
    //jesper pedersen
    public void setActivityDescription(Activity activity, String desciption){

        activity.setDescription(desciption);
        
    }
    //jesper pedersen
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

    // Daniel Henriksen
    public void setActivityStartTime (Activity activity, StartEndTime startTime) throws OperationNotAllowedException {
        activity.setStartTime(startTime);
    }

    // Daniel Henriksen
    public void setActivityEndTime (Activity activity, StartEndTime endTime) throws OperationNotAllowedException {
        if (activity.getStartTime().getYear() > endTime.getYear() || (activity.getStartTime().getWeek() > endTime.getWeek() && activity.getStartTime().getYear() == endTime.getYear())) {
            throw new OperationNotAllowedException("End time comes before Start time");
        } else {
            activity.setEndTime(endTime);
        }
    }

    // Daniel Henriksen
    public void setProjectStartTime (Project project, StartEndTime startTime) throws OperationNotAllowedException {
        if (!(loggedUser == project.getProjectLeader() || project.getProjectLeader() == null)) {
            throw new OperationNotAllowedException("User doesn't have permission");
        }
        project.setStartTime(startTime);  
    }

    // Daniel Henriksen
    public void setProjectEndTime (Project project, StartEndTime endTime) throws OperationNotAllowedException {
        if (!(loggedUser == project.getProjectLeader() || project.getProjectLeader() == null)) {
            throw new OperationNotAllowedException("User doesn't have permission");
        }

        if (project.getStartTime().getYear() > endTime.getYear() || (project.getStartTime().getWeek() > endTime.getWeek() && project.getStartTime().getYear() == endTime.getYear())) {
            throw new OperationNotAllowedException("End time comes before Start time");
        } else {
            project.setEndTime(endTime);
        }
    }

    // Daniel Henriksen
    public StartEndTime timeInputChecker(String timeInput) throws OperationNotAllowedException{
        StartEndTime time = null;
        if(timeInput.length() > 7 || !timeInput.contains("-")){
            throw new OperationNotAllowedException("The input has to contain a \"-\" and be on the form: WW-YYYY");
        }

        String weekYear[] = timeInput.split("-");

        if(!(weekYear[0].length() == 2 && weekYear[1].length() == 4)){
            throw new OperationNotAllowedException("The input has to be on the form: WW-YYYY");
        }

        try {
            time = new StartEndTime(Integer.parseInt(weekYear[0]), Integer.parseInt(weekYear[1]));
        } catch (Exception e) {
            throw new OperationNotAllowedException("The input has to be two numbers on the form: WW-YYYY");
        }

        LocalDate firstDayOfTheYear = LocalDate.of(time.getYear(), 1, 1);

        if(!(Year.of(time.getYear()).isLeap() && firstDayOfTheYear.getDayOfWeek().equals(DayOfWeek.WEDNESDAY) || firstDayOfTheYear.getDayOfWeek().equals(DayOfWeek.THURSDAY)) && (time.getWeek() > 52 || time.getWeek() < 1)){
            throw new OperationNotAllowedException("Please enter a week number between 1 and 52");
        } else if (time.getWeek() > 53 || time.getWeek() < 1){
            throw new OperationNotAllowedException("Please enter a week number between 1 and 53");
        }
        return time;
    }

}
