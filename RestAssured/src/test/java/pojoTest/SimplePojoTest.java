package pojoTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojoExample.SimplePojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SimplePojoTest {

    @BeforeClass
    public void execute()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").
                setContentType("application/json").log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType("application/json").log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void simplePojoUsage1() {
        SimplePojo simplePojo = new SimplePojo("value1", "value2");
        given().body(simplePojo).
                when().
                post("/postSimplePojo").
                then().
                assertThat().body("key1", equalTo(simplePojo.getKey1()),
                        "key2", equalTo(simplePojo.getKey2()));
    }

    @Test
    public void simplePojoUsage2() {
        SimplePojo simplePojo = new SimplePojo();
        simplePojo.setKey1("value1");
        simplePojo.setKey2("value2");

        given().
                body(simplePojo).
                when().
                post("/postSimplePojo").
                then().
                assertThat().body("key1", equalTo(simplePojo.getKey1()),
                        "key2", equalTo(simplePojo.getKey2()));
    }

    @Test
    public void deserializeSimplePojo() throws JsonProcessingException {
        SimplePojo simplePojo = new SimplePojo("value1","value2");


        //after extracting we need to store it under class object
        SimplePojo deserializePojo = given().
                body(simplePojo).
                when().
                post("/postSimplePojo").
                then().
                extract().response().
                as(SimplePojo.class);// this will deserialize json object back to java object

        //we need to compare the deserialized object with the json object
        ObjectMapper objectMapper =new ObjectMapper();

        //we cannot directly compare the java object with json object that's why we are converting both the objects into String
        String deserializedStr = objectMapper.writeValueAsString(deserializePojo);
        String simplePojoStr = objectMapper.writeValueAsString(simplePojo);

        assertThat(objectMapper.readTree(deserializedStr), equalTo(objectMapper.readTree(simplePojoStr)));


    }

}
