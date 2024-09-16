package Extract;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExtractSingleValue {

    @Test
    void testExtractSingleValue()  {
        // Send GET request and extract single fields{
        Response response = given()
                .baseUri("https://api.example.com")
                .when()
                .get("/users/123");

        // Extract individual fields
        int id = response.jsonPath().getInt("id");  // Extract 'id' field as an integer
        String name = response.jsonPath().getString("name");  // Extract 'name' field as a string
        String email = response.jsonPath().getString("email");  // Extract 'email' field as a string

        // Print extracted data
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);

    }

}
