Feature: WorkerTimeList
Description: manipulating WorkerTimeLists
Actors: User

#Silas Thule
Background: a user and WorkerTimeList
Given a WorkerTimeList exists
And a User exists

Scenario: User gets workedTimeList
Given the user registers 10.0 hours in WorkerTimeList
When the user gets workedTimeList
Then workedTimeList contains user with 10.0 hours

Scenario: User checks totaltime spent
Given the user registers 10.0 hours in WorkerTimeList
And a User exists
And the user registers 17.0 hours in WorkerTimeList
When the user gets totalTimeWorked
Then 27.0 is returned

Scenario: User checks his worked time
Given the user registers 10.0 hours in WorkerTimeList
And the user registers 17.0 hours in WorkerTimeList
When the user gets his worked time
Then 27.0 is returned