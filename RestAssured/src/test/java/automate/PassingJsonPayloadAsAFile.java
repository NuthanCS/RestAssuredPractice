package automate;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PassingJsonPayloadAsAFile {

    @BeforeClass
    public void execute()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                setContentType(ContentType.JSON).
                        log(LogDetail.ALL);
        RestAssured.requestSpecification= requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(201).
                expectContentType(ContentType.JSON).
                        log(LogDetail.ALL);
        RestAssured.responseSpecification= responseSpecBuilder.build();
    }

    @Test
    public void postRequestUsingFile()
    {
        File file = new File("src/main/resources/createEmployeePayload.json");
        given().body(file).
                when().post("/users").
                then().log().all().
                assertThat().body("name", equalTo("morpheus"));
    }

}
