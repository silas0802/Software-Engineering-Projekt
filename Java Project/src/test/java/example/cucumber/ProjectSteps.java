package example.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
