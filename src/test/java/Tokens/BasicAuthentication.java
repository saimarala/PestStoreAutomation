package Tokens;

import static io.restassured.RestAssured.given;

public class BasicAuthentication {
    void test(){
        //.auth().basic("username", "password") adds the Basic Authentication credentials to the request.
        given()
                .auth()
                .basic("admin", "password123")
                .when()
                .get("https://api.example.com/users")
                .then()
                .statusCode(200);
    }
}
