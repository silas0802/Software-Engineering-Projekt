Feature: Edit Project
    Description: editing different properties of an existing project
    Actors: Project Manager, User

#author - Silas
Scenario: Add a user to a project that already is assigned to a project
Given user with username "SM" logs in
And a user with username "TY" is added to the system
And a project with name "Skat-hjemmeside" is created
And the user is assigned to the project as worker
When the user is assigned to the project as worker
Then the error message "Worker is already assigned to a project" is given

#author - Silas
Scenario: Assign project manager
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user is assigned to the project as leader
Then the user is project leader of the project

#author - Silas
Scenario: Add a list of users to a project
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
When user adds a list of users to the project
Then the users are assigned to the project

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

Scenario: a worker tries to edit project description with PM
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
And user with username "AS" logs in
When the user edits the project description to "ABC"
Then the error message "User doesn't have permission" is given

Scenario: a worker tries to edit project description without PM
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user edits the project description to "ABC"
Then the project description is changed to "ABC"

# Auth - Daniel
Scenario: edit expected work time
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
When the user edits the expected work time to 10
Then the expected work time is 10

# Auth - Daniel
Scenario: a worker tries to edit expected work time with PM
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
And user with username "AS" logs in
When the user edits the expected work time to 20
Then the error message "User doesn't have permission" is given

Scenario: a worker tries to edit expected work time without PM
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
When the user edits the expected work time to 20
Then the expected work time is 20

Scenario: Finish Project
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
When the user finishes the project
Then the project is moved to finished projects

Scenario: Finish Project with unfinished activities
Given user with username "SM" logs in
And a project with name "Fun graphical UI" is created
And the user is assigned to the project as leader
And an activity with name "starting with the basics" under the project is created
When the user finishes the project
Then the error message "Project contains unfinished activities" is given

# Scenario: Edit starttime for project
# Given user with username "KD" logs in
# And a project with name "Everything is okay" is created
# And the user is assigned to the project as leader
# When the user sets the starttime to 11-12-2022
# Then the project starttime is set to 11-12-2022

# Scenario: Worker edits starttime for project with PM
# Given user with username "KD" logs in
# And a project with name "Everything is okay" is created
# And the user is assigned to the project as leader
# And user with username "SK" logs in
# When the user sets the starttime to 11-12-2022
# Then the error message "User doesn't have permission" is given

# Scenario: Worker edits starttime for project without PM
# Given user with username "KD" logs in
# And a project with name "Everything is okay" is created
# When the user sets the starttime to 11-12-2022
# Then the project starttime is set to 11-12-2022

# Scenario: Edit endtime for project
# Given user with username "KD" logs in
# And a project with name "Everything is okay" is created
# And the user is assigned to the project as leader
# When the user sets the endtime to 11-12-2022
# Then the project endtime is set to 11-12-2022

# Scenario: Worker edits endtime for project with PM
# Given user with username "KD" logs in
# And a project with name "Everything is okay" is created
# And the user is assigned to the project as leader
# And user with username "SK" logs in
# When the user sets the endtime to 11-12-2022
# Then the error message "User doesn't have permission" is given

# Scenario: Worker edits endtime for project without PM
# Given user with username "KD" logs in
# And a project with name "Everything is okay" is created
# When the user sets the endtime to 11-12-2022
# Then the project endtime is set to 11-12-2022