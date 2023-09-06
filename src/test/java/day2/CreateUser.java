package day2;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateUser {

   @Test
    void testCreateUser(ITestContext context){
       Faker faker=new Faker();
       JSONObject data=new JSONObject();
       data.put("name",faker.name().fullName());
       data.put("gender","male");
       data.put("email",faker.internet().emailAddress());
       data.put("status","inactive");
       String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";

       int id=given()
               .headers("Authorization","Bearer "+bearerToken)
               .contentType("application/json")
               .body(data.toString())
       .when()
               .post("https://gorest.co.in/public/v2/users")
       .jsonPath().getInt("id");

       Reporter.log("Generated id is : "+id);
       context.setAttribute("user_id",id);

    }
}
