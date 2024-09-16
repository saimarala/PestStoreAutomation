package api.day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserIdUsage {

    @Test
    void testUserIdUsage(){
        // Extract dynamic user ID from a POST response
        Response postResponse = given()
                .baseUri("https://api.example.com")
                .contentType("application/json")
                .body("{ \"name\": \"Jane Doe\", \"email\": \"jane.doe@example.com\" }")
                .when()
                .post("/users");

        int userId = postResponse.jsonPath().getInt("id");  // Extract user ID

        // Use the dynamic user ID in a GET request
        given()
                .baseUri("https://api.example.com")
                .when()
                .get("/users/" + userId)  // Use dynamic user ID in the URL
                .then()
                .statusCode(200)
                .body("id", equalTo(userId));  // Validate the user ID in the response
    }
}
