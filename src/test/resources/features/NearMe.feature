Feature: Infoez Near Me Map Page 

Scenario: Navigate to Near Me from Home 
Given user is on the Infoez home landing page
When user clicks on the Near me navigation link
Then user should be redirected to the Near Me page with URL containing "/map"
And the Near Me page should display heading "Businesses on map"
When user enters search nearby keyword "Artisan" in the search box
And clicks on Apply filter button
