package restAssuredBasics;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestResponseExtraction {

    @Test
    public void responseExtraction()
    {
        Response res = given().baseUri("https://reqres.in").param("page","2").
                when().get("api/users").
                then().assertThat().statusCode(200).
                extract().response();

        System.out.println("Response is : "+res.asPrettyString());
    }

    @Test
    public void singleFieldExtraction()
    {
        Response res = given().baseUri("https://reqres.in").param("page","2").
                when().get("api/users").
                then().assertThat().statusCode(200).
                extract().response();

        JsonPath jsonPath = new JsonPath(res.asString());
        System.out.println("email of the employee Tobias : "+ jsonPath.getString("data[2].email"));

        //System.out.println("email of the employee Tobias  : "+res.path("data[2].email"));
    }

    @Test
    public void singleFieldExtractionAsString()
    {
        String res = given().baseUri("https://reqres.in").param("page","2").
                when().get("api/users").
                then().assertThat().statusCode(200).
                extract().response().asString();

        System.out.println(JsonPath.from(res).getString("data[2].email"));


        //System.out.println("email of the employee Tobias  : "+res.path("data[2].email"));
    }


    @Test
    public void hamcrestAssertionForSingleFieldExtractionAsString()
    {
        String email = given().baseUri("https://reqres.in").param("page","2").
                when().get("api/users").
                then().assertThat().statusCode(200).
                extract().response().path("data[2].email");

        System.out.println(email);
        //using hamcrest
        assertThat(email, equalTo("tobias.funke@reqres.in"));

        //using testNG
        Assert.assertEquals(email,"tobias.funke@reqres.in");
    }

    @Test
    public void hamcrestAssertionMethods()
    {
        given().baseUri("https://reqres.in").param("page","2").
                when().get("api/users").
                then().log().all().assertThat().statusCode(200).

                //so if we use contains we should provide all the values which ID consists or else it will throw error
                body("data.id",contains(7,8,9,10,11,12),
                        "data.id", hasSize(6),
                        "data.id",is(not(empty())),
                        "data.id",containsInAnyOrder(8,12,10,7,11,9),
                        "data.id",is(not(emptyArray())),
                        "data[0]",hasKey("id"),
                        "data[0]",hasValue(7),
                        "data[0]",hasEntry("id",7)).

                //assert the values of ID that can be in any order but should contain all the values
                body("data.id",containsInAnyOrder(8,12,10,7,11,9)).

                //assert that values are not empty in generic way
                body("data.id", is(not(empty()))).

                //asserting specific array is empty or not
                body("data.id",is(not(emptyArray()))).

                //asserting the collection size
                body("data.id", hasSize(6)).

                //assert that every value in the collection starts with
                body("data.avatar",everyItem(startsWith("h"))).

                //Asserts that the collection contains key but not value
                body("data[0]",hasKey("id")).

                //asserts that collection map contains value
                body("data[0]",hasValue(7)).

                body("data[0]",hasEntry("id",7));













    }

}
