Feature: Logout
#Niclas
Scenario: attempts to logout without register hours 
Given The user is logged in
And Haven't registered hours
Then Being reminded of the registration of hours

#Niclas
Scenario: attempts to logout with register hours 
Given The user is logged in
And Have registered hours
Then the user is logged out