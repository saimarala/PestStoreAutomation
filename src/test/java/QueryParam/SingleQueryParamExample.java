package QueryParam;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SingleQueryParamExample {

    //https://api.example.com/users?name=John
    //queryParam("name", "John") adds the query parameter ?name=John to the request.
    //The full URL will be: https://api.example.com/users?name=John.
    @Test
    void testSingleQueryParamExample(){

        given()
                .baseUri("https://api.example.com")
                .queryParam("name", "John")  // Pass query parameter
                .when()
                .get("/users")
                .then()
                .statusCode(200)  // Validate the response code
                .log().all();  // Log the response

    }
}
