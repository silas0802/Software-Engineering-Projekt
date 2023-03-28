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