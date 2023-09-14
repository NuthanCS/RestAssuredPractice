package automate;

import io.restassured.config.EncoderConfig;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestParameters {

    @Test
    public void singleQueryParam()
    {
        given().
                baseUri("https://postman-echo.com").
                //Generic way of sending parameters
                //param("foo1","bar1").

                //Specific way of sending parameters
                        queryParam("foo1","bar1").
                log().all().
                when().
                get("/get").
                then().
                log().all().assertThat().statusCode(200);
    }

    @Test
    public void multipleQueryParams()
    {
        HashMap<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("foo1","bar1");
        queryParams.put("foo2","bar2");

        given().
                baseUri("https://postman-echo.com").queryParams(queryParams).
                log().all().
                when().
                get("/get").
                then().
                log().all().assertThat().statusCode(200);
    }

    @Test
    public void multiValueQueryParams()
    {

        given().baseUri("https://postman-echo.com/").
                queryParams("foo1","bar1;bar2;bar3").log().all().
                //queryParams("foo1","bar1, bar2, bar3").log().all().
                        //queryParams("foo1", "bar1", "foo2", "bar2")
                when().
                get("/get").
                then().
                log().all().assertThat().statusCode(200);

    }

    @Test
    public void pathParams()
    {

        given().baseUri("https://reqres.in/api").
                pathParam("id","2").log().all().
                when().get("/users/{id}").
                then().
                log().all().assertThat().statusCode(200).body("data.first_name", equalTo("Janet"));

    }

    @Test
    public void multiPartFormData()
    {
        given().baseUri("https://postman-echo.com").
                multiPart("foo1","bar1").
                multiPart("foo2","bar2").log().all().
                when().
                post("/post").
                then().
                log().all().assertThat().statusCode(200);
    }

    @Test
    public void FormUrlEncodingData()
    {
        given().baseUri("https://postman-echo.com").
                config(config.encoderConfig(EncoderConfig.encoderConfig().
                        appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                formParam("key1","value1").
                formParam("key 1", "value 1").
                when().
                post("/post").
                then().
                log().all().assertThat().statusCode(200);
    }



}
