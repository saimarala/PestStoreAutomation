package Tokens;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TokenStorage {
    static String token;
    @Test
    void testTokenStorage(){
        // Extract token from the response and store it
        Response response = given()
                .baseUri("https://api.example.com")
                .contentType("application/json")
                .body("{ \"name\": \"Jane Doe\", \"email\": \"jane.doe@example.com\" }")
                .when()
                .post("/users");

        // Store the extracted token in a static variable
        token = response.jsonPath().getString("token");

        // Use the token in a subsequent request
        given()
                .baseUri("https://api.example.com")
                .header("Authorization", "Bearer " + token)  // Use dynamic token
                .when()
                .get("/user/profile")
                .then()
                .statusCode(200);
    }
}
