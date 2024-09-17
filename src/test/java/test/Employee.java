package test;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Employee {
    //1. Extract the Array of Employees from a Specific Department
    //If you want to extract the employees array from a specific department (e.g., "Engineering"),
    // you can use JSONPath to navigate through the object structure.
    @Test
    void test(){
        Response response = given()
                .when()
                .get("https://api.example.com/company")
                .then()
                .statusCode(200)
                .extract()
                .response();

// Extract the 'employees' array from the 'Engineering' department
        List<Map<String, Object>> engineeringEmployees = response.jsonPath()
                .getList("company.departments.find { it.name == 'Engineering' }.employees");

        System.out.println(engineeringEmployees);  // Prints the list of employees in the Engineering department
        //company.departments navigates to the list of departments.
        //.find { it.name == 'Engineering' } filters the list to find the department where name equals "Engineering".
        //.employees accesses the employees array inside the selected department.

    }

    void nestedArray(){
        //To extract a specific field (e.g., names of the employees)
        // from the nested employees array, you can extend the JSONPath
        // Extract the 'name' field of employees from the 'Engineering' department
        Response response = given()
                .when()
                .get("https://api.example.com/company")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<String> employeeNames = response.jsonPath()
                .getList("company.departments.find { it.name == 'Engineering' }.employees.name");

        System.out.println(employeeNames);  // Prints the names of the employees: [John, Jane]
   //3. Extract All Employees Across All Departments
        //If you want to extract the employees array from all departments,
        // you can loop through the array of departments and collect the employee data.
        // Extract employees from all departments
        List<List<Map<String, Object>>> allEmployees = response.jsonPath().getList("company.departments.employees");

        System.out.println(allEmployees);  // Prints the list of employees from all departments
//Here, company.departments.employees extracts the employees array from each department.
// This returns a list of lists, where each inner list contains the employees of a department.


        // Extract employees with id > 1
        List<Map<String, Object>> employeesWithIdGreaterThan1 = response.jsonPath()
                .getList("company.departments.employees.findAll { it.id > 1 }");

        System.out.println(employeesWithIdGreaterThan1);  // Prints employees with id > 1: [{id=2, name=Jane}, {id=3, name=Mike}]

        //4. Extract Employees Based on a Condition
        //You can filter out employees based on a specific condition. For example,
        // you can extract employees whose id is greater than 1.

   //     5. Extract a Specific Employee Based on Department
   //     You can extract a single employee from a specific department using both filters and
        //     index notation. For instance, to extract the first employee in the "HR" department:
        // Extract the first employee from the 'HR' department
        Map<String, Object> firstHREmployee = response.jsonPath()
                .getMap("company.departments.find { it.name == 'HR' }.employees[0]");

        System.out.println(firstHREmployee);  // Prints: {id=3, name=Mike}
//Here, employees[0] accesses the first employee in the "HR" department.
        //6. Extract All Employee Names Across All Departments
        //If you want to extract the names of all employees across departments,
        // you can flatten the nested arrays and collect only the name fields.
        // Extract names of all employees across all departments
        List<String> allEmployeeNames = response.jsonPath()
                .getList("company.departments.employees.name");

        System.out.println(allEmployeeNames);  // Prints: [John, Jane, Mike]





    }

}
