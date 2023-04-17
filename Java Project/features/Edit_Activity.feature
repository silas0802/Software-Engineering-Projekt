Feature: Edit activity Feature

Scenario: worker already assigned
Given user with username "JP" logs in
And a user with username "JY" is added to the system
And a project with name "minSunhed" is created
And the user is assigned to the activity
When the user is assigned to the actitivy
Then the error message "Worker is already assigned to a project" is given



Scenario: edit activity Description
Given user with username "JP" logs in
And a project with name "MitID" is created
And an activity with name "Username interface" under the project is created
And a Description with name "need usernames" is added
When a Description with name "need usernames of min 8 chars" is added
Then the Description "need usernames of min 8 chars" is on the activity

Scenario: edit activity name
Given user with username "JG" logs in
And a project with name "DTU-IT" is created
And an activity with name "update systems" under the project is created
When the activities name is edited to "update on monday"
Then the name of the activity is "update on monday"

Scenario: Set expected work time on activity
Given user with username "JD" logs in
And a project with name "SU-kontoret" is created
And the user is assigned to the project as leader
And an activity with name "Calculate Money" under the project is created
When the user sets the expected work time of the activity to 20
Then the expected work time of the activity is 20


Scenario: edit expected work time on activity
Given user with username "JD" logs in
And a project with name "SU-kontoret" is created
And the user is assigned to the project as leader
And an activity with name "Calculate Money" under the project is created
And user sets the expected work time of the activity to 20
When the user sets the expected work time of the activity to 10
Then the expected work time of the activity is 10

Scenario: Finish Project
Given user with username "JH" logs in
And a project with name "Zip codes system" is created
And the user is assigned to the project as leader
And an activity with name "assign zip code" under the project is created
When the user finishes the activity
Then the activity is moved to finished activities under the project

Scenario: Add more activities to one project
Given user with username "JK" logs in
And a project with name "Facebook" is created
And the user is assigned to the project as leader
And an activity with name "Log in UI" under the project is created
When an activity with name "Homepage UI" under the project is created
Then the project has 2 activities