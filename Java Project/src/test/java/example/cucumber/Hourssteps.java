package example.cucumber;

import static org.junit.Assert.assertTrue;

import application.OperationNotAllowedException;
import application.ProjectManagerApp;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Hourssteps {

    ProjectManagerApp projectManagerApp;
    public Hourssteps(ProjectManagerApp projectManagerApp){
        this.projectManagerApp = projectManagerApp;
    }

    @When("the user registers {double} hours of work")
    public void theUserRegistersHoursOfWork(Double double1) throws OperationNotAllowedException{
        // Write code here that turns the phrase above into concrete actions
        VariablesHolder.workHours = double1;
        try{
            projectManagerApp.RegisterHours(VariablesHolder.activity,double1);
            } catch(OperationNotAllowedException e){
                VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
            }
    }
    @Then("time has been registered for the activity and user")
    public void timeHasBeenRegisteredForTheActivityAndUser() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(VariablesHolder.user.getTimeWorked()==VariablesHolder.workHours);
        assertTrue(VariablesHolder.activity.getWorkedTime()==VariablesHolder.workHours);
    }
}
