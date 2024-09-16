package api.day3;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ExtendedResponseSpecExample {
    static ResponseSpecification responseSpec;

    static {
        // Define expectations in the ResponseSpecBuilder
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)  // Expect HTTP 200 status
                .expectHeader("Content-Type", "application/json")  // Expect JSON Content-Type
                .expectHeader("Cache-Control", "no-cache")  // Expect Cache-Control header
                .expectBody("status", equalTo("success"))  // Validate 'status' field in JSON body
                .expectBody("data.id", notNullValue())  // Validate that 'data.id' is not null
                .build();  // Build the specification
    }
}
