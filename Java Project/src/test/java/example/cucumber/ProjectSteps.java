package example.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.OperationNotAllowedException;
import application.Project;
import application.ProjectManagerApp;
import application.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectSteps {
    
    

    ProjectManagerApp projectManagerApp;
    public ProjectSteps(ProjectManagerApp projectManagerApp){
        this.projectManagerApp = projectManagerApp;
        
        
    }

    @Given("user with username {string} logs in")
    public void userWithUsernameLogsIn(String initials) {
        // Write code here that turns the phrase above into concrete actions
        VariablesHolder.user = new User(initials);
        projectManagerApp.login(VariablesHolder.user);
    }
    
    @When("a project with name {string} is created")
    public void aProjectWithNameIsCreated(String name) {
        // Write code here that turns the phrase above into concrete actions
        VariablesHolder.project = new Project(name);
        projectManagerApp.createProject(VariablesHolder.project);
       
    }
    
    @Then("a project with name {string} is added to project list")
    public void aProjectWithNameIsAddedToProjectList(String name) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(name, VariablesHolder.project.getName());
        assertTrue(projectManagerApp.hasProject(VariablesHolder.project));
    }


    @When("the user is assigned to the project as leader")
    public void theUserIsAssignedToTheProjectAsLeader() {
        // Write code here that turns the phrase above into concrete actions

        projectManagerApp.assignLeader(VariablesHolder.project, VariablesHolder.user);
        
    }
    
    @Then("the user is project leader of the project")
    public void theUserIsProjectLeaderOfTheProject() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(VariablesHolder.user, VariablesHolder.project.getProjectLeader());
    }

    @Then("the users are assigned to the project")
    public void the_users_are_assigned_to_the_project() {
        // Write code here that turns the phrase above into concrete actions
       

        assertTrue(projectManagerApp.projectHasUsers(VariablesHolder.project, VariablesHolder.users));
       
    }

    @When("user adds a list of users to the project")
    public void user_adds_a_list_of_users_to_the_project() {
        // Write code here that turns the phrase above into concrete actions
        VariablesHolder.users = new User[] {new User("user1"),new User("user2"),new User("user3"),new User("user4")};
        
        try {
			VariablesHolder.project.assignWorkers(VariablesHolder.users);
		} catch (OperationNotAllowedException e) {
			VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
		}
        
       
    }

    @Then("the project is moved to finished projects")
    public void the_project_is_moved_to_finished_projects() {
        // Write code here that turns the phrase above into concrete actions
        projectManagerApp.projectIsFinished(VariablesHolder.project);
    }

    @When("the user finishes the project")
    public void the_user_finishes_the_project() {
        // Write code here that turns the phrase above into concrete actions
        try {
            projectManagerApp.finishProject(VariablesHolder.project);
		} catch (OperationNotAllowedException e) {
			VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
		}
    }

    @Then("the expected work time is {int}")
    public void the_expected_work_time_is(int i) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user edits the expected work time to {int}")
    public void the_user_edits_the_expected_work_time_to(int i) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the project name is changed to {string}")
    public void the_project_name_is_changed_to(String projectName) {
        assertEquals(VariablesHolder.project.getName(), projectName);
    }

    @When("the user edits the project name to {string}")
    public void the_user_edits_the_project_name_to(String s) {
        // Write code here that turns the phrase above into concrete actions
        try {
            projectManagerApp.setProjectName(VariablesHolder.project,s);
		} catch (OperationNotAllowedException e) {
			VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
		}
    }

    @Then("the project description is changed to {string}")
    public void the_project_description_is_changed_to(String description) {
        assertEquals(VariablesHolder.project.getDescription(), description);
    }

    @When("the user edits the project description to {string}")
    public void the_user_edits_the_project_description_to(String description) {
        try {
            projectManagerApp.setProjectDescription(VariablesHolder.project ,description);
		} catch (OperationNotAllowedException e) {
			VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
		}
    }

    @Given("a user with username {string} is added to the system")
    public void aUserWithUsernameIsAddedToTheSystem(String name) {
        // Write code here that turns the phrase above into concrete actions
        VariablesHolder.user = new User(name);
        projectManagerApp.createUser(VariablesHolder.user);
        
    }
    
    @When("the user is assigned to the project as worker")
    public void theUserIsAssignedToTheProjectAsWorker() {
        // Write code here that turns the phrase above into concrete actions
        
        try {
			VariablesHolder.project.assignWorker(VariablesHolder.user);
		} catch (OperationNotAllowedException e) {
			VariablesHolder.errorMessageHolder.setErrorMessage(e.getMessage());
		}
    }
    
    @Given("the project has an unfinished activity")
    public void theProjectHasAnUnfinishedActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
