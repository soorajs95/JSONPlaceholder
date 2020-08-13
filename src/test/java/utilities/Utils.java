package utilities;

import io.restassured.response.Response;
import org.junit.Assert;

import java.util.ArrayList;

import static io.restassured.RestAssured.with;


public class Utils {

    public static String JSON_PLACEHOLDER_HOST = "https://jsonplaceholder.typicode.com";
    public static String USERS = "/users";
    public static String POSTS = "/posts";
    public static String COMMENTS = "/comments";

    public static Response getRequestWithParameter(String URI, String parameterKey, String parameterValue) {
        return with().params(parameterKey, parameterValue).given().get(JSON_PLACEHOLDER_HOST + URI);
    }

    public static Response getRequestWithParameter(String URI, String parameterKey, ArrayList<Integer> parameterValue) {
        return with().params(parameterKey, parameterValue).given().get(JSON_PLACEHOLDER_HOST + URI);
    }

    public static void verifyResponseCode(Response response, int statusCode) {
        Assert.assertEquals("Response status code did not match", statusCode, response.statusCode());
    }

}
