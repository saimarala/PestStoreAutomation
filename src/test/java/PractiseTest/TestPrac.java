package PractiseTest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestPrac {

    @Test
    void testHandlep() {
        // Get the response with a dynamic list of users
//        Response response = given()
//                .baseUri("https://reqres.in")
//                .when()
//                .get("/api/users?page=2");


        Response response = given()
                .baseUri("https://reqres.in")
                .when()
                .get("/api/users?page=2").then().extract().response();

//  


//        Reporter.log(response.then().extract().asString(),true);

        // Extract the size of the 'users' array
//        int userCount = response.jsonPath().getList("data").size();
//     
//        // Print the user count
//        System.out.println("Total Users: " + userCount);
        // Extract list of employees

        List<Map<String, Object>> employees = response.jsonPath().getList("data");
        int val = 0;
        // Iterate through each employee
        for (Map<String, Object> employee : employees) {
//            System.out.println("ID: " + employee.get("id"));
//            System.out.println("Name: " + employee.get("email"));
//            System.out.println("Role: " + employee.get("first_name"));
//            System.out.println("------------");

            val += Integer.parseInt(employee.get("id").toString());
        }

        System.out.println(val);

       int val1=0;
        // Extract specific data using loop
        List<Integer> employeeNames = response.jsonPath().getList("data.id");
        for (int name : employeeNames) {
          //  System.out.println("Employee Name: " + name);
            val1 += name;         
        }
        System.out.println("list  " + val1);


        // Extract individual values
        int page = response.jsonPath().getInt("page");
        int perPage = response.jsonPath().getInt("per_page");
        int total = response.jsonPath().getInt("total");
        int totalPages = response.jsonPath().getInt("total_pages");

        //    List<Integer>dd=response.jsonPath().getList("total_pages");

        // Print extracted values
        System.out.println("Page: " + page);
        System.out.println("Per Page: " + perPage);
        System.out.println("Total: " + total);
        System.out.println("Total Pages: " + totalPages);


    }
}
