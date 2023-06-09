package example.cucumber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import application.ProjectManagerApp;
import application.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * @author Niclas
 */
public class UserSteps {
    
     
    String tempUser;

    ProjectManagerApp projectManagerApp;

    public UserSteps(ProjectManagerApp projectManagerApp){
        this.projectManagerApp = projectManagerApp;
        
    }

    @Given("that the user is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        assertFalse(projectManagerApp.isLoggedIn());
    }

    @Given("the user {string} is in the system.")
    public void theUserIsInTheSystem(String userName) {
        projectManagerApp.createUser(new User("ABC"));
        VariablesHolder.user = projectManagerApp.searchByName(userName);

    }

    @Then("the user login succeeds")
    public void theUserLoginSucceeds() {
        assertTrue(projectManagerApp.hasUser(VariablesHolder.user));
        projectManagerApp.login(VariablesHolder.user);
    }

    @Then("the user is logged in")
    public void theUserIsLoggedIn() {
        assertTrue(projectManagerApp.isLoggedIn());
    }


    @Given("That the User is not logged in")
    public void thatTheUserIsNotLoggedIn1() {
        assertFalse(projectManagerApp.isLoggedIn());
    }



    @Given("{string} does not exist")
    public void doesNotExist(String username) {
        tempUser = username;
        assertTrue(projectManagerApp.searchByName(username) == null);
    }

    @Given("the user's initials has {int} or less characters")
    public void theUserSInitialsHasOrLessCharacters(Integer i) {
        assertTrue(tempUser.length() <= i);
    }

    @Then("Promt the user if they wanna create user with {string}")
    public void promtTheUserIfTheyWannaCreateUserWith(String string) {   
        VariablesHolder.user = new User(string);
    }

    @Then("Creating the new user")
    public void creatingTheNewUser() {
        projectManagerApp.createUser(VariablesHolder.user);
        assertTrue(projectManagerApp.hasUser(VariablesHolder.user));
        projectManagerApp.login(VariablesHolder.user);
    }

}
