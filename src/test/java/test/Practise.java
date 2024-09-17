package test;

import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Practise {
    @Test
    void test(){
       Response response = given()
                .baseUri("https://reqres.in")
               .queryParam("page",2)
                .when()
                .get("/api/users");
     //  response.then().body("name",equalTo("cerulean"));
   //     Reporter.log(response.then().extract().asString(),true);
//        response.jsonPath().getString("phoneNumbers.find { it.type == 'home' }.number");  // Extract from array
//       String t= response.jsonPath().getString("data.find { it.id == '7' }.email");  // Extract from array

    //    Reporter.log(response.jsonPath().getString("data[2].email"),true);
//        List<Map<String, String>> phoneNumbers = response.jsonPath().getList("data");
        List<Map<String, Object>> engineeringEmployees = response.jsonPath()
                .getList("data");

        System.out.println(engineeringEmployees);
        for (Map ss:engineeringEmployees){
            if (ss.containsValue(7)){
                ss.entrySet().forEach(System.out::println);
                           }
        }


    }
}
