Feature: create activity 
Description: Creating activities
Actors: User

Scenario: create activity
Given user with username "GB" logs in
And a project with name "Destroy C language" is created
When an activity with name "starting with the basics" under the project is created
Then the project contains the activity

