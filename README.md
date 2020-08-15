# JSONPlaceholder

JSONPlaceholder is a REST service automation testing framework using:

- [Cucumber JVM](https://cucumber.io/) Behavior-Driven Development tool
- [REST-assured](http://rest-assured.io/) library for REST service testing
- [REST-assured json-schema-validator](https://javadoc.io/doc/io.rest-assured/json-schema-validator/latest/index.html) for JSON schema validations
- [Apache Commons Validator](https://commons.apache.org/proper/commons-validator/) for email format validation
- [Cluecumber Report Maven Plugin](https://github.com/trivago/cluecumber-report-plugin) for Cucumber test reporting

## Test-Coverage


## Test-Report

**Test Execution Report:**

![Report](/images/Report_Image.png)  

**Circleci Pipeline Report:**

![Report](/images/circleci_report.png)  

## Approach

- Using open source libraries and effective tools for automation testing
- Using Cucumber BDD to easily handle data inside feature file and for easy understanding

## Executing Tests

**Maven Commands:**

- Run the project - `mvn clean test`
- Run tests with cucumber tags - `-Dcucumber.filter.tags="@JSONPlaceholder"`

## Reports

- Generate the report using maven command - `mvn cluecumber-report:reporting`
- Reports will be generated in `target/generated-reports/index.html` after running the tests using maven commands