package api.day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ExtractJsonObject {

    @Test
    void testExtractJsonObject(){
        // Send GET request and extract the nested object 'address'
        Response response = given()
                .baseUri("https://api.example.com")
                .when()
                .get("/users/123");

        // Extract 'address' object
        Map<String, String> address = response.jsonPath().getMap("address");

        // Print the extracted address object
        System.out.println("Address: " + address);
    }
}
