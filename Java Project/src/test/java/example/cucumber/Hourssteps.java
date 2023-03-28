package example.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Hourssteps {



    @Given("entered work {double} hours")
    public void enteredWorkHours(Double double1) {
        VariablesHolder.workHours = double1;
    }

    @Then("round the work time to the closest {int} min.")
    public void roundTheWorkTimeToTheClosestMin(Integer i) {
        VariablesHolder.workHours = roundTo(VariablesHolder.workHours,i/60);
    }


    @Then("register the timeworked in user")
    public void registerTheTimeworkedInUser() {
        VariablesHolder.user.registerTimeWorked(VariablesHolder.workHours);
       
    }

    private static double roundTo(double v, double r) {
        return Math.round(v / r) * r;
      }
}
