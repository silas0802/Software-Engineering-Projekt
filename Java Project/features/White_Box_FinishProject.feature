Feature: finish project
Description: White box test for the method "finishProject" in application.
Actors: User
#Jesper pedersen
Scenario: projectLeader finishes project 
Given a user with username "JP" logs in
And a project with name "make homemade sanchwiches" is created
And the user is assigned to the project as leader
And an activity with name "get ham" under the project is created
And an activity with name "get cheese" under the project is created
And activity with name "get ham" is finished
And activity with name "get cheese" is finished
When the projectLeader finishes project
Then the project is finished

#Jesper pedersen
Scenario: User finishes project 
Given a user with username "JP" logs in
And a project with name "make homemade sanchwiches" is created
And the user is assigned to the project as leader
And an activity with name "get ham" under the project is created
And an activity with name "get cheese" under the project is created
And activity with name "get ham" is finished
And activity with name "get cheese" is finished
And a user with username "KM" logs in 
When the user finishes project
Then the error message "User doesn't have permission" is given

#Jesper pedersen
Scenario: User finishes project 
Given a user with username "JP" logs in
And a project with name "make homemade sanchwiches" is created
And the user is assigned to the project as leader
And an activity with name "get ham" under the project is created
And an activity with name "get cheese" under the project is created
And activity with name "get ham" is finished 
When the projectLeader finishes project
Then the error message "Project contains unfinished activities" is given
