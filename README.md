# JSONPlaceholder

JSONPlaceholder is a REST service automation testing framework using:

- [Cucumber JVM](https://cucumber.io/) Behavior-Driven Development tool
- [REST-assured](http://rest-assured.io/) library for REST service testing
- [REST-assured json-schema-validator](https://javadoc.io/doc/io.rest-assured/json-schema-validator/latest/index.html) for JSON schema validations
- [Apache Commons Validator](https://commons.apache.org/proper/commons-validator/) for email format validation
- [Cluecumber Report Maven Plugin](https://github.com/trivago/cluecumber-report-plugin) for Cucumber test reporting

## Pre-requisites

- Java JDK version 8 or above should be installed
- Maven should be installed

## Approach

- Using open source libraries and effective tools for automation testing
- Using Cucumber BDD to easily handle data inside feature file and for easy understanding
- Using json-schema-validator to validate all the Api response fields

## Test-Coverage

- All the response fields are validated using *json-schema-validator*
- Email format validation is done using *Apache Commons Validator* and *json-schema-validator* to demonstrate different ways of validating email format

**Flow Tested:**

- Search for the user with username “Delphine”
- Use the details fetched to make a search for the posts written by the user
- For each post, fetch the comments and validate if the emails in the comment section are in the proper format

## Test-Report

**Test Execution Report:**

![Report](/images/Report_Image.png)  

**Circleci Pipeline Report:**

![Report](/images/circleci_report.png)  

## Executing Tests

**Maven Commands:**

- Run the project - `mvn clean test`
- Run tests with cucumber tags - `-Dcucumber.filter.tags="@JSONPlaceholder"`

## Reports

- Generate the report using maven command - `mvn cluecumber-report:reporting`
- Reports will be generated in `target/generated-reports/index.html` after running the tests using maven commands