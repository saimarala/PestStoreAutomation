package Tokens;

import static io.restassured.RestAssured.given;

public class FormAuthentication {
// .form() method is used to send the form parameters.
    void test(){
        given()
                .auth()
                .form("user", "password123")
                .when()
                .post("https://api.example.com/login")
                .then()
                .statusCode(200);

    }
}
