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
                setBaseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").
                //addHeader("X-API-Key","PMAK-64f888ba98f63100392e6ddd-bcc3b50b3e2ba7e70cb0adc9b0e714612d").
                addHeader("x-mock-match-request-body","true").
                //setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
               //setContentType(ContentType.JSON).
                        setContentType("application/json;charset=utf-8").
                log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);

        customResponseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void sendJsonObjectAsMap()
    {

        HashMap<String, Object> mainObject = new HashMap<String, Object>();

        HashMap<String, String> nestedObject = new HashMap<String, String>();
        nestedObject.put("name","myThirdWorkSpace");
        nestedObject.put("type","personal");
        nestedObject.put("description","Rest assured created this");

        mainObject.put("workspace", nestedObject);

        given().body(mainObject).
                when().post("/workspaces").
                then().
                assertThat().body("type", equalTo("personal"));

    }

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

        given().
                body(jsonList).
                when().post("/post").
                then().spec(customResponseSpecification).
                assertThat().
                body("msg", equalTo("success"));

    }

    @Test
    public void sendComplexJsonBodyUsingMapAndList()
    {
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(1);
        idList.add(2);

        HashMap<String, Object> batter2 = new HashMap<String, Object>();
        batter2.put("id",idList);
        batter2.put("type","Chocolate");

        HashMap<String, Object> batter1 = new HashMap<String, Object>();
        batter1.put("id","1001");
        batter1.put("type","Regular");

        List<HashMap<String, Object>> batterList = new ArrayList<HashMap<String, Object>>();
        batterList.add(batter1);
        batterList.add(batter2);

        HashMap<String, List<HashMap<String, Object>>> battersObject = new HashMap<String, List<HashMap<String, Object>>>();
        battersObject.put("batter", batterList);

        List<String> typeList = new ArrayList<String>();
        typeList.add("test1");
        typeList.add("test2");

        HashMap<String, Object> topping2 = new HashMap<String, Object>();
        topping2.put("id","5002");
        topping2.put("type", typeList);

        HashMap<String, Object> topping1 = new HashMap<String, Object>();
        topping1.put("id","5001");
        topping1.put("type","None");

        List<HashMap<String,Object>> toppingList = new ArrayList<HashMap<String, Object>>();
        toppingList.add(topping1);
        toppingList.add(topping2);

        HashMap<String, Object> mainObject = new HashMap<String, Object>();
        mainObject.put("id","0001");
        mainObject.put("type","donut");
        mainObject.put("name","Cake");
        mainObject.put("ppu",0.55);
        mainObject.put("batters", battersObject);
        mainObject.put("topping", toppingList);

        given().body(mainObject).
                when().
                post("/postComplexJson").
                then().
                spec(customResponseSpecification).
                assertThat().body("msg",equalTo("success"));

    }
}
