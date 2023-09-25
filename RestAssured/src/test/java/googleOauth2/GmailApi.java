package googleOauth2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Base64;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GmailApi {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String accessToken = "ya29.a0AfB_byCnecTXCKNw1XlbvDC6n9sOSoWfacal4Z7mKs59umWxlgFN5rGhsLrg8utdHgzq0kkz7aUnZ_mL_MQ8ROLRsKqv6oIhkUO1tenhu9r_2C7dgVEZoI_1n2zPi5jZNGTlaxEwU-h39YAcXByUD9vGaHdgZqRRI9AVEgaCgYKAVcSARMSFQGOcNnCjocUhxAlnL9XhaiB4szNeA0173";

    @BeforeClass
    public void execute()
    {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://gmail.googleapis.com/").
                addHeader("Authorization","Bearer "+accessToken).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
               // expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void getUserProfile()
    {
        given(requestSpecification).
                basePath("/gmail/v1").
                pathParam("userid","thundervarman@gmail.com").
                when().
                get("/users/{userid}/profile").
                then().
                spec(responseSpecification);

    }

    @Test
    public void sendEmail()
    {
        //first we need to send the body in RFC 2822 format
        String message = "From: thundervarman@gmail.com\n" +
                "To: csnuthan@gmail.com\n" +
                "Subject: Sample Email\n" +
                "\n" +
                "Good MORNING baby";

        // Then we need to encode it using base64 url encoder
        String base64UrlencodedMessage = Base64.getUrlEncoder().encodeToString(message.getBytes());

        //we need to create hashmap to send the request payload
        HashMap<String, String> payload = new HashMap<>();
        payload.put("raw", base64UrlencodedMessage);

        given(requestSpecification).
                basePath("/gmail/v1").
                pathParam("userid","thundervarman@gmail.com").
                body(payload).
                when().
                post("/users/{userid}/messages/send").
                then().
                spec(responseSpecification);

    }

    @Test
    public void deleteEmail()
    {

        String message = "From: thundervarman@gmail.com\n" +
                "To: csnuthan@gmail.com\n" +
                "Subject: Sample Email\n" +
                "\n" +
                "Get Ready ASAP";

        String base64UrlEncoded = Base64.getUrlEncoder().encodeToString(message.getBytes());

        HashMap<String, String> payload = new HashMap<>();
        payload.put("raw", base64UrlEncoded);

       Response response = given(requestSpecification).
                basePath("/gmail/v1/").
                pathParam("userId","thundervarman@gmail.com").
               body(payload).
                when().
                post("/users/{userId}/messages/send").then().spec(responseSpecification).extract().response();

       String Id = response.path("id");
       System.out.println(Id);

       given(requestSpecification).
               basePath("/gmail/v1/").
               pathParam("userId","thundervarman@gmail.com").
               pathParam("id",Id).
               when().
               delete("users/{userId}/messages/{id}").
               then().spec(responseSpecification).assertThat().statusCode(204);

    }

    @Test
    public void getEmailsList()
    {
        given(requestSpecification).
                basePath("/gmail/v1").
                pathParam("userId","thundervarman@gmail.com").
                when().
                get("users/{userId}/messages").
                then().
                spec(responseSpecification);
    }

    @Test
    public void getSpecificEmail()
    {

        Response response = given(requestSpecification).
                basePath("/gmail/v1").
                pathParam("userId","thundervarman@gmail.com").
                when().
                get("users/{userId}/messages").
                then().
                spec(responseSpecification).extract().response();

        String id = response.path("messages[0].id");

        given(requestSpecification).
                basePath("/gmail/v1").
                pathParam("userId","thundervarman@gmail.com").
                pathParam("id",id).
                when().
                get("users/{userId}/messages/{id}").
                then().spec(responseSpecification);

    }


}
