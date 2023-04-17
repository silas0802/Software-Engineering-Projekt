package example.cucumber;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    
    public ActivitySteps(ProjectManagerApp projectManagerApp){
        this.projectManagerApp = projectManagerApp;
        
        
    }


    @When("an activity with name {string} under the project is created")
    public void anActivityWithNameUnderTheProjectIsCreated(String activityName) throws OperationNotAllowedException {
        
        VariablesHolder.activity= new Activity(activityName);
        try{
        projectManagerApp.createActivity(VariablesHolder.project,VariablesHolder.activity);
        } catch(OperationNotAllowedException e){
            VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
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

    assertEquals(errorMessage, VariablesHolder.errorMessageHolder.getErrorMessage());
    
}
    


@When("user is assigned the activities")
public void userIsAssignedTheActivities() {
    try{
        projectManagerApp.assignActivityToUser(VariablesHolder.user,VariablesHolder.activity);
    
    }catch(OperationNotAllowedException e){
        VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
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
        projectManagerApp.assignActivityToUser(VariablesHolder.user, activity);
       }catch(OperationNotAllowedException e){
        VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
       }
    }
}

@When("an activity with name {string} is assigned to user")
public void anActivityWithNameIsAssignedToUser(String activityName) {
   VariablesHolder.activity=new Activity(activityName);
   try{
    projectManagerApp.assignActivityToUser(VariablesHolder.user, VariablesHolder.activity);
   }catch(OperationNotAllowedException e){
    VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
   }
}

@Then("the error message {string}")
public void theErrorMessage(String errorMessage1) {
    assertEquals(errorMessage1, VariablesHolder.errorMessageHolder.getErrorMessage());
    assertEquals(20, projectManagerApp.getUserActivities(VariablesHolder.user).size());
}



@Then("the project which the activity belongs to is shown")
public void theProjectWhichTheActivityBelongsToIsShown() {
    
    assertEquals(VariablesHolder.project, VariablesHolder.activity.getProject());
}



@Given("{int} users are logged in")
public void usersAreLoggedIn(Integer operations) {
   for (int i = 0; i < operations; i++) {
    User user = new User("Bot");
    projectManagerApp.createUser(user);
   }
}

@Given("{int} users has active activities")
public void usersHasActiveActivities(Integer operations) throws OperationNotAllowedException {
    List<User> users =projectManagerApp.getUsers();
    for (int i = 0; i < operations; i++) {
        Activity activity = new Activity("space");
        projectManagerApp.assignActivityToUser(users.get(i), activity);

    }
}

@When("searching for users without activities")
public void searchingForUsersWithoutActivities() throws OperationNotAllowedException {
   List<User> users = projectManagerApp.getUsers();
   List<User> temp = new ArrayList<>();
   List<User> unactveUsers =users.stream().filter(user -> user.getActivities().equals(temp)).collect(Collectors.toList());
   for (int i = 0; i < unactveUsers.size(); i++) {
    projectManagerApp.assignActivityToUser(unactveUsers.get(i),VariablesHolder.activity);
   }

}

@Then("all users without activities are assigned to the activity")
public void allUsersWithoutActivitieAreAssignedToTheActivity() {
    
    assertEquals(10,VariablesHolder.activity.getUsersOnActivity().size() );
}


@When("a Description with name {string} is added")
public void aDescriptionWithNameIsAdded(String description) {
    VariablesHolder.desciption=description;
   projectManagerApp.setActiveDescription(VariablesHolder.activity, description);
}

@Then("the Description is added to the activity")
public void theDescriptionIsAddedToTheActivity() {
    assertEquals(VariablesHolder.desciption, VariablesHolder.activity.getDescription());
    
}
}


