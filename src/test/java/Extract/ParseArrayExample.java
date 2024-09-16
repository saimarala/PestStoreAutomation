package Extract;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class ParseArrayExample {

    void testParseArray(){
        Response response = RestAssured
                .given()
                .baseUri("https://api.example.com")
                .when()
                .get("/user/123");

        JsonPath jsonPath = response.jsonPath();

        // Extract the list of phone numbers
        List<String> phoneNumbers = jsonPath.getList("phoneNumbers.number");

        // Print all phone numbers
        for (String number : phoneNumbers) {
            System.out.println("Phone Number: " + number);
        }
    }
}
