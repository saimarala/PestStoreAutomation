package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class LoggingDemo {


    @Test
    void testLogs(){
        given()
        .when()
                .get("https://reqres.in/api/users?page=2")
        .then()
               // .log().headers();//only headers
                //.log().body();//only body
               // .log().cookies();//only cookies
                .log().all();

    }
}
