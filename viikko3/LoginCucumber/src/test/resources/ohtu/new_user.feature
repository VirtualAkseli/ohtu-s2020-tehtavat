Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When  new username "akseli" and password "sala1nen" are entered
        Then  system will respond with "new user registered"
    
    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When  new username "pekka" and password "akkep" are entered
        Then  system will respond with "new user not registered"
    
    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When  new username "us20" and password "sala1nen" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When  new username "absalom" and password "salan3n" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting of only letters
        Given command new is selected
        When  new username "absalom" and password "kahdeksan" are entered
        Then  system will respond with "new user not registered"

    Scenario: can login with successfully generated account
        Given user "eero" with password "salainen1" is created
        And   command login is selected
        When  username "eero" with password "salainen1" is entered
        Then  system will respond with "logged in"
