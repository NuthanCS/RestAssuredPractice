package restAssuredBasics;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VerifyAsserThat {

    @BeforeClass
    public void execute()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void verifyAssertThat()
    {
        List<String> first_name=  new ArrayList<String>(), last_name= new ArrayList<String>();
        List<Integer> id;

        Response response = given().get("/users");

        first_name= JsonPath.from(response.getBody().asString()).get("data.first_name");

        last_name = JsonPath.from(response.getBody().asString()).get("data.last_name");

        id= new ArrayList<Integer>();
        id = JsonPath.from(response.getBody().asString()).get("data.id");

        assertThat((new Object[]{first_name.get(0), last_name.get(0),
        first_name.get(1), last_name.get(1),
        first_name.get(2), last_name.get(2),
        first_name.get(3), last_name.get(3),
                first_name.get(4), last_name.get(4),
                first_name.get(5), last_name.get(5)}), is (new Object[]{"George","Bluth",
                "Janet", "Weaver",
                "Emma","Wong",
                "Eve","Holt",
                "Charles","Morris",
                "Tracey","Ramos"}));

    }

}
