package api.endpoints;

import payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class userEndPoints2 {

    static ResourceBundle getURl(){
       return  ResourceBundle.getBundle("router");
    }
    public static Response createUser(User payload) {
        Response response = given()
                .contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
                .when()
                .post(getURl().getString("post_url"));
        return response;
    }

    public static Response readUser(String username) {
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(getURl().getString("get_url"));
        return response;
    }

    public static Response updateUser(User payload, String username) {
        Response response = given()
                .contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username",username).body(payload)
                .when()
                .put(getURl().getString("update_url"));
        return response;
    }
    public static Response deleteUser(String username) {
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(getURl().getString("delete_url"));
        return response;
    }
}
