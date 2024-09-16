package api.day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicValueExtraction {
    //Example: Extracting a Dynamic User ID

    @Test
    void testDynamicValueExtraction(){
       // Send POST request and get the response
        Response response = given()
                .baseUri("https://api.example.com")
                .contentType("application/json")
                .body("{ \"name\": \"Jane Doe\", \"email\": \"jane.doe@example.com\" }")
                .when()
                .post("/users");

        // Extract dynamic values like 'id' and 'token'
        int userId = response.jsonPath().getInt("id");
        String token = response.jsonPath().getString("token");

        // Print the dynamic values
        System.out.println("User ID: " + userId);
        System.out.println("Token: " + token);

    }
}
