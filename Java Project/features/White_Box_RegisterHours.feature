Feature: Register hours
Description: White box test for the method "RegisterHours" in application.
Actors: User

#Silas Thule
Scenario: User have entered uneven work time.
Given a user with username "asda" logs in
And a project with name "tidsprojekt" is created
And an activity with name "tidsaktivitet" under the project is created
When the user registers 6.7 hours of work
Then the error message "Time not rounded to nearst half hour" is given

#Silas Thule
Scenario: User have entered even work time.
Given a user with username "was" logs in
And a project with name "tidsprojekt1" is created
And an activity with name "tidsaktivitet1" under the project is created
When the user registers 7.5 hours of work
Then time has been registered for the activity and user
