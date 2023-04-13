package example.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ReminderWork {

    @Given("If user hasn't entered a timer")
    public void ifUserHasnTEnteredATimer() {
        // Todo check if user has entered work today, probably need an date for the work entered. 
    }
    @Then("Give reminder to the user to enter work hours.")
    public void giveReminderToTheUserToEnterWorkHours() {
        System.out.println("Remember to enter your time you have worked today");
    }
    
}
