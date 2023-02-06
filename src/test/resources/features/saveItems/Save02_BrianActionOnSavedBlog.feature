@action_saveitems @brian @previewSite
Feature: Brain Action On Saved Blogs

  Scenario: Brian Saved Items On Blog
    Given the user logged in as "brian1"
    And the browser opened at "/blog"
    And within the content
    When the user opens action menu
    And the user clicks on menu item "save"
    Then the user sees successful flash message

  Scenario: Brian Sees Items On Saves App
    Given the user logged in as "brian1"
    And the browser opened at "/saved"
    And within the content
    When the user sees text "Brit + Co"

  Scenario: Brian Add Items To Collection On Saved App
    Given the user logged in as "brian1"
    And the browser opened at "/saved"
    And within the content
    When the user opens action menu by aria-controls "addToCollection"
    And the user adds saved items to collection
    Then the user sees successful flash message