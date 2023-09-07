package responseSpecification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ResponseSpecUsingSpecBuilderClass {

    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeExecution()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        responseSpecification= new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();

    }

    @Test
    public void validateStatusCode()
    {
        get("/users").then().spec(responseSpecification);
    }

    @Test
    public void validateResponseBody()
    {
        Response response = get("/users").then().spec(responseSpecification).extract().response();
        assertThat(response.path("data[1].id").toString(), equalTo("2"));
    }


}
