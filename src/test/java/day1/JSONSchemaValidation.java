package day1;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JSONSchemaValidation {

    @Test
    void jsonSchemaValidation(){
        given()
        .when()
                .get("https://reqres.in/api/users?page=2")
        .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONSchema.json"));

    }
}
