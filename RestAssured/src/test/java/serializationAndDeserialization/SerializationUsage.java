package serializationAndDeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class SerializationUsage {
    @BeforeClass
    public void execute()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.getpostman.com").
                setContentType("application/json").
                addHeader("x-mock-match-request-body","true").
                addHeader("X-API-Key","PMAK-64f888ba98f63100392e6ddd-bcc3b50b3e2ba7e70cb0adc9b0e714612d").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectContentType("application/json").
                expectStatusCode(200).log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }
    @Test
    public void serializeMapToJsonObjectUsingJackson() throws JsonProcessingException {
        HashMap<String, String> nestedObject = new HashMap<String, String>();
        nestedObject.put("name","myThirdWorkspace");
        nestedObject.put("type","personal");
        nestedObject.put("description","Rest assured created this");

        HashMap<String, Object> mainObject = new HashMap<String, Object>();
        mainObject.put("workspace", nestedObject);

        //we are going to use Jackson library outside the RestAssured to do serialization
        // i.e we are using jackson explicitly using objectMapper class
        ObjectMapper objectMapper = new ObjectMapper();
        String mainObjectStr = objectMapper.writeValueAsString(mainObject);

        given().
                body(mainObjectStr).
                when().
                post("/workspaces").then().
                assertThat().
                body("workspace.name", equalTo("myThirdWorkspace"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));

    }

    @Test
    public void serializeListToJsonArrayUsingJackson() throws JsonProcessingException {

        HashMap<String, String > object1 = new HashMap<String, String>();
        object1.put("id","5001");
        object1.put("type","None");

        HashMap<String, String> object2 = new HashMap<String, String>();
        object2.put("id","5002");
        object2.put("type","Glazed");

        List<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
        arrayList.add(object1);
        arrayList.add(object2);

        ObjectMapper objectMapper = new ObjectMapper();
        String mainList = objectMapper.writeValueAsString(arrayList);

        given().baseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").
                header("x-mock-match-request-body","true").
                contentType("application/json;charset=utf-8").log().all().
                body(mainList).
                when().
                post("/post").
                then().
                assertThat().statusCode(200).
                body("msg",equalTo("success"));

    }

    @Test
    public void serializeJacksonObjectNodeToJson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode nestedObjectNode = objectMapper.createObjectNode();
        nestedObjectNode.put("name","myFourthWorkspace");
        nestedObjectNode.put("type","personal");
        nestedObjectNode.put("description","Rest assured created this");

        ObjectNode mainObjectNode = objectMapper.createObjectNode();
        mainObjectNode.set("workspace", nestedObjectNode);

        String mainObjectStr = objectMapper.writeValueAsString(mainObjectNode );

        given().
                body(mainObjectNode).
                when().
                post("/workspaces").then().
                assertThat().
                body("workspace.name", equalTo("myFourthWorkspace"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));

    }

    @Test
    public void serializeJacksonArrayNodeToJson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode1 = objectMapper.createObjectNode();
        objectNode1.put("id","5001");
        objectNode1.put("type","None");

        ObjectNode objectNode2 = objectMapper.createObjectNode();
        objectNode2.put("id","5002");
        objectNode2.put("type","Glazed");

        ArrayNode arrayNodeList = objectMapper.createArrayNode();
        arrayNodeList.add(objectNode1);
        arrayNodeList.add(objectNode2);

        String mainList = objectMapper.writeValueAsString(arrayNodeList);

        given().baseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").
                header("x-mock-match-request-body","true").
                contentType("application/json;charset=utf-8").log().all().
                body(mainList).
                when().
                post("/post").
                then().
                assertThat().statusCode(200).
                body("msg",equalTo("success"));

    }


}
