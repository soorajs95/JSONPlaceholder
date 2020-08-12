package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Assert;
import utilities.Utilities;

import java.util.ArrayList;

public class JSONPlaceHolderSteps extends Utilities {

    public static Response jsonPlaceHolderUserResponse, jsonPlaceHolderUserPostsResponse, jsonPlaceHolderUserPostCommentsResponse;
    public static ArrayList<Integer> userId, postIds;
    public static ArrayList<String> emailIds;

    @Given("Username to query {} and get the userId")
    public void usernameToQueryUsernameAndGetTheUserId(String username) {
        jsonPlaceHolderUserResponse = getRequestWithParameter(USERS, "username", username);
        userId = jsonPlaceHolderUserResponse.path("id");
        Assert.assertTrue("", userId.size() > 0);
    }

    @Then("Get all the postIds for the userId")
    public void getAllThePostIdsForTheUserId() {
        jsonPlaceHolderUserPostsResponse = getRequestWithParameter(POSTS, "userId", userId);
        postIds = jsonPlaceHolderUserPostsResponse.path("id");
        Assert.assertTrue("", postIds.size() > 0);
    }

    @And("Get all the comments for the postIds")
    public void getAllTheCommentsForThePostIds() {
        jsonPlaceHolderUserPostCommentsResponse = getRequestWithParameter(COMMENTS, "postId", postIds);
    }

    @Then("Validate email format in each comment")
    public void validateEmailFormatInEachComment() {
        emailIds = jsonPlaceHolderUserPostCommentsResponse.path("email");
        emailIds.forEach(email -> Assert.assertTrue("", EmailValidator.getInstance().isValid(email)));
    }

}
