package example.cucumber;

import static org.junit.Assert.assertTrue;

import application.OperationNotAllowedException;
import application.ProjectManagerApp;
import application.User;
import application.WorkerTime;
import application.WorkerTimeList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author Niclas, Silas Thule
 */
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
            projectManagerApp.registerHours(VariablesHolder.activity,double1);
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
    //Silas Thule
    @Given("a WorkerTimeList exists")
    public void aWorkerTimeListExists() {
        // Write code here that turns the phrase above into concrete actions
        VariablesHolder.workerTimeList = new WorkerTimeList();
    }
    //Silas Thule
    @Given("a User exists")
    public void aUserExists() {
        // Write code here that turns the phrase above into concrete actions
        VariablesHolder.user = new User("Torb");
    }
    //Silas Thule
    @Given("the user registers {double} hours in WorkerTimeList")
    public void theUserRegistersHoursInWorkerTimeList(Double int1) {
        // Write code here that turns the phrase above into concrete actions
        VariablesHolder.workerTimeList.registerTime(VariablesHolder.user, int1);
    }
    //Silas Thule
    @When("the user gets workedTimeList")
    public void theUserGetsWorkedTimeList() {
        // Write code here that turns the phrase above into concrete actions
        VariablesHolder.workerTimes = VariablesHolder.workerTimeList.getWorkedTimeList();
    }
    //Silas Thule
    @Then("workedTimeList contains user with {double} hours")
    public void workedTimeListContainsUserWithHours(Double int1) {
        // Write code here that turns the phrase above into concrete actions
        boolean check = false;
        for (WorkerTime worktime : VariablesHolder.workerTimes) {
            if (worktime.user == VariablesHolder.user && worktime.time == int1) {
                check = true;
            }
        }
        assertTrue(check);
    }
    //Silas Thule
    @When("the user gets totalTimeWorked")
    public void theUserGetsTotalTimeWorked() {
        // Write code here that turns the phrase above into concrete actions
        VariablesHolder.workHours = VariablesHolder.workerTimeList.totalTimeWorked();
    }
    //Silas Thule
    @Then("{double} is returned")
    public void isReturned(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(VariablesHolder.workHours == double1);
    }
}
