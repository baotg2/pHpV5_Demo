@app_core @blockUser @brian @previewSite
Feature:  Brain Process On Block Friend

  Scenario: Brian Block User
    Given the user logged in as "brian"
    And the browser opened at "/krixi"
    And within the slot top
    When the user "Block this user"
    And the user accepts the confirm
    When within the content
    And the user "Unblock"
    Then the user sees page url contains "/settings/blocked"
    Then the user sees successful flash message