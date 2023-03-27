package example.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Project;
import application.ProjectManagerApp;
import application.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivitySteps {
    User user;
    Project project;
    VariablesHolder variablesHolder;
    ProjectManagerApp projectManagerApp;
    public ActivitySteps(ProjectManagerApp projectManagerApp,VariablesHolder variablesHolder){
        this.projectManagerApp = projectManagerApp;
        this.variablesHolder= variablesHolder;
        user=variablesHolder.user;
        project=variablesHolder.project;
    }


    @When("an activity with name {string} under the project is created")
    public void anActivityWithNameUnderTheProjectIsCreated(String string) {
        
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("the project contains the activity")
    public void theProjectContainsTheActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
}
