package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class PathAndQueryParameters {

    //https://reqres.in/api/users?page=2&id=5
    @Test
    void testQueryAndParameters(){
        given()
                .pathParam("mypath1","api")
                .pathParam("mypath2","users")//path parameters
                .queryParam("page",2)
                .queryParam("id",5)
        .when()
                .get("https://reqres.in/{mypath1}/{mypath2}")
        .then()
                .statusCode(200).log().all();

    }
}
