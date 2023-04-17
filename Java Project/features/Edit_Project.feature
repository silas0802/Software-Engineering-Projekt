Feature: Edit Project
    Description: editing different properties of an existing project
    Actors: Project Manager, User


Scenario: Add a user to a project that already is assigned to a project
Given user with username "SM" logs in
And a user with username "TY" is added to the system
And a project with name "Skat-hjemmeside" is created
And the user is assigned to the project as worker
When the user is assigned to the project as worker
Then the error message "Worker is already assigned to a project" is given

Scenario: Assign project manager
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user is assigned to the project as leader
Then the user is project leader of the project

# Scenario: Add a list of users to a project
# Given user with username "SM" logs in
# And a project with name "Fun graphical UI" is created
# And the user is assigned to the project as leader
# When user adds a list of users to the project
# Then the users are assigned to the project

# Auth - Daniel
Scenario: Edit project name
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
When the user edits the project name to "Boring graphical UI"
Then the project name is changed to "Boring graphical UI"

Scenario: a worker tries to edit project name with PM
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
And user with username "AS" logs in
When the user edits the project name to "Boring graphical UI"
Then the error message "User doesn't have permission" is given

Scenario: a worker tries to edit project name without PM
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user edits the project name to "Boring graphical UI"
Then the project name is changed to "Boring graphical UI"

# Auth - Daniel
Scenario: Edit project description
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
When the user edits the project description to "boring, not fun"
Then the project description is changed to "boring, not fun"

Scenario: a worker tries to edit project description
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user edits the project description to "ABC"
Then the error message "User doesn't have permission" is given

# Auth - Daniel
Scenario: edit expected work time
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
When the user edits the expected work time to 10
Then the expected work time is 10

# Auth - Daniel
Scenario: a worker tries to edit expected work time
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user edits the expected work time to 20
Then the error message "User doesn't have permission" is given

Scenario: Finish Project
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
When the user finishes the project
Then the project is moved to finished projects

# Scenario: Finish Project with unfinished activities
# Given user with username "SM" logs in
# And a project with name "Fun graphical UI" is created
# And the user is assigned to the project as leader
# And the project has an unfinished activity
# When the user finishes the project
# Then throw an error saying "Project contains unfinished activities"