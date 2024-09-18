package ResponseSpec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ResponseSpecificationExamples {

    @Test
    void test(){


        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)  // Expect status code to be 200
                .expectContentType("application/json")  // Expect content type to be JSON
                .expectHeader("Cache-Control", "no-cache")  // Expect specific headers
                .expectBody("success", equalTo(true))  // Expect specific body field
                .build();


        given()
                .when()
                .get("/api/users")
                .then()
                .spec(responseSpec);  // Apply the reusable response spec
       //Adding Additional Validations
        //You can always add extra validations specific to an individual request,
        // even if you're using a ResponseSpecification.
        given()
                .when()
                .get("/api/users")
                .then()
                .spec(responseSpec)  // Apply the predefined spec
                .body("data.size()", greaterThan(0));  // Additional body check

//Global Response Specification
//Similar to a request specification, you can set a global
// response specification so that it applies to all requests automatically.
        RestAssured.responseSpecification = responseSpec;

        given()
                .when()
                .get("/api/users")
                .then()
                .statusCode(200);  // The response specification is automatically applied

        //Example of a Response Specification for Common API Checks
        //Here’s an example of a ResponseSpecification for an API that often returns JSON data,
        // a status code of 200, and includes certain headers.
        ResponseSpecification commonResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectHeader("Server", "Apache")
                .expectBody("message", containsString("success"))
                .build();

    //Now, you can apply this commonResponseSpec to multiple test cases.
        given()
                .when()
                .get("/api/orders")
                .then()
                .spec(commonResponseSpec);

        given()
                .when()
                .get("/api/products")
                .then()
                .spec(commonResponseSpec);



    }
}
