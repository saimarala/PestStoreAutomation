package day1;

import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class CookiesDemo {


    @Test(priority = 1, enabled = false)
    void testCookies() {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .cookie("ACE", "").log().all();


    }


    @Test(priority = 2)
    void getCookiesInfo() {
        Response res = given()
                .when()
                .get("https://www.google.com/");
        //get cookie info
        String cookie_value = res.getCookie("AEC");
        Reporter.log("value of cookie is : " + cookie_value, true);
        //get all cookie info
        Map<String, String> cookies_values = res.getCookies();
        Reporter.log(cookies_values.keySet() + ":", true);

        for (String k : cookies_values.keySet()) {
            Reporter.log(k + " : " + res.getCookie(k), true);
        }

    }
}
