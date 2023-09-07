package requestSpecification;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RequestSpecFromBddToNonBdd {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeExecution()
    {
        requestSpecification= given().
                baseUri("https://reqres.in/api").
                log().all();// it will log the request
    }

    @Test
    public void validateStatusCode()
    {
        //using the requestSpecification instance varioable we are extracting the response of the get Request and storing it in the response variable
        Response response = requestSpecification.get("/users").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void validateResponseBody()
    {
        Response response = requestSpecification.get("/users").then().log().all().extract().response();
        assertThat(response.path("data[1].id").toString(),equalTo("2"));
    }


}
