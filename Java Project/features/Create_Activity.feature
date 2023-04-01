Feature: create activity 
Description: Creating activities
Actors: User

Scenario: create activity
Given user with username "GB" logs in
And a project with name "Destroy C language" is created
When an activity with name "starting with the basics" under the project is created
Then the project contains the activity

Scenario: too many activities
Given user with username "HB" logs in
And a project with name "build a Holograaphic city" is created
And the project has 100 active activities
When an activity with name "build a well" under the project is created
Then the error message "Too many activities" is given

Scenario: looking at a workers activities
Given user with username "JP" logs in
And a project with name "lunch system" is created
And an activity with name "buying meat" under the project is created
And user is assigned the activities
And an activity with name "buying salad" under the project is created
And user is assigned the activities
Then user has the assigned activities

Scenario: too many activities
Given user with username "JP" logs in
And a project with name "Bully JP" is created
And 20 activities are assigned to user
When an activity with name "end JP" is assigned to user
Then the error message "Maximum of 20 activities are already assigned to user this week"


Scenario: an activities mother project
Given user with username "GB" logs in
And a project with name "kill everyone" is created
And an activity with name "start with oxygen supply" under the project is created
Then the project which the activity belongs to is shown

#Scenario: show active users
#Given 20 users are logged in 
#And 10 users has active activities
#And a project with name "try the system" is created
#And an activity with name "small stuff" under the project is created
#When searching for users without activities
#Then all users without activities are assigned to the activity