package Tokens;

import static io.restassured.RestAssured.given;

public class DigestAuthentication {
    //Digest Authentication is a more secure form of authentication compared to Basic Authentication,
    // where the credentials are hashed before sending.
    void test(){
        given()
                .auth()
                .digest("admin", "password123")
                .when()
                .get("https://api.example.com/users")
                .then()
                .statusCode(200);
    }
}
