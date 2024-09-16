package Tokens;

import static io.restassured.RestAssured.given;

public class PreemptiveBasicAuthentication {
    //.preemptive().basic() sends the credentials immediately without waiting for a challenge.

    void test(){
        given()
                .auth()
                .preemptive()
                .basic("user", "password123")
                .when()
                .get("https://api.example.com/secure-data")
                .then()
                .statusCode(200);


    }
}
