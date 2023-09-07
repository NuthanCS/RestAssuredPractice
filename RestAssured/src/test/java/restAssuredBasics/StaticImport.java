package restAssuredBasics;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class StaticImport {

    @org.testng.annotations.Test
    public void sample()
    {

        given().baseUri("https://reqres.in").when().get("api/users").then().statusCode(200).
                body("page", is(equalTo(Integer.parseInt("1"))));
    }

}
