package requestSpecification;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestSpecificationExample {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeExecution()
    {
        requestSpecification= given().
                baseUri("https://reqres.in/api");
    }

    @Test
    public void validateStatusCode()
    {
        given().spec(requestSpecification).
                when().get("/users").
                then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void validateResponseBody()
    {
        given().spec(requestSpecification).
                when().get("/users").
                then().log().all().body("data[1].id",equalTo(2));
    }

}
