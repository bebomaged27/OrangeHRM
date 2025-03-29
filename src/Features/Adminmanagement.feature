Feature: Admin user management

Scenario: Add and delete a user
  Given I am on the login page
  When I log in with "Admin" and "admin123"
  And I navigate to the Admin tab
  And I add a new user with username "abanoubmaged", password "123456789a", employee name "Ranga", role "0", and status "Enabled"
 Then the number of records should increase by 1
  When I search for "Abanoubmaged"
  And I delete the user
  #Then the number of records should decrease by 1