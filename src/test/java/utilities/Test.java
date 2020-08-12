package utilities;

import io.restassured.response.Response;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;


public class Test {

    public static void main(String[] args) {
        // Get user id
        Response response = given().param("username", "Delphine")
                .get("https://jsonplaceholder.typicode.com/users");
        List<Integer> userIds = response.path("id");
        Integer userId = userIds.get(0);
        System.out.println("UserId: " + userId);

        // Get post ids by user
        response = given()
                .formParam("userId", userId)
                .get("https://jsonplaceholder.typicode.com/posts");
        List<Integer> postIds = response.path("id");
        System.out.println("Post Ids by user: " + postIds);

        // Get emails to verify
        response = given()
                .formParam("postId", postIds)
                .get("https://jsonplaceholder.typicode.com/comments");
        List<String> emails = response.path("email");
        System.out.println("Email to verify: " + emails);

        // Verify email valid
        EmailValidator validator = EmailValidator.getInstance();
        for (String emailToVerify : emails) {
            Assert.assertTrue(validator.isValid(emailToVerify));
        }
    }

}
