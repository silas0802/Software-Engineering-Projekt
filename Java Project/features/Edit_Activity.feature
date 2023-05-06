Feature: Edit Activity Feature

Scenario: user is assigned to an activity
Given a user with username "TY" logs in
And a project with name "IM GOING INSANE" is created
And an activity with name "Username interface" under the project is created 
And a user with username "KY" is added to the system
When the user is assigned to the activity
Then the user has been assigned to the activity

Scenario: user is already assigned to the activity
Given a user with username "JP" logs in
And a user with username "JY" is added to the system
And an activity with name "Username interface" under the project is created
And a project with name "minSunhed" is created
And the user is assigned to the activity
When the user is assigned to the actitivy
Then the error message "User is already assigned to the activity" is given

Scenario: edit activity name
Given a user with username "JG" logs in
And a project with name "DTU-IT" is created
And an activity with name "update systems" under the project is created
When the activities name is edited to "update on monday"
Then the name of the activity is "update on monday"

Scenario: edit activity description
Given a user with username "JP" logs in
And a project with name "MitID" is created
And an activity with name "Username interface" under the project is created
And a Description with name "need usernames" is added
When a Description with name "need usernames of min 8 chars" is added
Then the Description "need usernames of min 8 chars" is on the activity

Scenario: edit activity start time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2026" is set
When the activity start time "15-2023" is set
Then the activity start time becomes "15-2023"

Scenario: edit activity start time to a non-exitent start time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2026" is set
When the activity start time "56-2023" is set
Then the error message "Please enter a week number between 1 and 52" is given

Scenario: edit activity start time to a non-exitent start time when the year has 53 weeks
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2026" is set
When the activity start time "56-2026" is set
Then the error message "Please enter a week number between 1 and 53" is given

Scenario: edit activity end time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2020" is set
And the activity end time "16-2026" is set
When the activity end time "15-2023" is set
Then the activity end time becomes "15-2023"

Scenario: edit activity end time to a non-exitent end time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2020" is set
And the activity end time "16-2026" is set
When the activity end time "56-2023" is set
Then the error message "Please enter a week number between 1 and 52" is given

Scenario: edit activity end time to a non-exitent end time when the year has 53 weeks
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2020" is set
And the activity end time "16-2026" is set
When the activity end time "56-2026" is set
Then the error message "Please enter a week number between 1 and 53" is given

Scenario: edit activity end time to a time before start time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2026" is set
And the activity end time "20-2026" is set
When the activity end time "15-2026" is set
Then the error message "End time comes before Start time" is given

Scenario: edit expected work time on activity
Given a user with username "JD" logs in
And a project with name "SU-kontoret" is created
And an activity with name "Calculate Money" under the project is created
And user sets the expected work time of the activity to 20
When the user sets the expected work time of the activity to 10
Then the expected work time of the activity is 10

Scenario: Finish Activity
Given a user with username "JH" logs in
And a project with name "Zip codes system" is created
And the user is assigned to the project as leader
And an activity with name "assign zip code" under the project is created
When the user finishes the activity
Then the activity is moved to finished activities under the project

Scenario: Add more activities to one project
Given a user with username "JK" logs in
And a project with name "Facebook" is created
And the user is assigned to the project as leader
And an activity with name "Log in UI" under the project is created
When an activity with name "Homepage UI" under the project is created
Then the project has 2 activities