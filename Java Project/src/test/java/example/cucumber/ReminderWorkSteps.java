package example.cucumber;

import static org.junit.Assert.assertTrue;

import application.ProjectManagerApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
/**
 * @author Niclas
 */
public class ReminderWorkSteps {

    ProjectManagerApp projectManagerApp;
    public ReminderWorkSteps(ProjectManagerApp projectManagerApp){
        this.projectManagerApp = projectManagerApp;
    }

    @Given("If user hasn't entered a timer")
    public void ifUserHasnTEnteredATimer() {
        assertTrue(!projectManagerApp.hasRegisteredHours());
    }
    @Then("Give reminder to the user to enter work hours.")
    public void giveReminderToTheUserToEnterWorkHours() {
        System.out.println("Remember to enter your time you have worked today");
    }
    
}
