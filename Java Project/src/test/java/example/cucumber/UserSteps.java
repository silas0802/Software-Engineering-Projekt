package example.cucumber;

import static org.junit.Assert.assertFalse;

import application.Activity;
import application.ProjectManagerApp;
import application.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class UserSteps {
    
    User user; 
    Activity activity;

    ProjectManagerApp projectManagerApp;

    public UserSteps(ProjectManagerApp projectManagerApp){
        this.projectManagerApp = projectManagerApp;
    }



    @Given("that the user is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        assertFalse(projectManagerApp.isLoggedIn());
    }

    @Given("the user {string} is in the system.")
    public void theUserIsInTheSystem(String user) {
       projectManagerApp.users.contains(user);
    }

    @Then("the user login succeeds")
    public void theUserLoginSucceeds() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user is logged in")
    public void theUserIsLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
