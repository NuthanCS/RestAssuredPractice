import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RequestSpec1 {

    RequestSpecification requestSpec;

    @BeforeClass
    public void setupRequestSpecification()
    {
        requestSpec= RestAssured.given()
                .baseUri("https://reqres.in/api")
                .basePath("/users");

    }

    @Test
    public void getUsers()
    {
        RestAssured.given()
                .spec(requestSpec)
                .when()
                .then()
                .statusLine("HTTP/1.1 200 OK");
        Response res = requestSpec.get();
        res.prettyPrint();
    }

    @Test
    public void getUserDetailsOfPage2()
    {
        RestAssured.given()
                .spec(requestSpec)
                .param("page","2")
                .when()
                .then()
                .statusLine("HTTP/1.1 200 OK");

    }

}
