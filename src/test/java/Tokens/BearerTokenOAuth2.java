package Tokens;

import static io.restassured.RestAssured.given;

public class BearerTokenOAuth2 {
//Bearer Token or OAuth2 authentication is used in modern APIs where an
// access token (JWT or other token types) is used in the Authorization header.
    void test(){
        given()
                .auth()
                .oauth2("abc123xyz-token")
                .when()
                .get("https://api.example.com/profile")
                .then()
                .statusCode(200);
        //You can also add the token manually as a header:
        given()
                .header("Authorization", "Bearer abc123xyz-token")
                .when()
                .get("https://api.example.com/profile")
                .then()
                .statusCode(200);


    }
}
