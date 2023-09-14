package pojoTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojoExample.createUserPojo.AddressPojo;
import pojoExample.createUserPojo.GeoPojo;
import pojoExample.createUserPojo.UserMainPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateSampleUserTest {

    @BeforeClass
    public void execute()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com").
                setContentType("application/json").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectContentType("application/json").
                expectStatusCode(201).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void createUserTest()
    {
        GeoPojo geoPojo =new GeoPojo("-37.3159","81.1496");
        AddressPojo addressPojo = new AddressPojo("Kulas Light","Apt. 556","Gwenborough","92998-3874", geoPojo);
        UserMainPojo userMainPojo = new UserMainPojo("Leanne Graham","Bret","Sincere@april.biz", addressPojo);


        given().body(userMainPojo).
                when().post("/users").
                then().assertThat().body("id",equalTo(11));
    }


}
