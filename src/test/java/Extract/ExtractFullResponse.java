package Extract;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExtractFullResponse {
    //You can extract the full response body as a string or other formats like XML or a specific type.

    @Test
    void testExtractFullResponse(){
        // Send GET request and extract the entire response body as a string
        String responseBody = given()
                .baseUri("https://reqres.in")
                .when()
                .get("/api/users?page=2")
                .then()
                .extract()
                .asString();  // Extract full response as a string

        // Print the full response body
        System.out.println("Response Body: " + responseBody);
    }
}
