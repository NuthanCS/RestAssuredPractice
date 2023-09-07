package requestSpecification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RequestSpecUsingSpecBuilderClass {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeExecution()
    {
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").build();
        //requestSpecBuilder.setBaseUri("https://reqres.in/api");

       // requestSpecification= requestSpecBuilder.build();
    }


    @Test
    public void validateStatusCode()
    {
        Response response = given(requestSpecification).get("/users").then().log().all().extract().response();
        assertThat(response.statusCode(),equalTo(200));
    }

    @Test
    public void validateResponseBody()
    {
        Response response = given(requestSpecification).get("/users").then().log().all().extract().response();
        assertThat(response.path("data[1].id").toString(), equalTo("2"));
    }


}
