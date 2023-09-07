package restAssuredBasics;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestAssertion {

    @Test
    public void assertStatusCode()
    {
        given().baseUri("https://reqres.in").param("page","2").
                when().get("api/users").
                then().log().all().assertThat().statusCode(200);
    }

    //using hamcrest
    @Test
    public void assertResponseBody()
    {

        given().baseUri("https://reqres.in").param("page","2").
                when().get("api/users").
                then().log().all().assertThat().body("total", equalTo(12)).
                and().body("page",is(2)).
                and().body("data[1].id",is(8));

    }

}
