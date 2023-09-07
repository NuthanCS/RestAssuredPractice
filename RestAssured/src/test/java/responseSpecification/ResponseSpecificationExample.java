package responseSpecification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ResponseSpecificationExample {

    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeExecution() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .log(LogDetail.ALL);

        RestAssured.requestSpecification= requestSpecBuilder.build();

        responseSpecification = RestAssured.expect().
                statusCode(200).
                contentType(ContentType.JSON);
    }

    @Test
    public void validateStatusCode()
    {
        get("/users").then().spec(responseSpecification).log().all();
    }

    @Test
    public void validateResponseBody()
    {
        Response response = get("/users").then().spec(responseSpecification).log().all().extract().response();
        assertThat(response.path("data[1].id").toString(), equalTo("2"));
    }


}
