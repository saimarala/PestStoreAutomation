package Extract;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RecursiveDescent {

    @Test
    void test(){
        Response response = given()
                .baseUri("https://reqres.in")
                .queryParam("page",2)
                .when()
                .get("/api/users")
                .then()
                .extract()
                .response();

// Extract all 'price' fields using recursive descent
        List<String> prices = response.jsonPath().getList("..email");

        System.out.println(prices);  // Output: [15, 12, 20, 19.99]

    }
}
