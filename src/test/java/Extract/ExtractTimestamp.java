package Extract;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class ExtractTimestamp {
    @Test
    void testExtractTimestamp(){
        // Get the response with a dynamic timestamp
        Response response = given()
                .baseUri("https://api.example.com")
                .when()
                .get("/events");

        // Extract the timestamp
        String timestamp = response.jsonPath().getString("eventTime");

        // Parse the timestamp into a LocalDateTime object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime eventTime = LocalDateTime.parse(timestamp, formatter);

        // Print the extracted event time
        System.out.println("Event Time: " + eventTime);
    }
}
