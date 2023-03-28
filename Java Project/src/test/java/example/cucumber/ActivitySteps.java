package example.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    
}
