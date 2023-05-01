Feature: create activity 
Description: Creating activities
Actors: User

Scenario: create activity
Given a user with username "GB" logs in
And a project with name "Destroy C language" is created
When an activity with name "starting with the basics" under the project is created
Then the project contains the activity

Scenario: too many activities on a project
Given a user with username "HB" logs in
And a project with name "build a Holograaphic city" is created
And the project has 100 active activities
When an activity with name "build a well" under the project is created
Then the error message "Too many activities" is given

Scenario: looking at a workers activities
Given a user with username "JP" logs in
And a project with name "lunch system" is created
And an activity with name "buying meat" under the project is created
And user is assigned the activities
And an activity with name "buying salad" under the project is created
And user is assigned the activities
Then user has the assigned activities

Scenario: too many activities for user
Given a user with username "JP" logs in
And a project with name "Bully JP" is created
And 20 activities are assigned to user
When an activity with name "end JP" is assigned to user
Then the error message "Maximum of 20 activities are already assigned to user this week"

Scenario: the activity belongs to a project
Given a user with username "GB" logs in
And a project with name "kill everyone" is created
And an activity with name "start with oxygen supply" under the project is created
Then the project which the activity belongs to is shown

Scenario: add multiple users to activity
Given 20 users are logged in 
And 10 users has active activities
And a project with name "try the system" is created
And an activity with name "small stuff" under the project is created
When searching for users without activities
Then all users without activities are assigned to the activity

Scenario: set activity Description
Given a user with username "EH" logs in
And a project with name "Buy groceris" is created
And an activity with name "purchasing meat" under the project is created
When a Description with name "we need this done asap" is added
Then the Description is added to the activity

Scenario: set activity start time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
When the activity start time "15-2023" is set
Then the activity start time becomes "15-2023"

Scenario: set a non-exitent activity start time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
When the activity start time "56-2023" is set
Then the error message "Please enter a week between 1 and 52" is given

Scenario: set a non-exitent activity start time when the year has 53 weeks
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
When the activity start time "56-2026" is set
Then the error message "Please enter a week between 1 and 53" is given

Scenario: set activity end time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
When the activity end time "15-2023" is set
Then the activity end time becomes "15-2023"

Scenario: set a non-exitent activity end time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
When the activity end time "56-2023" is set
Then the error message "Please enter a week between 1 and 52" is given

Scenario: set a non-exitent activity end time when the year has 53 weeks
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
When the activity end time "56-2026" is set
Then the error message "Please enter a week between 1 and 53" is given

Scenario: activity end time is set before start time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And an activity with name "Exit everything" under the project is created
And the activity start time "16-2026" is set
When the activity end time "15-2026" is set
Then the error message "End time comes before Start time" is given

Scenario: Set expected work time on activity
Given a user with username "JD" logs in
And a project with name "SU-kontoret" is created
And an activity with name "Calculate Money" under the project is created
When the user sets the expected work time of the activity to 20
Then the expected work time of the activity is 20
