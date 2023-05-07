Feature: Edit Project
    Description: editing different properties of an existing project
    Actors: Project Manager, User

#author - Silas
Scenario: Add a user to a project that already is assigned to a project
Given a user with username "SM" logs in
And a user with username "TY" is added to the system
And a project with name "Skat-hjemmeside" is created
And the user is assigned to the project as worker
When the user is assigned to the project as worker
Then the error message "Worker is already assigned to a project" is given

#author - Silas
Scenario: Assign project manager
Given a user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user is assigned to the project as leader
Then the user is project leader of the project

#author - Silas
Scenario: Add a list of users to a project
Given a user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
When user adds a list of users to the project
Then the users are assigned to the project

# Daniel Henriksen
Scenario: Edit project name
Given a user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
When the user edits the project name to "Boring graphical UI"
Then the project name is changed to "Boring graphical UI"

# Daniel Henriksen
Scenario: a worker tries to edit project name with PM
Given a user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
And a user with username "AS" logs in
When the user edits the project name to "Boring graphical UI"
Then the error message "User doesn't have permission" is given

# Daniel Henriksen
Scenario: a worker tries to edit project name without PM
Given a user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user edits the project name to "Boring graphical UI"
Then the project name is changed to "Boring graphical UI"

# Daniel Henriksen
Scenario: Edit project description
Given a user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
When the user edits the project description to "boring, not fun"
Then the project description is changed to "boring, not fun"

# Daniel Henriksen
Scenario: a worker tries to edit project description with PM
Given a user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
And a user with username "AS" logs in
When the user edits the project description to "ABC"
Then the error message "User doesn't have permission" is given

# Daniel
Scenario: a worker tries to edit project description without PM
Given a user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user edits the project description to "ABC"
Then the project description is changed to "ABC"

#Silas Thule
Scenario: Finish Project
Given a user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
When the user finishes the project
Then the project is moved to finished projects

#Silas Thule
Scenario: Finish Project with unfinished activities
Given a user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
And an activity with name "starting with the basics" under the project is created
When the user finishes the project
Then the error message "Project contains unfinished activities" is given

# Daniel Henriksen
Scenario: PM edits project start time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the user is assigned to the project as leader
When the project start time "16-2023" is set
Then the project start time becomes "16-2023"

# Daniel Henriksen
Scenario: user edits project start time with no PM assigned
Given a user with username "TH" logs in
And a project with name "I hate this" is created
When the project start time "17-2023" is set
Then the project start time becomes "17-2023"

# Daniel Henriksen
Scenario: user edits project start time with PM assigned
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the user is assigned to the project as leader
And a user with username "AB" logs in
When the project start time "18-2023" is set
Then the error message "User doesn't have permission" is given

# Daniel Henriksen
Scenario: PM edits project end time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the user is assigned to the project as leader
And the project start time "16-2020" is set
When the project end time "19-2023" is set
Then the project end time becomes "19-2023"

# Daniel Henriksen
Scenario: user edits project end time with no PM assigned
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the project start time "16-2020" is set
When the project end time "20-2023" is set
Then the project end time becomes "20-2023"

# Daniel Henriksen
Scenario: user edits project end time with PM assigned
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the user is assigned to the project as leader
And a user with username "AB" logs in
When the project end time "21-2023" is set
Then the error message "User doesn't have permission" is given

# Daniel Henriksen
Scenario: edit project end time to a time before start time no PM
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the project start time "16-2026" is set
When the project end time "15-2026" is set
Then the error message "End time comes before Start time" is given