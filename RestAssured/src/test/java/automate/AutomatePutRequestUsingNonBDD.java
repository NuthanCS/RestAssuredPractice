package automate;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AutomatePutRequestUsingNonBDD {

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
    public void automatePutUsingNonBDD()
    {
        String id = "2";
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"Writer\"\n" +
                "}";

        Response response = with().body(payload).pathParam("id", id).put("/users/{id}");
        assertThat(response.path("name").toString(), equalTo("morpheus"));
        assertThat(response.path("job").toString(), equalTo("Writer"));
    }

}
