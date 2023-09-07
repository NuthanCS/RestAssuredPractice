package restAssuredBasics;

import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import java.util.HashSet;

import static io.restassured.RestAssured.*;

public class RequestAndResponseLogging {

    @Test
    public void requestAndResponseLogging()
    {

        given().baseUri("https://reqres.in").param("page","2").
                //for logging request
                log().all().

                when().get("api/users").
                then().

                //for logging response and it should always be used after then
                log().all().
                assertThat().statusCode(200);
    }

    @Test
    public void LogIfOnlyErrorOccurs()
    {

        given().baseUri("https://reqres.in").param("page","2").
                        log().all().

                when().get("api/users").

                then().
                //for logging response only if error occurs
                        log().ifError().
                assertThat().statusCode(200);
    }

    @Test
    public void LogIfOnlyValidationErrorOccurs()
    {

        given().baseUri("https://reqres.in").param("page","2").

                //if we don't want to use separate log method for request and response we can use below code
                config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                //log().ifValidationFails().

                when().get("api/users").

                then().
                //for logging response only if Validation fails
                        //log().ifValidationFails().
                assertThat().statusCode(20);
    }


    @Test
    public void LogBlacklistHeaders()
    {

        given().baseUri("https://reqres.in").param("page","2").

                //if we don't want to use separate log method for request and response we can use below code
                        config(config.logConfig(LogConfig.logConfig().blacklistHeader("Accept"))).
                log().all().

                        when().get("api/users").

                then().

                        assertThat().statusCode(200);
    }

    @Test
    public void LogBlacklistMultipleHeaders()
    {
        HashSet<String> headers = new HashSet<String>();
        headers.add("Accept");
        headers.add("X-Api-Key");

        given().baseUri("https://reqres.in").param("page","2").

                //if we don't want to use separate log method for request and response we can use below code
                        config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).
                log().all().

                when().get("api/users").

                then().

                assertThat().statusCode(200);
    }

}
