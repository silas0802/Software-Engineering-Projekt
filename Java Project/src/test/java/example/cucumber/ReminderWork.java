package example.cucumber;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ReminderWork {

    @Given("If user hasn't entered a timer")
    public void ifUserHasnTEnteredATimer() {
        VariablesHolder.registeredHours = false;
        assertTrue(VariablesHolder.registeredHours == false);
    }
    @Then("Give reminder to the user to enter work hours.")
    public void giveReminderToTheUserToEnterWorkHours() {
        System.out.println("Remember to enter your time you have worked today");
    }
    
}
