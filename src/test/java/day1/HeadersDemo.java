package day1;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class HeadersDemo {
    @Test(priority = 1)
    void testHeaders() {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding","gzip")
                .and()
                .header("Server","gws");

    }


    @Test(priority = 2)
    void getHeaders() {
      Response res= given()
                .when()
                .get("https://www.google.com/");

      //get single header info
        String header_value=res.getHeader("Content-Type");
        Reporter.log("value od contet header is :"+header_value,true);
        //get all headers
      Headers header_values=res.getHeaders();
      for (Header hd:header_values){
          Reporter.log(hd.getName()+":"+hd.getValue(),true);
      }

    }
}
