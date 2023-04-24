Feature: Den forbrugte tid skal registreres med 1/2 times nøjagtighed.

Scenario: User have entered uneven work time.
Given entered work 1.4 hours
Then round the work time to the closest 30 min.
And register the timeworked in user