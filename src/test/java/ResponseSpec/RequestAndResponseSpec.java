package ResponseSpec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class RequestAndResponseSpec {

    @Test
    void test(){
        // Request Specification
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.example.com")
                .addHeader("Authorization", "Bearer your_token")
                .build();

 //Response Specification
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();

// Using both specs
        given()
                .spec(requestSpec)
                .when()
                .get("/api/users")
                .then()
                .spec(responseSpec);  // Apply the response specification

    }
}
