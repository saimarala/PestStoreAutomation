package QueryParam;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MultipleQueryParamsExample {
    //https://api.example.com/users?name=John&age=30
//    The queryParams() method adds multiple parameters (?name=John&age=30) to the request URL.
//    You can pass a Map<String, Object> to queryParams() to handle multiple parameters dynamically.

    @Test
    void testMultipleQueryParamsExample(){
        // Create a Map to hold query parameters
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", "John");
        queryParams.put("age", 30);

        given()
                .baseUri("https://api.example.com")
                .queryParams(queryParams)  // Pass multiple query parameters
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .log().all();  // Log the response
    }
}
