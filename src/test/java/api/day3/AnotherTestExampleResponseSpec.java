package api.day3;

import static api.day3.ResponseSpecExample.responseSpec;
import static io.restassured.RestAssured.given;

public class AnotherTestExampleResponseSpec {

    void test(){
        // Use the response specification in a test
        given()
                .baseUri("https://api.example.com")
                .when()
                .get("/someEndpoint")
                .then()
                .spec(responseSpec)  // Apply the response specification
                .log().all();  // Log the response
    }
}
