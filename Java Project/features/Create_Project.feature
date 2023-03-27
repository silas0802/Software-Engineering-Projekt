Feature: Create Project


Scenario: Create project
Given user with username "JP" logs in
When a project with name "Fun graphical UI" is created
Then a project with name "Fun graphical UI" is added to project list




Scenario: create project with project leader
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user is assigned to the project as leader
Then the user is project leader of the project