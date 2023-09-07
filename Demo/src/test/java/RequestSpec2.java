import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class RequestSpec2 {

    @Test
    public void requestSpecUsingDifferentApproach()
    {
        RequestSpecification req = RestAssured.given();
        req.baseUri("https://reqres.in/api");
        req.basePath("/users/");
        req.param("page","1");

        Response response= req.get();
        //response.prettyPrint();
        System.out.println(("Direct call : "+response.asString()));

        Response response1 = RestAssured.given(req).get();
        //response1.prettyPrint();
        System.out.println(("Passing request inside given method : "+response1.asString()));

        Response response2 = RestAssured.given().spec(req).get();
        //response2.prettyPrint();
        System.out.println(("Using spec method : "+response2.asString()));
    }

    @Test
    public void requestSpecBuilderType1()
    {
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .setBasePath("/users")
                .addParam("page","2")
                .build();

        RestAssured.given(reqSpec).get().prettyPrint();

    }

    @Test
    public void requestSpecBuilderType2()
    {
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setBaseUri("https://reqres.in/api");
        reqBuilder.setBasePath("/users");
        reqBuilder.addParam("page","2");
        RequestSpecification reqSpec = reqBuilder.build();

        RestAssured.given(reqSpec).get().prettyPrint();


    }


}
