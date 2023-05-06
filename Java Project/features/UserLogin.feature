Feature: User Login
#Niclas
Scenario: Existing user logging in
Given that the user is not logged in
And the user "ABC" is in the system.
Then the user login succeeds
And the user is logged in

#Niclas
Scenario: New user logging in
Given That the User is not logged in
And "DEF" does not exist
And the user's initials has 4 or less characters
Then Promt the user if they wanna create user with "ABC"
Then Creating the new user
And the user is logged in

Scenario: Existing user logging in
Given that the user is not logged in
And the user "ABC" is in the system.
Then the user login succeeds
And the user is logged in