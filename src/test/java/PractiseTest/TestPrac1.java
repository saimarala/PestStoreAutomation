package PractiseTest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestPrac1 {

    @Test
    void testHandlep() {
        // Get the response with a dynamic list of users
        Response response = given()
                .baseUri("https://restcountries.com")
                .when()
                .get("/v2/all");


        List<Map<String, Object>> employees1 = response.jsonPath().getList("$");

        // Iterate through each employee
        for (Map<String, Object> employee : employees1) {
            System.out.println("ID: " + employee.get("altSpellings"));
        }
        List<String> sellCounts = response.jsonPath().getList("altSpellings");
        List<String> buyCounts = response.jsonPath().getList("capital");
        System.out.println(sellCounts);
        System.out.println(buyCounts);

    }
}
