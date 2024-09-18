package RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;

public class RequestSpecificationExample {

    //In Rest Assured, a Request Specification is a reusable object that allows you to define common request configurations (like headers, authentication, base URI, etc.) for multiple API requests. Instead of repeating the same configuration in every request, you can define it once using a RequestSpecification, and then reuse it across multiple test cases.
    //
    //Benefits of Using Request Specification:
    //Reusability: You can define common settings like base URI, base path, headers, query parameters, etc., and reuse them in multiple requests.
    //Cleaner Code: Reduces redundancy and keeps your code DRY (Don't Repeat Yourself).
    //Consistency: Ensures that requests sent from different test cases share the same configuration.

    @Test
    void test(){


        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.example.com")
                .setBasePath("/v1")
                .addHeader("Authorization", "Bearer your_token")
                .addHeader("Content-Type", "application/json")
                .build();
        //setBaseUri() defines the base URI for all requests.
        //setBasePath() defines the base path for all requests.
        //addHeader() is used to set default headers (e.g., Authorization and Content-Type).
        //Once the RequestSpecification is created, you can apply it to any request by chaining the spec() method.
        given()
                .spec(requestSpec)
                .when()
                .get("/users")
                .then()
                .statusCode(200);

       //spec(requestSpec) applies the previously defined specification to the request,
        // automatically using the base URI, base path, headers, etc.

        //Setting Query Parameters and Path Parameters in the Specification
        //You can also set query parameters and path parameters in the request specification to
        // avoid repeating them in every request.
        RequestSpecification requestSpecWithParams = new RequestSpecBuilder()
                .setBaseUri("https://api.example.com")
                .addQueryParam("status", "active")
                .build();

        given()
                .spec(requestSpecWithParams)
                .when()
                .get("/users")
                .then()
                .statusCode(200);
        //In this case, all requests using this specification will
        // automatically include the query parameter status=active.
        RequestSpecification requestSpecWithPathParams = new RequestSpecBuilder()
                .setBaseUri("https://api.example.com")
                .addPathParam("userId", 123)
                .build();

        given()
                .spec(requestSpecWithPathParams)
                .when()
                .get("/users/{userId}")
                .then()
                .statusCode(200);


        //Combining RequestSpecification with Additional Request Data
        //You can always override or add extra details (like query parameters, path parameters, headers, etc.) to a
        // specific request even if you are using a RequestSpecification.
        given()
                .spec(requestSpec)
                .header("Custom-Header", "CustomValue")
                .when()
                .get("/users")
                .then()
                .statusCode(200);
      //In this case, the Custom-Header will be added only for this specific request, while
        // the common headers from the request specification will still be applied.
        RequestSpecification authSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.example.com")
                .setBasePath("/v1")
                .setAuth(oauth2("your_access_token"))  // OAuth2 Authentication
                .build();

        given()
                .spec(authSpec)
                .when()
                .get("/user/profile")
                .then()
                .statusCode(200);
  //Default Request Specification in Rest Assured
        //You can also set a global request specification that will apply to all requests by default,
        // without needing to specify it each time.
        RestAssured.requestSpecification = requestSpec;

        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200);



    }
    //Conclusion
    //RequestSpecification allows you to define common configurations for multiple requests in Rest Assured.
    //It improves code reusability and readability by centralizing common settings (base URI, headers, authentication, etc.).
    //You can define query parameters, path parameters, headers, and more in the request specification.
    //You can set global request specifications to automatically apply to all API requests in your tests.
}
