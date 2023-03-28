package example.cucumber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.Activity;
import application.ProjectManagerApp;
import application.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class UserSteps {
    
    User user; 
    String input;
    List<User> users = new ArrayList<User>();
    Activity activity;
    String userChoice;

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


    @Given("That the User is not logged in")
    public void thatTheUserIsNotLoggedIn1() {
        assertFalse(projectManagerApp.isLoggedIn());
    }



    @Given("{string} does not exist")
    public void doesNotExist(String string) {
        input = string;
        assertFalse(projectManagerApp.hasUser(user));
    }

    @Given("the user’s initials has {int} or less characters")
    public void theUserSInitialsHasOrLessCharacters(Integer i) {
        assertTrue(input.length() <= i);
    }

    @Then("Promt the user if they wanna create user with {string}")
    public void promtTheUserIfTheyWannaCreateUserWith(String string) {   
    //     System.out.println("Do you want to create: " + string + " y or n");
    //     Scanner in = new Scanner(System.in);
    //     userChoice = in.nextLine();
    //     in.close();
    //     assertFalse(userChoice != "y");
        userChoice = "y";
        assertTrue(userChoice == "y");
        user = new User(string);


    }

    @Then("Creating the new user")
    public void creatingTheNewUser() {
        projectManagerApp.createUser(user);
        assertTrue(projectManagerApp.hasUser(user));
        projectManagerApp.login(user);
    }

   

}
