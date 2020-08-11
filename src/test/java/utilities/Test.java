package utilities;

import io.restassured.response.Response;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class Test {

    public static void main(String[] args) {
        // Get user id
        Response response = given().get("https://jsonplaceholder.typicode.com/users");
        Integer userId = 0;
        List<String> names = response.path("username");
        List<Integer> userIds = response.path("id");
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase("Delphine"))
                userId = userIds.get(i);
        }
        System.out.println("UserId: " + userId);

        // Get post ids by user
        response = given().get("https://jsonplaceholder.typicode.com/posts");
        List<Integer> postIdsByUser = new ArrayList<>();
        userIds = response.path("userId");
        List<Integer> postIds = response.path("id");
        for (int j = 0; j < userIds.size(); j++) {
            if (userIds.get(j).equals(userId))
                postIdsByUser.add(postIds.get(j));
        }
        System.out.println("Post Ids by user: " + postIdsByUser);

        // Get emails to verify
        response = given().get("https://jsonplaceholder.typicode.com/comments");
        List<String> emailsToVerify = new ArrayList<>();
        postIds = response.path("id");
        List<String> emails = response.path("email");
        for (int k = 0; k < postIds.size(); k++) {
            for (int postIdByUser : postIdsByUser) {
                if (postIds.get(k) == postIdByUser)
                    emailsToVerify.add(emails.get(k));
            }
        }
        System.out.println("Email to verify: " + emailsToVerify);

        // Verify email valid
        EmailValidator validator = EmailValidator.getInstance();
        for (String emailToVerify : emailsToVerify) {
            Assert.assertTrue(validator.isValid(emailToVerify));
        }
    }

}
