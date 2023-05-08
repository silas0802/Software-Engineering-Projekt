Feature: assign user to actitivy
Description: White box test for the method "assignActivityToUser" in application.
Actors: User

#Anton Ekman
Scenario: user is assigned to an activity
Given a user with username "TY" logs in
And a project with name "Website development" is created
And an activity with name "Username interface" under the project is created 
When the user is assigned to the activity
Then the user has been assigned to the activity

#Anton Ekman
Scenario: user is already assigned to the activity
Given a user with username "JP" logs in
And an activity with name "Username interface" under the project is created
And a project with name "min Sundhed" is created
And the user is assigned to the activity
When the user is assigned to the actitivy
Then the error message "User is already assigned to the activity" is given

#Anton Ekman
Scenario: user is assigned to maximum number of activity
Given a user with username "AN" logs in
And 20 activities are assigned to user
And a project with name "Important" is created
And an activity with name "Username interface" under the project is created 
When the user is assigned to the actitivy
Then the error message "Maximum of 20 activities are already assigned to user this week" is given
