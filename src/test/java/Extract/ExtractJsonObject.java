package Extract;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ExtractJsonObject {

    @Test
    void testExtractJsonObject(){
        // Send GET request and extract the nested object 'address'
        Response response = given()
                .baseUri("https://reqres.in")
                .when()
                .get("/api/users?page=2")
                .then()
                .extract().response();

        // Extract 'address' object
        Map<String, String> address = response.jsonPath().getMap("$");

        // Print the extracted address object
        System.out.println("Address: " + address.get("email"));
    }
}
