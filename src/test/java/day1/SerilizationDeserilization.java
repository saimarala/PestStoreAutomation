package day1;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class SerilizationDeserilization {


    @Test
    void convertPojo2Json() throws JsonProcessingException {
        PojoPostRequest data = new PojoPostRequest();
        data.setName("sai");
        data.setName("India");
        data.setPhone("1233");
        String coursesArr[]={"Java","JS"};
        data.setCourses(coursesArr);
        //convert java object to json object(serilization)
        ObjectMapper obj = new ObjectMapper();
       String jsonData= obj.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        Reporter.log(jsonData,true);
    }


    @Test
    void convertJson2Pojo() throws JsonProcessingException {
        String jsondata ="{\n" +
                "  \"name\" : \"India\",\n" +
                "  \"location\" : null,\n" +
                "  \"phone\" : \"1233\",\n" +
                "  \"courses\" : [ \"Java\", \"JS\" ]\n" +
                "}";
        //convert json  object to pojo object(serilization)
        ObjectMapper obj = new ObjectMapper();
        PojoPostRequest d=obj.readValue(jsondata,PojoPostRequest.class);
        Reporter.log(d.getName(),true);
    }
}
