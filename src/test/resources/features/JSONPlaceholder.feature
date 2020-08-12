Feature: JSONPlaceholder

  Scenario Outline: Query user <username> posts and validate email format
    Given Username to query <username> and get the userId
    Then Get all the postIds for the userId
    And Get all the comments for the postIds
    Then Validate email format in each comment
    Examples:
      | username |
      | Delphine |
      | Samantha |