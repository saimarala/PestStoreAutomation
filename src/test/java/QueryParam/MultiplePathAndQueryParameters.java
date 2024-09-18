package QueryParam;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MultiplePathAndQueryParameters {

    //https://reqres.in/api/users?page=2&id=5
    @Test
    void testQueryAndParameters(){
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("myPath1","api");
        pathParams.put("myPath2","users");
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page",2);
        queryParams.put("id",5);
        given()
//                .pathParam("mypath1","api")
//                .pathParam("mypath2","users")//path parameters
//                .queryParam("page",2)
//                .queryParam("id",5)
                .pathParams(pathParams)
                .queryParams(queryParams)
        .when()
                .get("https://reqres.in/{myPath1}/{myPath2}")
        .then()
                .statusCode(200).log().all();

    }
}
