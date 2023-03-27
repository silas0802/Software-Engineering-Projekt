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
    User user;
    Project project;
    VariablesHolder variablesHolder;

    ProjectManagerApp projectManagerApp;
    public ProjectSteps(ProjectManagerApp projectManagerApp, VariablesHolder variablesHolder){
        this.projectManagerApp = projectManagerApp;
        this.variablesHolder= variablesHolder;
        user=variablesHolder.user;
        project=variablesHolder.project;
    }

    @Given("user with username {string} logs in")
    public void userWithUsernameLogsIn(String initials) {
        // Write code here that turns the phrase above into concrete actions
        user = new User(initials);
        projectManagerApp.login(user);
    }
    
    @When("a project with name {string} is created")
    public void aProjectWithNameIsCreated(String name) {
        // Write code here that turns the phrase above into concrete actions
        project = new Project(name);
        projectManagerApp.createProject(project);
       
    }
    
    @Then("a project with name {string} is added to project list")
    public void aProjectWithNameIsAddedToProjectList(String name) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(name, project.getName());
        assertTrue(projectManagerApp.hasProject(project));
    }


    @When("the user is assigned to the project as leader")
    public void theUserIsAssignedToTheProjectAsLeader() {
        // Write code here that turns the phrase above into concrete actions

        projectManagerApp.assignLeader(project, user);
        
    }
    
    @Then("the user is project leader of the project")
    public void theUserIsProjectLeaderOfTheProject() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(user, project.getProjectLeader());
    }
}
