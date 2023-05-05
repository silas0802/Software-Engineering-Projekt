package example.cucumber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import application.OperationNotAllowedException;
import application.ProjectManagerApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * @author Niclas
 */
public class UserLogoutSteps {

    ProjectManagerApp projectManagerApp;

    public UserLogoutSteps(ProjectManagerApp projectManagerApp){
        this.projectManagerApp = projectManagerApp;
        
    }
    //Niclas
    @Given("The user is logged in")
    public void theUserIsLoggedIn() {
        projectManagerApp.login(VariablesHolder.user);
    }
    //Niclas
    @Given("Haven't registered hours")
    public void havenTRegisteredHours() {
        assertFalse(projectManagerApp.hasRegisteredHours());
    }
    //Niclas
    @Then("Being reminded of the registration of hours")
    public void beingRemindedOfTheRegistrationOfHours() {
        System.out.println("Remember to register hours before logging out of the user.");
    }

    //Silas Thule
    @Given("Have registered hours")
    public void haveRegisteredHours() throws OperationNotAllowedException{
        try{
            projectManagerApp.RegisterHours(VariablesHolder.activity,1);
            } catch(OperationNotAllowedException e){
                VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
            }
        assertTrue(projectManagerApp.hasRegisteredHours());
    }
    //Niclas
    @Then("the user is logged out")
    public void theUserIsLoggedOut() {
        try {
            projectManagerApp.logout();
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }
}
