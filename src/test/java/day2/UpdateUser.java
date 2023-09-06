package day2;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {
     @Test
    void testUpdateUser(ITestContext context){
         //int id=(Integer)context.getAttribute("user_id");
         int id=(Integer)context.getSuite().getAttribute("user_id");
        Faker faker=new Faker();
        JSONObject data=new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","active");
        String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";

        given()
                .headers("Authorization","Bearer "+bearerToken)
                .contentType("application/json")
                .pathParam("id",id)
                .body(data.toString())
        .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
        .then().statusCode(200).log().all();

        Reporter.log("Generated id is : "+id);
    }
}
