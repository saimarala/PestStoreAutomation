package api.test;

import api.endpoints.userEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static api.endpoints.userEndPoints.createUser;

public class DDTests {
    @Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostuser(String userID,String userName,String fname,String lname,String useremail,String pwd,String ph){
        User userPayload=new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);
        Response response=userEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    void testUserByName(String userName){
        Response response=userEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
