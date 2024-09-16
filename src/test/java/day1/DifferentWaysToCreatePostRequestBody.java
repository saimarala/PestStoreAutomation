package day1;



import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

/*
    Different ways to create POST request body
    1.Post request body using Hashmap
    2.Post request body creation using Org.JSON
    3.Post request body creation using POJO
    4.Post request body creation using external file data
 */
public class DifferentWaysToCreatePostRequestBody {

    //Post request hashmap
    @Test(priority = 1)
    void testPostHashMap(){
        HashMap<String,Object>data=new HashMap<>();
        data.put("name","sai");
        data.put("location","India");
        data.put("phone","1234");
        String coursesArr[]={"Java","JS"};
        data.put("courses",coursesArr);

        given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("")
        .then()
                .statusCode(201)
                .body("name",equalTo("sai"))
                .body("location",equalTo("India"))
                .body("courses[0]",equalTo("Java"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }

    @Test
    void testDelete(){
        given()
        .when()
                .delete("http://localhost:/students/4")
        .then()
                .statusCode(200);
    }
//Post request JSONObject
    @Test(priority = 2)
    void testPostJsonLibrary(){
        JSONObject data =new JSONObject();
        data.put("name","sai");
        data.put("location","India");
        data.put("phone","1234");
        String coursesArr[]={"Java","JS"};
        data.put("courses",coursesArr);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("")
                .then()
                .statusCode(201)
                .body("name",equalTo("sai"))
                .body("location",equalTo("India"))
                .body("courses[0]",equalTo("Java"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }


    //Post request POJO
    @Test(priority = 3)
    void testPostJsonPOJO(){
        PojoPostRequest data = new PojoPostRequest();
        data.setName("Sai");
        data.setLocation("India");
        data.setName("12345");
        String coursesArr[]={"Java","JS"};
        data.setCourses(coursesArr);
        //data.setCourses(new String[]{"Java", "JS"});

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("")
                .then()
                .statusCode(201)
                .body("name",equalTo("sai"))
                .body("location",equalTo("India"))
                .body("courses[0]",equalTo("Java"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }

    //Post request External Json File
    @Test(priority = 4)
    void testPostExternalJsonFile() throws FileNotFoundException {
        JSONTokener jt=new JSONTokener(new FileReader(new File(".\\body.json")));
        JSONObject data=new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("")
                .then()
                .statusCode(201)
                .body("name",equalTo("sai"))
                .body("location",equalTo("India"))
                .body("courses[0]",equalTo("Java"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }
}
