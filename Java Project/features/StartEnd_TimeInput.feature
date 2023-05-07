Feature: Time Input for Start and End times.
Description: This feature cuts away around 10-15 tests, as the input for all four methods: "setActivityStartTime", "setActivityEndTime", "setProjectStartTime", "setProjectEndTime"
is run through the method: "timeInputChecker" before it reaches the other methods. This is therefore a method specific feature file.
Actors: User

# Daniel Henriksen
Scenario: user enters invalid date format when asked for StartEnd Time input 1
Given a user with username "HT" logs in
When the user inputs the time "155-202"
Then the error message "The input has to be on the form: WW-YYYY" is given

# Daniel Henriksen
Scenario: user enters invalid date format when asked for StartEnd Time input 1
Given a user with username "HT" logs in
When the user inputs the time "15202315"
Then the error message "The input has to contain a \"-\" and be on the form: WW-YYYY" is given

# Daniel Henriksen
Scenario: user enters invalid date format when asked for StartEnd Time input 1
Given a user with username "HT" logs in
When the user inputs the time "Il-egal"
Then the error message "The input has to be two numbers on the form: WW-YYYY" is given

# Daniel Henriksen
Scenario: user enters a non-exitent time when asked for StartEnd Time input
Given a user with username "TH" logs in
When the user inputs the time "56-2023"
Then the error message "Please enter a week number between 1 and 52" is given

# Daniel Henriksen
Scenario: the year has 53 weeks and user enters a non-exitent time when asked for StartEnd Time input
Given a user with username "TH" logs in
When the user inputs the time "56-2026"
Then the error message "Please enter a week number between 1 and 53" is given