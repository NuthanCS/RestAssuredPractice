package requestSpecification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DefaultRequestSpecification {

    @BeforeClass
    public void beforeExecution()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api");

        RestAssured.requestSpecification=requestSpecBuilder.build();
    }

    @Test
    public void validateStatusCode()
    {
        Response response= get("/users").then().log().all().extract().response();
        assertThat(response.statusCode(),is(equalTo(200)));
    }

    @Test
    public void validateResponseBody()
    {
        Response response= get("/users").then().log().all().extract().response();
        assertThat(response.path("data[1].id").toString(), equalTo("2"));
    }

}
