package Extract;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ExtractArray {
    @Test
    void testExtractArray(){
        // Send GET request and extract the 'phoneNumbers' array
        Response response = given()
                .baseUri("https://reqres.in")
                .when()
                .get("/api/users?page=2");

        // Extract 'phoneNumbers' array as a list of maps
        List<Map<String, String>> phoneNumbers = response.jsonPath().getList("data");

        // Print the extracted phone numbers
        System.out.println("Phone Numbers: " + phoneNumbers.get(0).get("email"));


    }
}
