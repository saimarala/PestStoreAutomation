package POSTRequestBody;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;


import static io.restassured.RestAssured.given;

public class POSTReq {

    void test(){
        //1. Using a JSON String Directly
        //You can pass a JSON string as the body of the request.
        // This is a simple and quick method for small or predefined JSON structures.

        String requestBody = "{ \"name\": \"John\", \"age\": 30, \"email\": \"john.doe@example.com\" }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)  // Pass the raw JSON string
                .when()
                .post("https://api.example.com/users")
                .then()
                .statusCode(201);

        //2. Using a Java Map (Serialized to JSON Automatically)
        //Rest Assured can automatically serialize a Java Map object into a JSON request body.
        Map<String, Object> requestBody1 = new HashMap<>();
        requestBody1.put("name", "John");
        requestBody1.put("age", 30);
        requestBody1.put("email", "john.doe@example.com");

        given()
                .header("Content-Type", "application/json")
                .body(requestBody1)  // Rest Assured automatically serializes the map to JSON
                .when()
                .post("https://api.example.com/users")
                .then()
                .statusCode(201);
    //3. Using a Java Object (POJO)
        //You can create a plain old Java object (POJO), and Rest Assured will serialize it into JSON automatically.
        // This method is great for structured data.
         class User {
            private String name;
            private int age;
            private String email;

            // Constructor, Getters, Setters
            public User(String name, int age, String email) {
                this.name = name;
                this.age = age;
                this.email = email;
            }
        }

        User user = new User("John", 30, "john.doe@example.com");

        given()
                .header("Content-Type", "application/json")
                .body(user)  // Rest Assured serializes the User object to JSON
                .when()
                .post("https://api.example.com/users")
                .then()
                .statusCode(201);

   //4. Using External JSON Files
        //If you have a large or predefined JSON request body, you can load it from an external file.
        File jsonFile = new File("path/to/user.json");

        given()
                .header("Content-Type", "application/json")
                .body(jsonFile)  // Pass the file directly
                .when()
                .post("https://api.example.com/users")
                .then()
                .statusCode(201);
    //5. Using Form Parameters (x-www-form-urlencoded)
        //If the API expects data as form parameters (often used in web forms),
        // you can send the data using .formParam().
        given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("name", "John")
                .formParam("age", 30)
                .formParam("email", "john.doe@example.com")
                .when()
                .post("https://api.example.com/users")
                .then()
                .statusCode(201);
   //6. Using XML String
        //For APIs that expect XML data in the request body, you can pass an XML string.
        String requestBody2 = "<user><name>John</name><age>30</age><email>john.doe@example.com</email></user>";

        given()
                .header("Content-Type", "application/xml")
                .body(requestBody2)  // Pass the raw XML string
                .when()
                .post("https://api.example.com/users")
                .then()
                .statusCode(201);
   //7. Using XML with a Java Object (POJO)
        //You can also serialize a Java object into XML
        // using annotations like @XmlRootElement when working with XML requests.

    //    8. Using Multipart Form Data
    //    For file uploads or multipart requests, you can send both
    //    files and form data using the multiPart() method.
        File file = new File("path/to/file.txt");

        given()
                .multiPart("file", file)
                .formParam("description", "File upload example")
                .when()
                .post("https://api.example.com/upload")
                .then()
                .statusCode(200);
    //9. Using JSONObject or JSONArray (from org.json library)
        //You can also construct JSON bodies using JSONObject or JSONArray from the org.json library.


        JSONObject requestBody3 = new JSONObject();
        requestBody3.put("name", "John");
        requestBody3.put("age", 30);
        requestBody3.put("email", "john.doe@example.com");

        given()
                .header("Content-Type", "application/json")
                .body(requestBody3.toString())  // Convert JSONObject to string
                .when()
                .post("https://api.example.com/users")
                .then()
                .statusCode(201);


    }
}
