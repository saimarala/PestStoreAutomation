package ResponseTime;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseTimeExamples {

    void test(){
        //1. Extracting Response Time Directly
        //You can extract the response time in milliseconds or seconds using the time() method. The time() method can return the time in milliseconds by default, but you can also convert it to seconds.
        long responseTime = given()
                .when()
                .get("https://api.example.com/resource")
                .time();  // Extract response time in milliseconds

        System.out.println("Response time: " + responseTime + " ms");

        //If you want the response time in seconds, you can pass TimeUnit.SECONDS to the time() method.
//        long responseTimeInSeconds = given()
//                .when()
//                .get("https://api.example.com/resource")
//                .time(TimeUnit.SECONDS);  // Extract response time in seconds
//
//        System.out.println("Response time: " + responseTimeInSeconds + " seconds");

//2. Validating Response Time
//You can also assert that the response time is within a certain threshold, ensuring the API meets your performance expectations.
//
//Example: Validating Response Time is Less Than 2 Seconds
        given()
                .when()
                .get("https://api.example.com/resource")
                .then()
                .time(lessThan(2000L));  // Assert that the response time is less than 2000 ms (2 seconds)
        //3. Logging the Response Time
        //You can also log the response time along with the rest of the response data using Rest Assured's logging capabilities.
//        given()
//                .when()
//                .get("https://api.example.com/resource")
//                .then()
//                .log().time();  // Log the response time

//4. Combining Response Time with Other Validations
//You can combine response time validation with other response content validations (e.g., status code, body content) in the same test.
        given()
                .when()
                .get("https://api.example.com/resource")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(1001))
                .time(lessThan(1500L));  // Validate that response time is less than 1500 ms

    }

    //Method	Description
    //time()	Extracts the response time in milliseconds (default).
    //time(TimeUnit.SECONDS)	Extracts the response time in seconds (or any other unit).
    //log().time()	Logs the response time to the console.
    //.time(lessThan(2000L))	Validates that the response time is within a threshold.
}
