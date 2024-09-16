package api.day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ExtractList {

    @Test
    void testExtractList(){
        // Send GET request and extract the 'type' from phoneNumbers as a list
        Response response = given()
                .baseUri("https://api.example.com")
                .when()
                .get("/users/123");

        // Extract 'type' fields from the 'phoneNumbers' array as a list of strings
        List<String> phoneTypes = response.jsonPath().getList("phoneNumbers.type");

        // Print the extracted phone types
        System.out.println("Phone Types: " + phoneTypes);
    }
}