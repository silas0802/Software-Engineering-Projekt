package example.cucumber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import application.Activity;
import application.ProjectManagerApp;
import application.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class UserSteps {
    
    private static final String M = null;
    User user; 
    List<User> users = new ArrayList<User>();
    Activity activity;
    VariablesHolder variablesholder;

    ProjectManagerApp projectManagerApp;

    public UserSteps(ProjectManagerApp projectManagerApp, VariablesHolder variablesHolder){
        this.projectManagerApp = projectManagerApp;
        
    }



    @Given("that the user is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        assertFalse(projectManagerApp.isLoggedIn());
    }

    @Given("the user {string} is in the system.")
    public void theUserIsInTheSystem(String userName) {
        projectManagerApp.createUser(new User("ABC"));
        user = projectManagerApp.searchByName(userName);

    }

    @Then("the user login succeeds")
    public void theUserLoginSucceeds() {
        assertTrue(projectManagerApp.hasUser(user));
        projectManagerApp.login(user);
    }

    @Then("the user is logged in")
    public void theUserIsLoggedIn() {
        assertTrue(projectManagerApp.isLoggedIn());
    }


}
