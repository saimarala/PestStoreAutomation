package Extract;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ExtractFromArray {

    @Test
    void testExtractFromArray(){
        // Get the list of users
        String response = given()
                .baseUri("https://reqres.in")
                .when()
                .get("/api/users?page=2")
                .asString();

        // Use JsonPath to find the ID of "Jane Doe"
        JsonPath jsonPath = new JsonPath(response);
        int janeDoeId = jsonPath.getInt("data.find { it.first_name== 'Michael' }.id");
        System.out.println("sai get : "+jsonPath.getString("data.find { it.first_name }.first_name")); 
        System.out.println("sai : "+jsonPath.getList("data.findAll { it.first_name }.first_name"));
        // Print the extracted ID
        System.out.println("Jane Doe's ID: " + janeDoeId);
  //If you need to fetch all matching elements, you can use .findAll:
        List<Integer> ff=jsonPath.getList("data.findAll { it.first_name == 'Michael' }.id");
        System.out.println(ff);
    System.out.println(jsonPath.getList("data.findAll { it.first_name == 'Michael' }.id"));
    
    //keyname= sell_count

//        List<Integer> sellCounts = jsonPath.getList("findAll { it.sell_count }.sell_count");
//
//        // Print the extracted values
//        System.out.println("Sell Counts: " + sellCounts);

        given().
                get("/users").
                then().
                body("users.size()", greaterThan(5)).
                body("users[0].name", equalTo("John Doe"));
        given()
                .when()
                .get("/store")
                .then()
                .body("..price", everyItem(greaterThan(10.0)));
    }
}
