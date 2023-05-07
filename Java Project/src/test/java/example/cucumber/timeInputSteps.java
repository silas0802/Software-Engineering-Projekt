package example.cucumber;

import application.OperationNotAllowedException;
import application.ProjectManagerApp;
import io.cucumber.java.en.When;

public class timeInputSteps {

    ProjectManagerApp projectManagerApp;
    public timeInputSteps(ProjectManagerApp projectManagerApp){
        this.projectManagerApp = projectManagerApp;
    }

    @When("the user inputs the time {string}")
    public void theUserInputsTheTime(String input) {
        try {
            projectManagerApp.timeInputChecker(input);
        } catch (OperationNotAllowedException e) {
            VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

}
