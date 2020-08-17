package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import utilities.Utils;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class JSONPlaceHolderSteps extends Utils {

    public static Response userPostCommentsResponse;
    public static ArrayList<Integer> userId, postIds;

    @Given("The username to query {} and get the userId")
    public void usernameToQueryUsernameAndGetTheUserId(String username) {
        Response userResponse = getRequestWithParameter(USERS, "username", username);
        verifyResponseStatusCode(userResponse, HttpURLConnection.HTTP_OK);
        validateJsonResponseSchema(userResponse, "users.json");
        userResponse.then().assertThat().body("id", not(empty()));
        userId = userResponse.path("id");
    }

    @Then("Get all the postIds for the userId")
    public void getAllThePostIdsForTheUserId() {
        Response userPostsResponse = getRequestWithParameter(POSTS, "userId", userId);
        verifyResponseStatusCode(userPostsResponse, HttpURLConnection.HTTP_OK);
        validateJsonResponseSchema(userPostsResponse, "posts.json");
        userPostsResponse.then().assertThat().body("id", not(empty()));
        postIds = userPostsResponse.path("id");
    }

    @And("Get all the comments for the postIds")
    public void getAllTheCommentsForThePostIds() {
        userPostCommentsResponse = getRequestWithParameter(COMMENTS, "postId", postIds);
        verifyResponseStatusCode(userPostCommentsResponse, HttpURLConnection.HTTP_OK);
        validateJsonResponseSchema(userPostCommentsResponse, "comments.json");
    }

    @Then("Validate email format in each comment")
    public void validateEmailFormatInEachComment() {
        userPostCommentsResponse.then().assertThat().body("email", not(empty()));
        validateEmailFormat(userPostCommentsResponse.path("email"));
    }

}
