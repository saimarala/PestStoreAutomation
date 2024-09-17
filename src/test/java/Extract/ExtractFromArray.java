package Extract;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExtractFromArray {

    @Test
    void testExtractFromArray(){
        // Get the list of users
        String response = given()
                .baseUri("https://reqres.in")
                .when()
                .get("/api/users?page=2")
                .asString();

        // Use JsonPath to find the ID of "Jane Doe"
        JsonPath jsonPath = new JsonPath(response);
        int janeDoeId = jsonPath.getInt("users.find { it.name == 'Jane Doe' }.id");

        // Print the extracted ID
        System.out.println("Jane Doe's ID: " + janeDoeId);
    }
}
