Feature: Sign in Functionality


 Scenario Outline: Business Owner sign in
 Given User is on signin page
 When user enters Business Owner email "<email>" 
 And User enter password "<password>"
 And clicks on the Sign in button
 Then user should navigate to home page "<result>"

 Examples:
 | email            | password  | result |
 | owner@infoez.com | Owner@123 | valid  |
 | owne@infoez.com  | Owner@123 | invalid|
 | owner@infoez.com | Owner@124 | invalid|