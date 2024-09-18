package JsonPath;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JsonPathTest {

    void test(){
        //1. Extracting Data Using JSONPath
        //You can extract specific data using a JSONPath expression in Rest Assured.
        //Example: Extract the Color of the Bicycle
        Response response = given()
                .when()
                .get("/store")
                .then()
                .extract()
                .response();

        String bicycleColor = response.jsonPath().getString("store.bicycle.color");
        System.out.println(bicycleColor);  // Output: "red"
    //Here, jsonPath().getString("store.bicycle.color") uses a JSONPath expression to
        // extract the color field of the bicycle object.

        //Example: Extract All Book Prices
        List<Integer> bookPrices = response.jsonPath().getList("store.book.price");
        System.out.println(bookPrices);  // Output: [15, 12, 20]
        //This extracts the price of all books from the book array.

        //2. Using JSONPath with Assertions
        //You can use JSONPath expressions in Rest Assured for validation.
        // For example, if you want to validate that the price of a book is greater than a certain value,
        // you can use body() with JSONPath.

        //Example: Validate the Price of the First Book
        given()
                .when()
                .get("/store")
                .then()
                .body("store.book[0].price", equalTo(15));
       //This ensures that the price of the first book is 15.

        //3. Using Filters in JSONPath (find, findAll)
        //Filters are useful when you want to extract or validate data based on conditions.
        // The ?() syntax is used for filtering.
        //
        //Example: Find All Books with Price Greater than 12
        List<Map<String, Object>> expensiveBooks = response.jsonPath()
                .getList("store.book.findAll { it.price > 12 }");

        System.out.println(expensiveBooks);
       /// This will return all books with a price greater than 12.
      //Example: Extract the Author of the Book with Price Greater than 12
        List<String> authors = response.jsonPath()
                .getList("store.book.findAll { it.price > 12 }.author");

        System.out.println(authors);  // Output: ["J.K. Rowling", "Yuval Noah Harari"]
   //Here, .author extracts only the author field from the filtered books.
        //4. Using Wildcards (*)
        //The wildcard * is used to select all elements at a certain level in the JSON structure.
        //
        //Example: Extract All Authors
        List<String> authors1 = response.jsonPath().getList("store.book[*].author");
        System.out.println(authors1);  // Output: ["J.K. Rowling", "George Orwell", "Yuval Noah Harari"]
        //This extracts the author field from all books in the array.


        //5. Extracting Values with Recursive Descent (..)
        //The recursive descent operator .. allows you to search for elements at any level in the JSON structure.
        //
        //Example: Extract All Prices in the Response
        List<Float> allPrices = response.jsonPath().getList("..price");
        System.out.println(allPrices);  // Output: [15, 12, 20, 19.99]
      //This returns all price values, regardless of where they are located in the JSON.

        //6. Handling Arrays with Index
        //You can extract elements from an array using index notation.
        // For example, if you want to extract the second book in the book array,
        // you can use [1] (as arrays are 0-indexed).
        //
        //Example: Extract the Second Book's Category
        String secondBookCategory = response.jsonPath().getString("store.book[1].category");
        System.out.println(secondBookCategory);  // Output: "fiction"
       //7. Checking Array Lengths
        //You can also check the length of arrays using JSONPath expressions.
        //
        //Example: Validate the Number of Books in the Array
        given()
                .when()
                .get("/store")
                .then()
                .body("store.book.size()", equalTo(3));
        //This asserts that there are three books in the book array.
        //Summary of Common JSONPath Expressions in Rest Assured
        //JSONPath Expression	Description
        //$.store.book[0].author	Extract the author of the first book.
        //$.store.book[*].price	Extract prices of all books.
        //$.store.book[?(@.price > 12)]	Filter books with a price greater than 12.
        //$.store.book.length()	Get the number of books in the array.
        //$.store.bicycle.color	Extract the color of the bicycle.
        //$..price	Extract all prices from anywhere in the JSON.


    }
}
