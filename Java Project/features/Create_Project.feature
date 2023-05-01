Feature: Create Project
    Description: Creating project
    Actors: User


Scenario: Create project
Given a user with username "JP" logs in
When a project with name "Fun graphical UI" is created
Then a project with name "Fun graphical UI" is added to project list


Scenario: Create project with project leader
Given a user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user is assigned to the project as leader
Then the user is project leader of the project

Scenario: Project Numbering of 1 project
Given a user with username "SM" logs in
And projectIDCounter is 1
When a project with name "project 1" is created
Then the project id is 23001

Scenario: Project Numbering of 2 projects
Given a user with username "SM" logs in
And projectIDCounter is 1
And a project with name "project 1" is created
When a project with name "project 2" is created
Then the project id is 23002

Scenario: Reset numbering after newyear
Given a user with username "SM" logs in
And projectIDCounter is 60
And projectYearID is 2022
When a project with name "project 1" is created
Then the project id is 23001