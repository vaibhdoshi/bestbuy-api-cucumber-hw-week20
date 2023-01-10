Feature: Testing different request on Bestbuy Application

  Scenario: check if Bestbuy application can be access by User
    When User sends a Get request for store
    Then User should get list of stores and must get back a valid status code 200

  Scenario:Create a new store and verify if store is added
    When User creates new store record with all information
    Then I verify that new store is created and must get valid status code 201

  Scenario:Update store with new details and verify if store is updated
    When User can update new record with all store details
    Then I verify that store is updated

  Scenario:Product can be deleted and verify if store is deleted
    When User can delete new record for store
    Then I verify store is deleted and must get valid status code 404