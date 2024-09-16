package Tokens;

import static io.restassured.RestAssured.given;

public class OAuth1 {
    //For APIs that use OAuth 1.0 for authentication,
    // you can pass the required credentials (consumer key, consumer secret, token, and token secret) to authenticate.

    void test(){
        given()
                .auth()
                .oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
                .when()
                .get("https://api.example.com/resource")
                .then()
                .statusCode(200);

    }
}
