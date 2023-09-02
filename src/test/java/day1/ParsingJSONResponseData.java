package day1;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData {

    @Test(priority = 1)
    void testJsonResponse(){
        //Approach 1
//        given()
//                .contentType("ContentType/json")
//        .when()
//                .get("https://reqres.in/api/users?page=2")
//        .then()
//                .statusCode(200)
//                .header("Content-Type","application/json; charset=utf-8")
//                .body("data[1].first_name",equalTo("Lindsay"));

        //Approach 2

       Response res = given()
                .contentType("ContentType/json")
                .when()
                .get("https://reqres.in/api/users?page=2");
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
        Assert.assertEquals(res.jsonPath().get("data[1].first_name").toString(),"Lindsay");

    }


    @Test(priority = 2)
    void testJsonResponseBody(){
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page=2");
//        Assert.assertEquals(res.getStatusCode(),200);
//        Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
//        Assert.assertEquals(res.jsonPath().get("data[1].first_name").toString(),"Lindsay");

        JSONObject jo = new JSONObject(res.asString());//converting response to json object
        boolean status=false;
        for (int i=0;i<jo.getJSONArray("data").length();i++){
           String fName= jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
            //Reporter.log("first_name :"+fName,true);
            if (fName.equals("Lindsay")){
                status=true;
                break;
            }

        }
        Assert.assertEquals(status,true);

        int total=0;
        for (int i=0;i<jo.getJSONArray("data").length();i++){
            String id=jo.getJSONArray("data").getJSONObject(i).get("id").toString();
            total=total+Integer.parseInt(id);
        }

        Reporter.log("total id :",total,true);
        Assert.assertEquals(total,57);

    }
}
