@app_forum @brian
Feature:  Brian process on Forum

  Scenario: Brian Add New Forum With Empty Value
    Given the user logged in as "brian"
    And the browser opened at "/forum/thread/add"
    And within the content
    When the user clicks on button "buttonSubmit"
    Then the user sees text "Title is required."
    And the user sees text "Content is required. "

  Scenario: Brian Add New Forum
    Given the user logged in as "brian"
    When the browser opened at "/forum/thread/add"
    And the user add value on id "select-forum_id" is "General"
    When the user want to select category at 1 on dropdown list "select-forum_id"
    And the user adds title with value "IsThreadAutotest"
    And the user adds description
    And the user attaches a photo
    And the user adds a tag
    When the user clicks on button "buttonSubmit"
    Then the user sees successful flash message
    And the user sees text "IsThreadAutotest"

  Scenario: Brian verified subscribed threads
    Given the user logged in as "brian"
    When the browser opened at "/forum/subscribed-thread"
    And within the content
    Then the user sees text "IsThreadAutotest"

  Scenario: Another User Reply Thread
    Given the user logged in as "terry"
    When the browser opened at "/forum"
    And within the sidebar
    And the user searches with text "IsThreadAutotest"
    And the user access first condition "inputSort"
    And the user access first condition "inputWhen"
    Given within the content
    And the user clicks on the item title
    Then the user sees text "IsThreadAutotest"
    And the user adds description
    And the user attaches a photo
    When the user clicks on button "buttonSubmit"
    Then the user sees successful flash message
    
  Scenario: Brain Reaction On Thread
    Given the user logged in as "brian"
    When the browser opened at "/forum"
    And within the sidebar
    When the user searches with text "TestAutoDescription"
    And within the content
    And the user clicks on the item title
    Then the user sees text "IsThreadAutotest"
    And the user clicks on button "reactionButton"

  Scenario: Brain Edit Thread
    Given the user logged in as "brian"
    When the user clicks on "More"
    When the browser opened at item "forum" and tab ""
    And the user searches with text "TestAutoDescription"
    When the user want to click on button label "Action Menu" and process
    And the user clicks on "Edit Thread"
    And the user sees item of "Remove"
    Then the user sees text "Are you sure you want to delete this attachment file?"
    And the user want to "OK"
    Then the user don't see "Remove" displayed on screen

  Scenario: Brain move thread to another forum
    Given the user logged in as "brian"
    When the user clicks on "More"
    When the browser opened at item "forum" and tab ""
    And the user searches with text "TestAutoDescription"
    When the user want to click on button label "Action Menu" and process
    And the user clicks on "Move Thread"
    When the user want to select category at 4 on dropdown list "select-forum_id"
    And the user clicks on button "buttonSubmit"
    Then the user sees successful flash message
    And the user sees text "IsThreadAutotest"

  Scenario: Brain copy thread with default title
    Given the user logged in as "brian"
    When the user clicks on "More"
    When the browser opened at item "forum" and tab ""
    And the user searches with text "TestAutoDescription"
    When the user want to click on button label "Action Menu" and process
    And the user clicks on "Copy Thread"
    Then the user sees text "Copy Thread "
    #When the user clicks on input field "inputTitle" with value ""
    And the user want to "Copy"
    Then the user sees successful flash message
    When the user "new_notification" this item
    And the user clicks on notification "Your thread copying process has been completed. Review now" and process
    Then the user sees text "Copy - IsThreadAutotest"
    And the user clicks on button "actionMenuButton"
    And the user clicks on "Delete Thread"
    Then the user sees text "Are you sure you want to delete this thread permanently?"
    And the user want to "OK"
    Then the user sees successful flash message

  Scenario: Admin Stick Thread
    Given the user logged in as "admin"
    When the user clicks on "More"
    When the browser opened at item "forum" and tab ""
    And the user searches with text "IsThreadAutotest"
    And the user access first condition "inputSort"
    And the user access first condition "inputWhen"
    When the user want to click on button label "Action Menu" and process
    And the user clicks on "Stick Thread"
    Then the user sees successful flash message
    When the user want to click on button label "actionMenu" and process
    And the user clicks on "Unstick Thread"
    Then the user sees successful flash message

  Scenario: Brian verify on history
    Given the user logged in as "brian"
    When the user clicks on "More"
    When the browser opened at item "forum" and tab "/forum/history-thread"
    Then the user sees text "IsThreadAutotest"

  Scenario: Brian closed thread
    Given the user logged in as "brian"
    When the user clicks on "More"
    When the browser opened at item "forum" and tab ""
    And the user searches with text "TestAutoDescription"
    When the user want to click on button label "Action Menu" and process
    And the user clicks on "Close Thread"
    And the user want to "OK"
    Then the user sees successful flash message
    And the user sees text "Closed" on tab
    When the user want to click on button label "actionMenu" and process
    And the user clicks on "Reopen Thread"
    Then the user sees successful flash message
