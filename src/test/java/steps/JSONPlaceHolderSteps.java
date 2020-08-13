package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Assert;
import utilities.Utils;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class JSONPlaceHolderSteps extends Utils {

    public static Response userResponse, userPostsResponse, userPostCommentsResponse;
    public static ArrayList<Integer> userId, postIds;
    public static ArrayList<String> emailIds;

    @Given("Username to query {} and get the userId")
    public void usernameToQueryUsernameAndGetTheUserId(String username) {
        try {
            userResponse = getRequestWithParameter(USERS, "username", username);
            verifyResponseCode(userResponse, HttpURLConnection.HTTP_OK);
            userId = userResponse.path("id");
            Assert.assertTrue("No userId found for user " + username, userId.size() > 0);
        } catch (Exception e) {
            Assert.fail("Failed to query username and get userID: " + e);
        }
    }

    @Then("Get all the postIds for the userId")
    public void getAllThePostIdsForTheUserId() {
        try {
            userPostsResponse = getRequestWithParameter(POSTS, "userId", userId);
            verifyResponseCode(userPostsResponse, HttpURLConnection.HTTP_OK);
            postIds = userPostsResponse.path("id");
            Assert.assertTrue("No postIds found for userID " + userId, postIds.size() > 0);
        } catch (Exception e) {
            Assert.fail("Failed to get postIds for userId: " + e);
        }
    }

    @And("Get all the comments for the postIds")
    public void getAllTheCommentsForThePostIds() {
        try {
            userPostCommentsResponse = getRequestWithParameter(COMMENTS, "postId", postIds);
            verifyResponseCode(userPostCommentsResponse, HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            Assert.fail("Failed to get comments for postIds: " + e);
        }
    }

    @Then("Validate email format in each comment")
    public void validateEmailFormatInEachComment() {
        try {
            emailIds = userPostCommentsResponse.path("email");
            emailIds.forEach(email -> Assert.assertTrue("Email not valid: " + email, EmailValidator.getInstance().isValid(email)));
        } catch (Exception e) {
            Assert.fail("Failed to validate email format in comments: " + e);
        }
    }

}
