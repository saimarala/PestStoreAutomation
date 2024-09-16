package Extract;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

public class ParseJsonResponse {
    @Test
    void testParse(){
        // Set the base URI for the API
        RestAssured.baseURI = "https://api.example.com";  // Replace with your actual API base URL

        // Send GET request and capture the response
        Response response = RestAssured
                .given()
                .when()
                .get("/user/123");  // Replace with your endpoint

        // Extract the response body as a JsonPath object
        JsonPath jsonPath = response.jsonPath();

        // Parse specific fields from the JSON response

        int id = jsonPath.getInt("id");  // Extract integer
        String name = jsonPath.getString("name");  // Extract string
        String street = jsonPath.getString("address.street");  // Extract nested field
        String city = jsonPath.getString("address.city");  // Extract nested field
        String homePhone = jsonPath.getString("phoneNumbers.find { it.type == 'home' }.number");  // Extract from array
        String officePhone = jsonPath.getString("phoneNumbers.find { it.type == 'office' }.number");

        // Print the extracted data
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Street: " + street);
        System.out.println("City: " + city);
        System.out.println("Home Phone: " + homePhone);
        System.out.println("Office Phone: " + officePhone);
    }


}
