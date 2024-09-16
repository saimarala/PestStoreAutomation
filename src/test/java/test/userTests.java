package test;

import api.endpoints.userEndPoints;
import payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class    userTests {
    Faker faker;
    User userPayload;
    public Logger logger;

    @BeforeClass
    void setupData() {
        faker = new Faker();
        userPayload = new User();
        faker.idNumber();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    void testPostUser() {
        logger.info("Creating user");
        Response response = userEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        Reporter.log("response body : " +response.body().asString(),true);

        logger.info("Created user");
    }

    @Test(priority = 2)
    void testGetUserByName() {
        logger.info("Reading user info");
        Response response = userEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User info displayed");
    }

    @Test(priority = 3)
    void testUpdateUserByName() {
        //update data using payload
        logger.info("Updating user");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response = userEndPoints.updateUser(userPayload, this.userPayload.getUsername());
        response.then().log().body().statusCode(200);
        Assert.assertEquals(response.getStatusCode(), 200);
        //Checking data for update
        Response response1 = userEndPoints.readUser(this.userPayload.getUsername());
        response1.then().log().all();
        Assert.assertEquals(response1.getStatusCode(), 200);
        logger.info("Updated user");

    }
    @Test(priority = 4)
    void testDeleteUserByName(){
        logger.info("Deleting user");
        Response response=userEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200 );
        logger.info("Deleted user");
    }

}
