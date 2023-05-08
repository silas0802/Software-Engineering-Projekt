Feature: Edit Activity Feature

#Anton Ekman
Scenario: user is assigned to an activity
Given a user with username "TY" logs in
And a project with name "Website development" is created
And an activity with name "Username interface" under the project is created 
And a user with username "KY" is added to the system
When the user is assigned to the activity
Then the user has been assigned to the activity

#Anton Ekman
Scenario: user is already assigned to the activity
Given a user with username "JP" logs in
And a user with username "JY" is added to the system
And an activity with name "Username interface" under the project is created
And a project with name "minSunhed" is created
And the user is assigned to the activity
When the user is assigned to the actitivy
Then the error message "User is already assigned to the activity" is given



#jesper pedersen
Scenario: edit activity name
Given a user with username "JG" logs in
And a project with name "DTU-IT" is created
And an activity with name "update systems" under the project is created
When the activities name is edited to "update on monday"
Then the name of the activity is "update on monday"


#jesper pedersen
Scenario: edit activity description
Given a user with username "JP" logs in
And a project with name "MitID" is created
And an activity with name "Username interface" under the project is created
And a Description with name "need usernames" is added
When a Description with name "need usernames of min 8 chars" is added
Then the Description "need usernames of min 8 chars" is on the activity


# Daniel Henriksen
Scenario: edit activity start time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the project start time "16-2020" is set
And the project end time "21-2030" is set
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2026" is set
When the activity start time "15-2023" is set
Then the activity start time becomes "15-2023"


# Daniel Henriksen
Scenario: edit activity end time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the project start time "16-2020" is set
And the project end time "21-2030" is set
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2020" is set
And the activity end time "16-2026" is set
When the activity end time "15-2023" is set
Then the activity end time becomes "15-2023"


# Daniel Henriksen
Scenario: edit activity end time to a time before start time - week
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the project start time "16-2020" is set
And the project end time "21-2030" is set
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2026" is set
And the activity end time "20-2026" is set
When the activity end time "15-2026" is set
Then the error message "End time comes before Start time" is given


# Daniel Henriksen
Scenario: edit activity end time to a time before start time - year
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the project start time "16-2020" is set
And the project end time "21-2030" is set
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2026" is set
And the activity end time "20-2026" is set
When the activity end time "25-2025" is set
Then the error message "End time comes before Start time" is given


#jesper pedersen
Scenario: edit expected work time on activity
Given a user with username "JD" logs in
And a project with name "SU-kontoret" is created
And an activity with name "Calculate Money" under the project is created
And user sets the expected work time of the activity to 20.0
When the user sets the expected work time of the activity to 10.0
Then the expected work time of the activity is 10.0

#jesper pedersen
Scenario: Finish Activity
Given a user with username "JH" logs in
And a project with name "Zip codes system" is created
And the user is assigned to the project as leader
And an activity with name "assign zip code" under the project is created
When the user finishes the activity
Then the activity is moved to finished activities under the project


#jesper pedersen
Scenario: Add more activities to one project
Given a user with username "JK" logs in
And a project with name "Facebook" is created
And the user is assigned to the project as leader
And an activity with name "Log in UI" under the project is created
When an activity with name "Homepage UI" under the project is created
Then the project has 2 activities


# Daniel Henriksen
Scenario: edit activity start time, no project time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
When the activity start time "15-2023" is set
Then the error message "Project doesn't have established start time and end time: type 'back' to return" is given


# Daniel Henriksen
Scenario: edit activity end time, no project time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
When the activity end time "15-2023" is set
Then the error message "Project doesn't have established start time and end time: type 'back' to return" is given


