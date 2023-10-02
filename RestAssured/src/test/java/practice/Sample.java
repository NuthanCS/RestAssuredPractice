package practice;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Sample {


    @BeforeClass
    public void execute()
    {
         RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
                requestSpecBuilder.setBaseUri("https://reqres.in/api");
                requestSpecBuilder.setContentType(ContentType.JSON);

                RestAssured.requestSpecification = requestSpecBuilder.build();
    }


    @Test
    public void sample()
    {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.
                query(RestAssured.requestSpecification);

        System.out.println("Base URI : "+queryableRequestSpecification.getBaseUri());
        System.out.println("Headers : "+queryableRequestSpecification.getHeaders());

    }



}
