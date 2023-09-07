package automate;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SendingRequestPayloadInMultipleWays {

    ResponseSpecification customResponseSpecification;
    @BeforeClass
    public void execute()
    {


        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.getpostman.com").
                //setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                addHeader("X-API-Key","PMAK-64f888ba98f63100392e6ddd-bcc3b50b3e2ba7e70cb0adc9b0e714612d").
               setContentType("application/json;charset=utf-8").
                log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);

        customResponseSpecification = responseSpecBuilder.build();
    }

//    @Test
//    public void sendJsonObjectAsMap()
//    {
//
//        HashMap<String, Object> mainObject = new HashMap<String, Object>();
//
//        HashMap<String, String> nestedObject = new HashMap<String, String>();
//        nestedObject.put("name","myThirdWorkSpace");
//        nestedObject.put("type","personal");
//        nestedObject.put("description","Rest assured created this");
//
//        mainObject.put("workspace", nestedObject);
//
//        given().body(mainObject).
//                when().post("/workspaces").
//                then().
//                assertThat().statusCode(200);
//
//
//    }

    @Test
    public void sendJsonArrayAsList()
    {
        HashMap<String, String> object1 = new HashMap<String, String>();
        object1.put("id","5001");
        object1.put("type","None");

        HashMap<String, String> object2 = new HashMap<String, String>();
        object2.put("id","5002");
        object2.put("type","Glazed");

        //This list will be converted to json object internally using jackson library
        List<HashMap<String, String>> jsonList = new ArrayList<HashMap<String, String>>();
        jsonList.add(object1);
        jsonList.add(object2);

        given().body(jsonList).
                when().post("/post").
                then().spec(customResponseSpecification).
                assertThat().
                body("msg", equalTo("success"));


    }





}
