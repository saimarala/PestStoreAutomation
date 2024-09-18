package HTTPRequests;

import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HEADRequest {
//    //
//    A HEAD HTTP request is a type of request method in RESTful APIs that is similar to a GET request, but with one key difference: it only retrieves the headers of the response, without the response body. The purpose of a HEAD request is to obtain metadata about a resource, such as checking if it exists, its size, or its modification date, without downloading the entire content.
//
//    Key Characteristics of a HEAD Request:
//    No Response Body: Unlike a GET request, a HEAD request does not return the body of the resource. It only retrieves the HTTP headers.
//    Same Response Headers as GET: The headers in the response to a HEAD request are identical to those that would be returned if a GET request were made to the same URL.
//     Efficient: Since no body is returned, a HEAD request is faster and uses less bandwidth compared to a GET request, making it ideal for lightweight checks.
//    Common Use Cases for HEAD Requests:
//    Checking Resource Availability: A HEAD request can be used to check whether a resource exists (by looking at the HTTP status code).
//    Validating Cache: To check whether a cached version of a resource is still up to date by examining headers like Last-Modified or ETag.
//    Verifying Resource Size: By checking the Content-Length header, you can determine the size of the resource without downloading it.
//    Monitoring/Health Checks: It's often used for health checks on APIs, where you only want to verify that the service is up without fetching the entire data.
//    Pre-Validation for Download: Before downloading a large file, you might want to use a HEAD request to check the file size, modification date, or if you have proper access.
//    //

    @Test
    void test(){
        Response response = given()
                .baseUri("https://reqres.in")
                //   .param("page",2)
                .queryParam("page",2)

                .when()
                .head("/api/users");

        Reporter.log(response.body().asString(),true);

        given()
                .when()
                .head("/api/users/1")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .header("Content-Length", "455");
       //In this example:
        //
        //The head() method makes a HEAD request.
        //You can verify the status code and headers without dealing with the response body.
        //Summary of HTTP HEAD Request:
        //HEAD requests are similar to GET, but do not return the response body, only the headers.
        //Useful for lightweight operations like checking resource availability, size, or metadata.
        //More efficient than GET when you don't need the full content.



        //Key Characteristics of an OPTIONS Request:
        //Returns Allowed Methods: It provides information about which HTTP methods (e.g., GET, POST, PUT, DELETE, etc.) the resource supports.
        //Used for CORS (Cross-Origin Resource Sharing): In the context of web applications, the OPTIONS request is often used in Cross-Origin Resource Sharing (CORS) to check if the actual request is allowed from a different origin.
        //Preflight Requests: Browsers automatically send an OPTIONS request before certain types of requests (e.g., POST or PUT) to check if the server accepts them, especially in CORS scenarios.
        //No Response Body: Like a HEAD request, an OPTIONS request typically doesn’t return a response body but provides headers with the supported HTTP methods and other relevant information.

        Response response1 = given()
                .when()
                .options("/api/users")
                .then()
                .statusCode(204) // 204 No Content (typical for OPTIONS responses)
                .extract()
                .response();

// Extract the 'Allow' header to get the supported methods
        String allowedMethods = response1.getHeader("Allow");

        System.out.println("Allowed Methods: " + allowedMethods);

    }
}
