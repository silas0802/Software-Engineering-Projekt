package example.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import application.Activity;
import application.OperationNotAllowedException;
import application.ProjectManagerApp;
import application.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivitySteps {
    
    
    ProjectManagerApp projectManagerApp;
    
    public ActivitySteps(ProjectManagerApp projectManagerApp){
        this.projectManagerApp = projectManagerApp;
        
        
    }

    //jesper pedersen
    @When("an activity with name {string} under the project is created")
    public void anActivityWithNameUnderTheProjectIsCreated(String activityName) throws OperationNotAllowedException {
        
        VariablesHolder.activity= new Activity(activityName);
        try{
        projectManagerApp.createActivity(VariablesHolder.project,VariablesHolder.activity);
        } catch(OperationNotAllowedException e){
            VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }
    
    //jesper pedersen
    @Then("the project contains the activity")
    public void theProjectContainsTheActivity() {
        assertTrue(projectManagerApp.hasActivity(VariablesHolder.project, VariablesHolder.activity));
        
        
    }


    //jesper pedersen
    @Given("the project has {int} active activities")
    public void theProjectHasActiveActivities(Integer numofActivities) throws OperationNotAllowedException {
        for (int i = 0; i < numofActivities; i++) {
            Activity activity = new Activity("trial");
            projectManagerApp.createActivity(VariablesHolder.project, activity);
        }
    }
    //jesper pedersen
    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String errorMessage) {

        assertEquals(errorMessage, VariablesHolder.errorMessageHolder.getErrorMessage());
        
    }
        

    //jesper pedersen
    @When("user is assigned the activities")
    public void userIsAssignedTheActivities() {
        try{
            projectManagerApp.assignActivityToUser(VariablesHolder.user,VariablesHolder.activity);
        
        }catch(OperationNotAllowedException e){
            VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    //jesper pedersen
    @Then("user has the assigned activities")
    public void userHasTheAssignedActivities() {
        assertTrue(projectManagerApp.userHasActivity(VariablesHolder.activity, VariablesHolder.user));
        assertEquals(2,projectManagerApp.getUserActivities(VariablesHolder.user).size());
    }


    //Anton Ekman
    @Given("{int} activities are assigned to user")
    public void activitiesAreAssignedToUser(Integer numofActivities) {
        for (int i = 0; i < numofActivities; i++) {
            Activity activity = new Activity("trial");
        try{
            projectManagerApp.assignActivityToUser(VariablesHolder.user, activity);
        }catch(OperationNotAllowedException e){
            VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
        }
        }
    }
    //Anton Ekman
    @When("an activity with name {string} is assigned to user")
    public void anActivityWithNameIsAssignedToUser(String activityName) {
    VariablesHolder.activity=new Activity(activityName);
        try{
            projectManagerApp.assignActivityToUser(VariablesHolder.user, VariablesHolder.activity);
        }catch(OperationNotAllowedException e){
            VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    //Anton Ekman
    @Then("the error message {string}")
    public void theErrorMessage(String errorMessage1) {
        assertEquals(errorMessage1, VariablesHolder.errorMessageHolder.getErrorMessage());
        assertEquals(20, projectManagerApp.getUserActivities(VariablesHolder.user).size());
    }


    //jesper pedersen
    @Then("the project which the activity belongs to is shown")
    public void theProjectWhichTheActivityBelongsToIsShown() {
        
        assertEquals(VariablesHolder.project, VariablesHolder.activity.getProject());
    }


    //jesper pedersen
    @Given("{int} users are logged in")
    public void usersAreLoggedIn(Integer operations) {
        for (int i = 0; i < operations; i++) {
            User user = new User("Bot");
            projectManagerApp.createUser(user);
        }
    }


    //jesper pedersen
    @Given("{int} users has active activities")
    public void usersHasActiveActivities(Integer operations) throws OperationNotAllowedException {
        List<User> users =projectManagerApp.getUsers();
        for (int i = 0; i < operations; i++) {
            Activity activity = new Activity("space");
            projectManagerApp.assignActivityToUser(users.get(i), activity);

        }
    }

    //jesper pedersen    
    @When("searching for users without activities")
    public void searchingForUsersWithoutActivities() throws OperationNotAllowedException {
        List<User> users = projectManagerApp.getUsers();
        List<User> temp = new ArrayList<>();
        List<User> unactveUsers =users.stream().filter(user -> user.getActivities().equals(temp)).collect(Collectors.toList());
        for (int i = 0; i < unactveUsers.size(); i++) {
            projectManagerApp.assignActivityToUser(unactveUsers.get(i),VariablesHolder.activity);
        }

    }

    //jesper pedersen
    @Then("all users without activities are assigned to the activity")
    public void allUsersWithoutActivitieAreAssignedToTheActivity() {
        
        assertEquals(10,VariablesHolder.activity.getUsersOnActivity().size() );
    }

    //jesper pedersen
    @When("a Description with name {string} is added")
    public void aDescriptionWithNameIsAdded(String description) {
        VariablesHolder.description=description;
    projectManagerApp.setActivityDescription(VariablesHolder.activity, description);
    }

    //jesper pedersen
    @Then("the Description is added to the activity")
    public void theDescriptionIsAddedToTheActivity() {
        assertEquals(VariablesHolder.description, VariablesHolder.activity.getDescription());
        
    }

    //jesper pedersen
    @Given("the user is assigned to the activity")
    public void theUserIsAssignedToTheActivity() throws OperationNotAllowedException {
        projectManagerApp.assignActivityToUser(VariablesHolder.user, VariablesHolder.activity);
    }
    //Anton Ekman
    @When("the user is assigned to the actitivy")
    public void theUserIsAssignedToTheActitivy() throws OperationNotAllowedException {
        try {
            projectManagerApp.assignActivityToUser(VariablesHolder.user, VariablesHolder.activity);
            
        } catch (OperationNotAllowedException e) {
            VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    //jesper pedersen
    @Then("the Description {string} is on the activity")
    public void theDescriptionIsOnTheActivity(String descrip) {
        assertEquals(VariablesHolder.activity.getDescription(), descrip);
    }

    //jesper pedersen
    @When("the activities name is edited to {string}")
    public void theActivitiesNameIsEditedTo(String actiname) {
        VariablesHolder.activity.setName(actiname);
    }

    //jesper pedersen
    @Then("the name of the activity is {string}")
    public void theNameOfTheActivityIs(String activiname) {
        assertEquals(VariablesHolder.activity.getName(), activiname);
    }

    //jesper pedersen
    @When("the user sets the expected work time of the activity to {double}")
    public void theUserSetsTheExpectedWorkTimeOfTheActivityTo(Double exptime) {
        VariablesHolder.activity.setExpectedDuration(exptime);
    }

    //jesper pedersen
    @Then("the expected work time of the activity is {double}")
    public void theExpectedWorkTimeOfTheActivityIs(Double exptime) {
        assertEquals(VariablesHolder.activity.getExpectedDuration(), exptime);
    }

    //jesper pedersen
    @Given("user sets the expected work time of the activity to {double}")
    public void userSetsTheExpectedWorkTimeOfTheActivityTo(Double exptime) {
    VariablesHolder.activity.setExpectedDuration(exptime);
    }

    //jesper pedersen
    @When("the user finishes the activity")
    public void theUserFinishesTheActivity() {
        projectManagerApp.finishActivity(VariablesHolder.project, VariablesHolder.activity);
    }

    //jesper pedersen
    @Then("the activity is moved to finished activities under the project")
    public void theActivityIsMovedToFinishedActivitiesUnderTheProject() {
        assertFalse(projectManagerApp.hasActivity(VariablesHolder.project, VariablesHolder.activity));
        assertEquals(VariablesHolder.project.getFinishedActivities().size(), 1);
        assertTrue(VariablesHolder.activity.isActivityFinished());

    }

    //jesper pedersen
    @Then("the project has {int} activities")
    public void theProjectHasActivities(Integer numOfActivities) {
        assertEquals(VariablesHolder.project.getActivities().size(), numOfActivities);
        
        assertTrue(projectManagerApp.hasActivity(VariablesHolder.project, VariablesHolder.activity));
    }

    //jesper pedersen
    @Then("the user has been assigned to the activity")
    public void theUserHasBeenAssignedToTheActivity() {
        assertTrue(projectManagerApp.userHasActivity(VariablesHolder.activity, VariablesHolder.user));
    }
    
    @Given("the actitivy start time {string} is set")
    public void theActitivyStartTimeIsSet(String startTime) {
        try {
            projectManagerApp.setActivityStartTime(VariablesHolder.activity, projectManagerApp.timeInputChecker(startTime));
        } catch (OperationNotAllowedException e) {
            VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Daniel Henriksen
    @When("the activity start time {string} is set")
        public void theStartTimeIsSet(String startTime) {
            try {
                projectManagerApp.setActivityStartTime(VariablesHolder.activity, projectManagerApp.timeInputChecker(startTime));
            } catch (OperationNotAllowedException e) {
                VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
            }
        }
    
    // Daniel Henriksen
    @Then("the activity start time becomes {string}")
        public void theStartTimeBecomes(String startTime) {
            assertEquals(VariablesHolder.activity.getStartTime().getWeek(), Integer.parseInt(startTime.split("-")[0]));
            assertEquals(VariablesHolder.activity.getStartTime().getYear(), Integer.parseInt(startTime.split("-")[1]));
        }

    // Daniel Henriksen
    @When("the activity end time {string} is set")
        public void theEndTimeIsSet(String endTime) {
            try {
                projectManagerApp.setActivityEndTime(VariablesHolder.activity, projectManagerApp.timeInputChecker(endTime));
            } catch (OperationNotAllowedException e) {
                VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
            }
        }
   
    // Daniel Henriksen
    @Then("the activity end time becomes {string}")
        public void theEndTimeBecomes(String endTime) {
            assertEquals(VariablesHolder.activity.getEndTime().getWeek(), Integer.parseInt(endTime.split("-")[0]));
            assertEquals(VariablesHolder.activity.getEndTime().getYear(), Integer.parseInt(endTime.split("-")[1]));
        }

}



