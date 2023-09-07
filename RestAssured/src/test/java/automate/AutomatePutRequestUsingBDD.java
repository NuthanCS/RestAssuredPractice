package automate;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AutomatePutRequestUsingBDD {

    @BeforeClass
    public void execute()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.requestSpecification= requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification= responseSpecBuilder.build();
    }

    @Test
    public void automatePutRequest()
    {

        String id = "2";
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"Writer\"\n" +
                "}";

        given().
                body(payload).pathParam("id", id).
                when().
                put("/users/{id}").
                then().
                assertThat().
                body("name", equalTo("morpheus"), "job", equalTo("Writer"));
    }

}
