import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sampleGetTest {

    @Test
    public void getUser(){
        RestAssured.baseURI="https://reqres.in/api";
        Response response = RestAssured.given().param("page","2").
                when().get("/users/");
        System.out.println("\n\n to String\n"+response.toString());
        System.out.println("\n\n as String\n"+response.asString());
        System.out.println("\n\n response covg\n"+response);


       /* int pageSize= (response.getBody().jsonPath().get("per_page"));
        //response.getBody().jsonPath().get("data");
        JsonPath path = new JsonPath(response.asString());
        int size = path.getInt("data.size()");
        System.out.println(pageSize);
        System.out.println(size);
        Assert.assertEquals(pageSize,size);*/

                   //     .then().assertThat().statusCode(200);



    }

}
