package api.day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExtractUsingPath {
    //The .path() method extracts values directly from the response, and it can be used similarly to .jsonPath().

    @Test
    void testExtractUsingPath(){
        // Send GET request and extract fields using .path()
        String city = given()
                .baseUri("https://api.example.com")
                .when()
                .get("/users/123")
                .then()
                .extract()
                .path("address.city");  // Extract 'city' from nested 'address'

        System.out.println("City: " + city);
    }
}
