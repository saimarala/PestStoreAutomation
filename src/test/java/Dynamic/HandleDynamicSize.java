package Dynamic;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HandleDynamicSize {

    @Test
    void testHandleDynamicSize(){
        // Get the response with a dynamic list of users
        Response response = given()
                .baseUri("https://api.example.com")
                .when()
                .get("/users");

        // Extract the size of the 'users' array
        int userCount = response.jsonPath().getList("users").size();

        // Print the user count
        System.out.println("Total Users: " + userCount);
    }
}
