package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.commons.validator.routines.EmailValidator;
import utilities.Utils;

import javax.net.ssl.HttpsURLConnection;
import java.util.ArrayList;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JSONPlaceHolderSteps extends Utils {

    public static Response userPostCommentsResponse;
    public static ArrayList<Integer> userId, postIds;

    @Given("Username to query {} and get the userId")
    public void usernameToQueryUsernameAndGetTheUserId(String username) {
        Response userResponse = getRequestWithParameter(USERS, "username", username);
        userResponse.then().log().ifError().assertThat().statusCode(HttpsURLConnection.HTTP_OK)
                .assertThat().body(matchesJsonSchemaInClasspath("schemas/users.json"));
        userId = userResponse.path("id");
        assertThat("No userId found for user " + username, userId.size(), greaterThan(0));
    }

    @Then("Get all the postIds for the userId")
    public void getAllThePostIdsForTheUserId() {
        Response userPostsResponse = getRequestWithParameter(POSTS, "userId", userId);
        userPostsResponse.then().log().ifError().assertThat().statusCode(HttpsURLConnection.HTTP_OK)
                .assertThat().body(matchesJsonSchemaInClasspath("schemas/posts.json"));
        postIds = userPostsResponse.path("id");
        assertThat("No postIds found for userID " + userId, postIds.size(), greaterThan(0));
    }

    @And("Get all the comments for the postIds")
    public void getAllTheCommentsForThePostIds() {
        userPostCommentsResponse = getRequestWithParameter(COMMENTS, "postId", postIds);
        userPostCommentsResponse.then().log().ifError().assertThat().statusCode(HttpsURLConnection.HTTP_OK)
                .assertThat().body(matchesJsonSchemaInClasspath("schemas/comments.json"));
    }

    @Then("Validate email format in each comment")
    public void validateEmailFormatInEachComment() {
        ArrayList<String> emailIds = userPostCommentsResponse.path("email");
        emailIds.forEach(email -> assertThat("Email not valid: " + email, EmailValidator.getInstance().isValid(email), is(equalTo(true))));
    }

}
