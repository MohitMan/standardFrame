Feature: Application Login

  Scenario: Login to application
    Given User hit URL
    When User enter credentials to access application
    Then verify home page of application
