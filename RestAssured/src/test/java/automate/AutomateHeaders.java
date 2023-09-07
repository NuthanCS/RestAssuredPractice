package automate;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AutomateHeaders {

    @Test
    public void automateMultipleHeadersMethod1()
    {
        given().baseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").
                header("headerName","value2").
                header("x-mock-match-request-headers","headerName").
                when().get("/get").
                then().log().all().
                assertThat().statusCode(200);
    }

    @Test
    public void automateMultipleHeadersMethod2()
    {
        Header header = new Header("headerName","value1");
        Header matchHeader = new Header("x-mock-match-request-headers","headerName");

        given().baseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").
                header(header).
                header(matchHeader).
                when().get("/get").
                then().log().all().
                assertThat().statusCode(200);
    }

    @Test
    public void automateMultipleHeadersUsingHeadersObject()
    {
        Header header = new Header("headerName","value1");
        Header matchHeader = new Header("x-mock-match-request-headers","headerName");

        Headers headers = new Headers(header, matchHeader);

        given().baseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").
                headers(headers).
                when().get("/get").
                then().log().all().
                assertThat().statusCode(200);
    }

    @Test
    public void automateMultipleHeadersUsingMap()
    {

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("headerName","value1");
        headers.put("x-mock-match-request-headers","headerName");

        given().baseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").
                headers(headers).
                when().get("/get").
                then().log().all().
                assertThat().statusCode(200);
    }

    @Test
    public void automateMultiValueHeaders()
    {

        Header header1 = new Header("multiValueHeader", "value1");
        Header header2 = new Header("multiValueHeader" , "value2");

        Headers headers = new Headers(header1, header2);

        given().baseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").log().headers().
                headers(headers).
                when().get("/get").
                then().log().all().
                assertThat().statusCode(200);
    }

    @Test
    public void assertResponseHeaders()
    {

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("headerName","value1");
        headers.put("x-mock-match-request-headers","headerName");

        given().baseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").
                headers(headers).
                when().get("/get").
                then().log().all().
                assertThat().statusCode(200).headers("responseHeader","resValue1");
    }

    @Test
    public void extractResponseHeaders() {

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("headerName", "value1");
        headers.put("x-mock-match-request-headers", "headerName");

        Headers extractedHeader = given().baseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").
                headers(headers).
                when().get("/get").
                then().
                extract().headers();

        for (Header header : extractedHeader) {
            System.out.println("Header Name : " + header.getName() + " and " + "Header value : " + header.getValue());
        }
    }

        @Test
         public void extractMultiValueHeader()
        {
            HashMap<String, String > header = new HashMap<String, String>();
            header.put("headerName","value1");
            header.put("x-mock-match-request-headers","headerName");

            Headers extractedHeaders = given().baseUri("https://473a49dd-521d-46f3-915f-9db1de3a21d0.mock.pstmn.io").
                    headers(header).
                    when().get("/get").
                    then().extract().headers();

            List<String> headerValue = extractedHeaders.getValues("multiValueHeader");
            for(String value : headerValue) {
                System.out.println(value);
            }



        }

    }


