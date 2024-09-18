package Log;

import static io.restassured.RestAssured.given;

public class LogExamples {

    void test(){
        //1. Logging the Entire Request
        //To log the entire request (headers, parameters, body, etc.), you can use log().all() before sending
        // the request.

        //This will log all parts of the request,
        // including headers, query parameters, path parameters, and the request body (if applicable).
        given()
                .log().all()  // Log the entire request
                .when()
                .get("https://api.example.com/users")
                .then()
                .statusCode(200);
//2. Logging the Entire Response
//To log the entire response (headers, status code, body, etc.),
// you can use log().all() after sending the request.
        given()
                .when()
                .get("https://api.example.com/users")
                .then()
                .log().all()  // Log the entire response
                .statusCode(200);
//3. Logging Specific Parts of the Request or Response
//a. Logging Only Headers:
//To log only the headers (either request or response headers), use log().headers(
        //Request Headers:
        given()
                .log().headers()  // Log only request headers
                .when()
                .get("https://api.example.com/users")
                .then()
                .statusCode(200);
//Response Headers:
        given()
                .when()
                .get("https://api.example.com/users")
                .then()
                .log().headers()  // Log only response headers
                .statusCode(200);
//b. Logging Only Body:
//To log only the body (either request or response body), use log().body().
//
//Request Body:
        given()
                .body("{ \"name\": \"John\" }")
                .log().body()  // Log only request body
                .when()
                .post("https://api.example.com/users")
                .then()
                .statusCode(201);
//Response Body:
        given()
                .when()
                .get("https://api.example.com/users")
                .then()
                .log().body()  // Log only response body
                .statusCode(200);
        //c. Logging Only Parameters:
        //To log only the parameters, use log().params().
        //
        //Request Parameters:
        //java
        given()
                .queryParam("id", 1)
                .log().params()  // Log only request parameters
                .when()
                .get("https://api.example.com/users")
                .then()
                .statusCode(200);
    //d. Logging Only Status:
        //To log only the status code of the response, use log().status().
        given()
                .when()
                .get("https://api.example.com/users")
                .then()
                .log().status()  // Log only the response status code
                .statusCode(200);
      //4. Logging if Validation Fails
        //You can set Rest Assured to log the request or response only if the validation fails using log().ifValidationFails().
        // This is useful to avoid excessive logging and only log details in case of a failure.
        given()
                .when()
                .get("https://api.example.com/users")
                .then()
                .log().ifValidationFails()  // Log only if the test fails
                .statusCode(200);
//5. Logging Request and Response Separately
//You can log both the request and response separately by combining log() before and after the request.
        given()
                .log().all()  // Log the entire request
                .when()
                .get("https://api.example.com/users")
                .then()
                .log().all()  // Log the entire response
                .statusCode(200);






    }
    //Method	Description
    //log().all()	Logs everything (headers, body, status, etc.).
    //log().headers()	Logs only the headers.
    //log().body()	Logs only the body.
    //log().params()	Logs only the parameters.
    //log().status()	Logs only the status code.
    //log().ifValidationFails()	Logs details only if the validation fails
}
