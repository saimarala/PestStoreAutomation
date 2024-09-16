package api.day3;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;

public class ResponseSpecExample {
    // Create a reusable response specification
    static ResponseSpecification responseSpec;

    static {
        // Initialize the ResponseSpecBuilder
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)  // Expect status code 200
                .expectHeader("Content-Type", "application/json")  // Expect JSON content type
                .expectBody("status", equalTo("success"))  // Expect 'status' field in the body
                .build();  // Build the specification
    }
}
