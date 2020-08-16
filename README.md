# JSONPlaceholder

JSONPlaceholder is a REST service automation testing framework using:

- [REST-assured](http://rest-assured.io/) library for REST service testing
- [REST-assured json-schema-validator](https://javadoc.io/doc/io.rest-assured/json-schema-validator/latest/index.html) for JSON schema validations
- [Apache Commons Validator](https://commons.apache.org/proper/commons-validator/) for email format validation
- [Cucumber JVM](https://cucumber.io/) Behavior-Driven Development tool
- [Cluecumber Report Maven Plugin](https://github.com/trivago/cluecumber-report-plugin) for Cucumber test reporting

## Pre-requisites

- Java JDK version 8 or above should be installed
- Maven should be installed

## Approach

- Using open source libraries and effective tools for automation testing
- Using *Cucumber BDD* for handling test data inside feature file and for easy understanding of test flow
- Using *json-schema-validator* for validating all the Api response formats and patterns

## Test Flow

- Search for the user with username, Eg: Delphine
- Use the details fetched to make a search for the posts written by the user
- For each post, fetch the comments and validate if the emails in the comment section are in the proper format


## Test-Coverage

- All the Api response status codes are validated
- All the response formats and patterns are validated using *json-schema-validator*
- All the possible edge cases are handled, Eg: Username doesn't exist, UserId doesn't have any posts
- Email format validations are done using *Apache Commons Validator* and *json-schema-validator* to demonstrate different ways of validating email format

## Test-Report

**Test Execution Report:**

![Test report](/images/Report_Image.png)  

**circleci Pipeline Execution:**

[circleci configuration](/.circleci/config.yml)

![circleci execution](/images/circleci_execution.png)  

## Executing Tests

**Maven Commands:**

- Run the project - `mvn clean test`
- Run tests with cucumber tags - `-Dcucumber.filter.tags="@JSONPlaceholder"`

## Reports

- Generate the report using maven command - `mvn cluecumber-report:reporting`
- Reports will be generated in `target/generated-reports/index.html` after running the tests using maven commands