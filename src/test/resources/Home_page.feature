Feature: Citizen Disbursement System


  Scenario: 1. As the Clerk, I should be able to insert a single record of working class hero into database via an API
    Given user prepare single record for insertion
    When user trigger single insertion api
    Then user can see single working class hero available in system

  Scenario: 2. As the Clerk, I should be able to insert a multiple record of working class hero into database via an API
    Given user prepare multiple record for insertion
      |natid  |name |gender|birthday|salary|tax|
      |234567|Tharun|M     |10081990|100300|133|
      |345678|Teja  |F     |29091979|4423444|3224|
    When user trigger multiple insertion api
    Then user can see multiple working class hero's available in system

  Scenario: 3. As the Clerk, I should be able to upload a csv file to a portal so that I can populate the database from a UI
    Given user open the home page
    And user fetch already available work class hero count
    When user bulk upload csv for list of working class heros
    Then user can see bulk working class hero's available in system

  Scenario: 4. As the Bookkeeper, I should be able to query the amount of tax relief for each person in the database so that I can report the figures to my Bookkeeping Manager
    Given user should collect existing working class hero in system
    When user fetch tax relief of each working class hero from system
    Then user can report tax relief of each working clas hero


  Scenario: 5. As the Governor, I should be able to see a button on the screen so that I can dispense tax relief for my working class heroes
    Given user open the home page
    When user click on dispense button
    Then user can see the successfull dispension