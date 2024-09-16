package Tokens;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SessionBasedAuthentication {
    void test(){
        //For APIs that use session-based authentication, you can store the session ID after the login request and
        // reuse it in subsequent requests by setting the session ID in the cookie.
        // Login request to get the session ID
        Response loginResponse = given()
                .auth()
                .form("username", "password")
                .when()
                .post("https://api.example.com/login");

// Extract session ID from login response
        String sessionId = loginResponse.getCookie("JSESSIONID");

// Use the session ID in subsequent requests
        given()
                .cookie("JSESSIONID", sessionId)
                .when()
                .get("https://api.example.com/secure-endpoint")
                .then()
                .statusCode(200);

    }
}
