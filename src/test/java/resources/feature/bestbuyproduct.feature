Feature: Testing different request on Bestbuy Application

  Scenario: check if Bestbuy application can be access by User
    When User sends a Get request
    Then User should get list of products and must get back a valid status code 200

  Scenario:Create a new product and verify if product is added
    When User creates new product record with all information
    Then I verify that new product is created and must get valid status code 201

  Scenario:Update product with new details and verify if product is updated
    When User can update new record with all product details
    Then I verify that product is updated

  Scenario:Product can be deleted and verify if product is deleted
    When User can delete new record
    Then I verify product is deleted and must get valid staus code 404
