Feature: assign user to actitivy
Description: White box test for the method "assignActivityToUser" in application.
Actors: User

Scenario: user is assigned to an activity
Given a user with username "TY" logs in
And a project with name "Website development" is created
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

