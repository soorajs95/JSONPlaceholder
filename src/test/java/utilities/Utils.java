package utilities;

import io.restassured.response.Response;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Utils {

    public static String JSON_PLACEHOLDER_HOST = "https://jsonplaceholder.typicode.com";
    public static String USERS = "/users";
    public static String POSTS = "/posts";
    public static String COMMENTS = "/comments";

    public static Response getRequestWithParameter(String URI, String parameterKey, String parameterValue) {
        return given()
                .queryParam(parameterKey, parameterValue)
                .get(JSON_PLACEHOLDER_HOST + URI);
    }

    public static Response getRequestWithParameter(String URI, String parameterKey, ArrayList<Integer> parameterValue) {
        return given()
                .queryParam(parameterKey, parameterValue)
                .get(JSON_PLACEHOLDER_HOST + URI);
    }

    public static void verifyResponseStatusCode(Response response, int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    public static void validateJsonResponseSchema(Response response, String jsonFileName) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/" + jsonFileName));
    }

    public static void validateEmailFormat(ArrayList<String> emails) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        emails.forEach(email -> assertThat("Email not valid: " + email, emailValidator.isValid(email), is(true)));
    }

}
