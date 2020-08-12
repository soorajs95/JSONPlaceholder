package utilities;

import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;


public class Utilities {

    public static String JSON_PLACEHOLDER_HOST = "https://jsonplaceholder.typicode.com";
    public static String USERS = "/users";
    public static String POSTS = "/posts";
    public static String COMMENTS = "/comments";

    public static Response getRequest(String URI) {
        return given().get(JSON_PLACEHOLDER_HOST + URI);
    }

    public static Response getRequestWithParameter(String URI, String parameterKey, String parameterValue) {
        return with().params(parameterKey, parameterValue).given().get(JSON_PLACEHOLDER_HOST + URI);
    }

    public static Response getRequestWithParameter(String URI, String parameterKey, String[] parameterValue) {
        return with().params(parameterKey, Arrays.asList(parameterValue)).given().get(JSON_PLACEHOLDER_HOST + URI);
    }

    public static Response getRequestWithParameter(String URI, String parameterKey, ArrayList<Integer> parameterValue) {
        return with().params(parameterKey, parameterValue).given().get(JSON_PLACEHOLDER_HOST + URI);
    }

}
