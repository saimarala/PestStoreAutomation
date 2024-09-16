package ResponseTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ResponseTime {
//Rest Assured allows you to validate response times, which is crucial for performance and load testing.
    void test(){
        given().
                get("/users").
                then().
                time(lessThan(2000L));  // Response time should be less than 2 seconds
    }
}
