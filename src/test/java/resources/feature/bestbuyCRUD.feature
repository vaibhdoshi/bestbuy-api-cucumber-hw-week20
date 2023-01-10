Feature: Run CRUD operation on BestBUY API
  user should be able to create,read,update and delete products and store details

  Scenario Outline: User can run successful CRUD operation on store details
    When    User creates new store record with "<name>", "<type>", "<address>", "<address2>", "<city>", "<state>", "<zip>", "<lat>", "<lng>", "<hours>"
    And     User can search new record using store "<name>"
    And     User can update new record using storeID, name, "<type>", "<address>", "<address2>", "<city>", "<state>", "<zip>", "<lat>", "<lng>", "<hours>"
    And     User can delete new record of storeID
    Then    User is able to run successful CRUD operation on store details
    Examples:
      | name          | type   | address        | address2     | city   | state | zip   | lat       | lng       | hours                                                                         |
      | Milton Keynes | BigBox | 121st Street A | 52 High Road | London | MN    | 25687 | 44.879314 | 93.077156 | Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8 |


  Scenario Outline: User can run successful CRUD operation on product details
    When            User creates new product record with "<name>", "<type>", "<price>", "<upc>", "<shipping>", "<description>", "<manufacturer>", "<model>", "<url>", "<image>"
    And             User can search new record using product "<name>"
    And             User can update new record using productID, name, "<type>", "<price>", "<upc>", "<shipping>", "<description>", "<manufacturer>", "<model>", "<url>", "<image>"
    And             User can delete new record of productID
    Then            User is able to run successful CRUD operation on product details
    Examples:
      | name           | type  | price | upc          | shipping | description       | manufacturer | model     | url                                                                                                   | image                                                                |
      | Children Books | Books | 3.49  | 041333424019 | 1234     | Suitable for kids | Amazon       | MN2400B4Z | htt://www.bestbuy.com/site/Amazon-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC | htt://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg |
