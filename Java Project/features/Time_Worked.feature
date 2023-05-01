Feature: Register Time

Scenario: User have entered uneven work time.
Given user with username "karl" logs in
And a project with name "tidsprojekt" is created
And an activity with name "tidsaktivitet" under the project is created
When the user registers 2.7 hours of work
Then the error message "Time not rounded to nearst half hour" is given

Scenario: User have entered uneven work time.
Given user with username "karl" logs in
And a project with name "tidsprojekt1" is created
And an activity with name "tidsaktivitet1" under the project is created
When the user registers 2.5 hours of work
Then time has been registered for the activity and user