package example.cucumber;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import application.Activity;
import application.OperationNotAllowedException;
import application.Project;
import application.ProjectManagerApp;
import application.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivitySteps {
    
    
    ProjectManagerApp projectManagerApp;
    private ErrorMessageHolder errorMessage;
    public ActivitySteps(ProjectManagerApp projectManagerApp, ErrorMessageHolder errorMessage){
        this.projectManagerApp = projectManagerApp;
        
        this.errorMessage=errorMessage;
    }


    @When("an activity with name {string} under the project is created")
    public void anActivityWithNameUnderTheProjectIsCreated(String activityName) throws OperationNotAllowedException {
        
        VariablesHolder.activity= new Activity(activityName);
        try{
        projectManagerApp.createActivity(VariablesHolder.project,VariablesHolder.activity);
        } catch(OperationNotAllowedException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
        
    }
    
    @Then("the project contains the activity")
    public void theProjectContainsTheActivity() {
        assertTrue(projectManagerApp.hasActivity(VariablesHolder.project, VariablesHolder.activity));
        
        
    }



    @Given("the project has {int} active activities")
    public void theProjectHasActiveActivities(Integer numofActivities) throws OperationNotAllowedException {
        for (int i = 0; i < numofActivities; i++) {
            Activity activity = new Activity("trial");
            projectManagerApp.createActivity(VariablesHolder.project, activity);
        }
    }

@Then("the error message {string} is given")
public void theErrorMessageIsGiven(String errorMessage) {

    assertEquals(errorMessage, this.errorMessage.getErrorMessage());
    assertEquals(100, VariablesHolder.project.getActivities().size());
}
    


@When("user is assigned the activities")
public void userIsAssignedTheActivities() {
    try{
        ProjectManagerApp.assignActivityToUser(VariablesHolder.user,VariablesHolder.activity);
    
    }catch(OperationNotAllowedException e){
        errorMessage.setErrorMessage(e.getMessage());
    }

}

@Then("user has the assigned activities")
public void userHasTheAssignedActivities() {
    assertTrue(projectManagerApp.userHasActivity(VariablesHolder.activity, VariablesHolder.user));
    assertEquals(2,projectManagerApp.getUserActivities(VariablesHolder.user).size());
}



@Given("{int} activities are assigned to user")
public void activitiesAreAssignedToUser(Integer numofActivities) {
    for (int i = 0; i < numofActivities; i++) {
        Activity activity = new Activity("trial");
       try{
        ProjectManagerApp.assignActivityToUser(VariablesHolder.user, activity);
       }catch(OperationNotAllowedException e){
        errorMessage.setErrorMessage(e.getMessage());
       }
    }
}

@When("an activity with name {string} is assigned to user")
public void anActivityWithNameIsAssignedToUser(String activityName) {
   VariablesHolder.activity=new Activity(activityName);
   try{
    ProjectManagerApp.assignActivityToUser(VariablesHolder.user, VariablesHolder.activity);
   }catch(OperationNotAllowedException e){
    errorMessage.setErrorMessage(e.getMessage());
   }
}

@Then("the error message {string}")
public void theErrorMessage(String errorMessage1) {
    assertEquals(errorMessage1, this.errorMessage.getErrorMessage());
    assertEquals(20, ProjectManagerApp.getUserActivities(VariablesHolder.user).size());
}



@Then("the project which the activity belongs to is shown")
public void theProjectWhichTheActivityBelongsToIsShown() {
    
    assertEquals(VariablesHolder.project, VariablesHolder.activity.getProject());
}



@Given("{int} users are logged in")
public void usersAreLoggedIn(Integer operations) {
   for (int i = 0; i < operations; i++) {
    User user = new User("Bot");
    ProjectManagerApp.createUser(user);
   }
}

@Given("{int} users has active activities")
public void usersHasActiveActivities(Integer operations) throws OperationNotAllowedException {
    List<User> users =ProjectManagerApp.getUsers();
    for (int i = 0; i < operations; i++) {
        Activity activity = new Activity("space");
        ProjectManagerApp.assignActivityToUser(users.get(i), activity);

    }
}

@When("searching for users without activities")
public void searchingForUsersWithoutActivities() {
   
}

@Then("all users without activities are assigned to the activity")
public void allUsersWithoutActivitiesAreAssignedToTheActivity() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
}
