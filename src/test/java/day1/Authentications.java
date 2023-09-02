package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Authentications {

    /*
    Basic
    Digest
    preempive
    Bearer token
    oauth 1.0,2.0
    API key
     */
    @Test(priority = 1)
    void testBasicAuthentication() {
        given()
                .auth().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }


    @Test(priority = 2)
    void tesDigestAuthentication() {
        given()
                .auth().digest("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    @Test(priority = 3)
    void tesPreemtiveAuthentication() {
        given()
                .auth().preemptive().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    @Test(priority = 4)
    void tesBearerTokenAuthentication() {

        String bearerToken = "";
        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();

    }


    @Test
    void testOAuth1Authentication() {
        given()
                .auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")//this is for oAuth1.0
                .when()
                .get("url")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    void testOAuth2Authentication() {
        given()
                .auth().oauth2("ssss@2242235gvdgf")//this is for oAuth2.0
                .when()
                .get("url")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    void testOAPIKeyAuthentication() {
//        given()
//                .queryParam("appid","ds124434343")//appid is API key
//                .when()
//                .get("url")
//                .then()
//                .statusCode(200).log().all();

        //Method 2
        given()
                .queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")//appid is API key
                .pathParam("mypath", "data/2.5/forecast/daily")
                .queryParam("q", "Delhi")
                .queryParam("units", "metric")
                .queryParam("cnt", "7")
                .when()
                .get("http://api.openweathermap.org/{mypath}")
                .then()
                .statusCode(200).log().all();

}
}
