package day2;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetUser {
    //int id =0;

    @Test
    void testGetUser(ITestContext context){
       // int id=(Integer)context.getAttribute("user_id");//this should come from create user
        int id=(Integer)context.getSuite().getAttribute("user_id");
        String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
        given()
                .headers("Authorization","Bearer "+bearerToken)
                .pathParam("id",id)
        .when()
                .get("https://gorest.co.in/public/v2/users/{id}")
        .then()
                .statusCode(200).log().all();

    }
}
