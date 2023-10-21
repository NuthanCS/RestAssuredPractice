package automate;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PushbackInputStream;

import static io.restassured.RestAssured.given;

public class LoggingUsingFilters {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    @BeforeClass
    public void reuseFilters() throws FileNotFoundException {
        PrintStream printStream =new PrintStream(new File("src/main/resources/Sample1.log"));

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://postman-echo.com").
                addFilter(new RequestLoggingFilter(printStream)).
                addFilter(new ResponseLoggingFilter(printStream));
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void filters()
    {
        given().baseUri("https://postman-echo.com").

                //instead of log().all() we can use filters and we can exclude/include request and response information
                filters(new RequestLoggingFilter(LogDetail.BODY)).// logs only request body and prints in the console
                filters(new ResponseLoggingFilter(LogDetail.STATUS)).// logs only response code and prints in the console
                when().
                get("/get").
                then().
                statusCode(200);
    }


    @Test
    public void LoggingToFileUsingFilters() throws FileNotFoundException {
        // using PrintyStream class object we create a new File
        //PrintStream printStream = new PrintStream("src/main/resources/Sample.log"); another way of creating file
        PrintStream printStream = new PrintStream(new File("src/main/resources/Sample2.log"));
        given().baseUri("https://postman-echo.com").

                //instead of log().all() we can use filters and we can exclude/include request and response information
                filters(new RequestLoggingFilter(printStream)).// logs the request into the file
                filters(new ResponseLoggingFilter(printStream)).// logs the response into the file
                when().
                get("/get").
                then().
                statusCode(200);
    }

    @Test
    public void ReusingOfFilters() {

        given(requestSpecification).
                when().
                get("/get").
                then().spec(responseSpecification);

    }

}
