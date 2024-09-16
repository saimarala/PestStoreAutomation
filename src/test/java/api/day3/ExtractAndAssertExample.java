package api.day3;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ExtractAndAssertExample {

    @Test
    void extractAssert(){
        // Set the base URI
        RestAssured.baseURI = "https://api.example.com";

        // Send request and get response
        Response response = given()
                .when()
                .get("/user/123");

        // Extract JSON path
        JsonPath jsonPath = response.jsonPath();

        // Extract values
        String name = jsonPath.getString("name");
        String city = jsonPath.getString("address.city");

        // Assertions using JUnit or TestNG
        Assert.assertEquals("John Doe", name);
        Assert.assertEquals("New York", city);

        // Alternative assertions using Rest Assured's matchers
        response.then().body("name", equalTo("John Doe"));
        response.then().body("address.city", equalTo("New York"));
    }
}
