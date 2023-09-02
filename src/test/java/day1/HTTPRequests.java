package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class HTTPRequests {

    int id = 0;

    @Test(priority = 0)
    void getUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();

    }

    @Test(priority = 1)
    void createUser() {
        HashMap hm = new HashMap();
        hm.put("name", "AT");
        hm.put("job", "QA");
        id = given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
//                .then()
//                .statusCode(201).log().all();
    }

    @Test(priority = 2, dependsOnMethods = {"createUser"})
    void updateUser() {
        HashMap hm = new HashMap();
        hm.put("name", "AT");
        hm.put("job", "SDET");

        given()
                .contentType("application/json")
                .body(hm)
                .when()
                .put("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(200)
                .log().all();


    }

    @Test(priority = 3)
    void delete() {
        given()
                .when()
                .delete("https://reqres.in/api/users/\"+id")
                .then()
                .statusCode(204);
    }
}
