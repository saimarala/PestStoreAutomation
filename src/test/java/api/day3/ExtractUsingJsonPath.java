package api.day3;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExtractUsingJsonPath {
    //JsonPath is a powerful way to query and extract data from JSON responses in Rest Assured.
    // You can extract data from JSON using path expressions, similar to how you query XML using XPath.


    @Test
    void testExtractUsingJsonPath(){
        // Send GET request and extract response body as a JsonPath object
        JsonPath jsonPath = given()
                .baseUri("https://api.example.com")
                .when()
                .get("/users/123")
                .jsonPath();

        // Extract individual fields using JsonPath
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");

        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
    }
}
