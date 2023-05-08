Feature: Edit Project End Time
Description: White box test for the method "setProjectEndTime" in application. PM = Project Manager
Actors: User

# Daniel Henriksen
Scenario: PM edits project end time
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the user is assigned to the project as leader
And the project start time "16-2020" is set
When the project end time "19-2023" is set
Then the project end time becomes "19-2023"

# Daniel Henriksen
Scenario: user edits project end time with no PM assigned
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the project start time "16-2020" is set
When the project end time "20-2023" is set
Then the project end time becomes "20-2023"

# Daniel Henriksen
Scenario: user edits project end time with PM assigned
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the user is assigned to the project as leader
And a user with username "AB" logs in
When the project end time "21-2023" is set
Then the error message "User doesn't have permission" is given

# Daniel Henriksen
Scenario: edit project end time to a time before start time no PM
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the project start time "16-2026" is set
When the project end time "15-2026" is set
Then the error message "End time comes before Start time" is given

# Daniel Henriksen
Scenario: edit project end time to a time before start time no PM
Given a user with username "TH" logs in
And a project with name "I hate this" is created
And the project start time "16-2026" is set
When the project end time "20-2025" is set
Then the error message "End time comes before Start time" is given