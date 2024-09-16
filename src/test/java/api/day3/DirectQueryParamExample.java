package api.day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DirectQueryParamExample {
    @Test
    void testDirectQueryParamExample(){
        given()
                .baseUri("https://api.example.com")
                .when()
                .get("/users?name=John&age=30")  // Pass query parameters directly in URL
                .then()
                .statusCode(200)
                .log().all();  // Log the response

    }
}
