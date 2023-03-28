package example.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Activity;
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
    public void anActivityWithNameUnderTheProjectIsCreated(String activityName) {
        VariablesHolder.activity= new Activity(activityName);
        projectManagerApp.createActivity(VariablesHolder.project,VariablesHolder.activity);
        
    }
    
    @Then("the project contains the activity")
    public void theProjectContainsTheActivity() {
        assertTrue(projectManagerApp.hasActivity(VariablesHolder.project, VariablesHolder.activity));
        
        
    }
    
}
