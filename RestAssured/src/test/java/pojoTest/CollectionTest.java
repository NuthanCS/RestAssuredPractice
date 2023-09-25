package pojoTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.ValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.collection.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class CollectionTest {

    @BeforeClass
    public void execute()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.getpostman.com").
                setContentType("application/json").
                addHeader("X-API-Key","PMAK-64f888ba98f63100392e6ddd-bcc3b50b3e2ba7e70cb0adc9b0e714612d").
                //addQueryParam("Workspace ID","5e6b8626-5ed6-4dd3-8574-be33ee74c37e").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder =new ResponseSpecBuilder().
                expectContentType("application/json").
                expectStatusCode(200).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();

    }

//    @Test
//    public void serializeCreateCollectionTest()
//    {
//        Header header = new Header("Content-Type","application/json");
//        List<Header> headerList = new ArrayList<Header>();
//        headerList.add(header);
//
//        Body body = new Body("raw","{\\\"data\\\": \\\"123\\\"}");
//
//        RequestBase request = new RequestBase("https://postman-echo.com/post","POST", headerList, body,"This is a sample POST Request");
//
//        RequestRootBase requestRoot = new RequestRootBase("Sample POST Request",request);
//        List<RequestRootBase> requestRootList = new ArrayList<RequestRootBase>();
//        requestRootList.add(requestRoot);
//
//        FolderBase folder = new FolderBase("This is a folder",requestRootList);
//        List<FolderBase> folderList = new ArrayList<FolderBase>();
//        folderList.add(folder);
//
//        Info info = new Info("Sample Collection 03","This is just a sample collection.","https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
//
//        CollectionBase collection = new CollectionBase(info, folderList);
//
//        CollectionRootBase collectionRoot = new CollectionRootBase(collection);
//
//
//        given().
//                body(collectionRoot).
//                when().
//                post("/collections").
//                then().
//                body("collection.name",
//                        equalTo("Sample Collection 03"));
//    }

//    @Test
//    public void deserializeCreateCollectionTestUsingCustomAssertion() throws JsonProcessingException, JSONException {
//        Header header = new Header("Content-Type","application/json");
//        List<Header> headerList = new ArrayList<Header>();
//        headerList.add(header);
//
//        Body body = new Body("raw","{\\\"data\\\": \\\"123\\\"}");
//
//        RequestBase request = new RequestBase("https://postman-echo.com/post","POST", headerList, body,"This is a sample POST Request");
//
//        RequestRootBase requestRoot = new RequestRootBase("Sample POST Request",request);
//        List<RequestRootBase> requestRootList = new ArrayList<RequestRootBase>();
//        requestRootList.add(requestRoot);
//
//        FolderBase folder = new FolderBase("This is a folder",requestRootList);
//        List<FolderBase> folderList = new ArrayList<FolderBase>();
//        folderList.add(folder);
//
//        Info info = new Info("Sample Collection 03","This is just a sample collection.","https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
//
//        CollectionBase collection = new CollectionBase(info, folderList);
//
//        CollectionRootBase collectionRoot = new CollectionRootBase(collection);
//
//
//        String collectionUid = given().
//                body(collectionRoot).
//                when().
//                post("/collections").
//                then().extract().response().path(" collection.uid");
//        System.out.println("Collection Uid : "+collectionUid);
//
//        CollectionRootBase deserializedCollectionRoot = given().
//                pathParam("collectionUid",collectionUid).
//                when().
//                get("/collections/{collectionUid}").
//                then().
//                extract().response().
//                as(CollectionRootBase.class);
//        System.out.println(deserializedCollectionRoot.toString());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String collectionRootStr = objectMapper.writeValueAsString(collectionRoot);
//        String deserializedCollectionRootStr = objectMapper.writeValueAsString(deserializedCollectionRoot);
//
//
//        //We are performing json assertions with some customization
//        //It uses strict ordering for arrays.
//        //It customizes the comparison behavior for values found at the JSON path "collection.item[*].item[*].request.url" by always considering them equal
//        //,regardless of their actual values.
//        //Here by we are successfully compared the json bodies by excluding unequal values i.e url
//        JSONAssert.assertEquals(collectionRootStr, deserializedCollectionRootStr,
//                new CustomComparator(JSONCompareMode.STRICT_ORDER,
//                        new Customization("collection.item[*].item[*].request.url",
//                                new ValueMatcher<Object>()
//                                {
//                                    public boolean equal(Object o1, Object o2){
//                                        return true;
//                                    }
//                        })));
//
//    }

    @Test
    public void serialize_And_Deserialize_Using_BaseClass_Implementation_With_Custom_Assertion() throws JsonProcessingException, JSONException {
       //we are going to create request payload only for request pojo classes
        Header header = new Header("Content-Type","application/json");
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(header);

        Body body = new Body("raw","{\\\"data\\\": \\\"123\\\"}");

        RequestRequest request = new RequestRequest("https://postman-echo.com/post","POST", headerList, body,"This is a sample POST Request");

        RequestRootRequest requestRoot = new RequestRootRequest("Sample POST Request",request);
        List<RequestRootRequest> requestRootList = new ArrayList<RequestRootRequest>();
        requestRootList.add(requestRoot);

        FolderRequest folder = new FolderRequest("This is a folder",requestRootList);
        List<FolderRequest> folderList = new ArrayList<FolderRequest>();
        folderList.add(folder);

        Info info = new Info("Sample Collection 03","This is just a sample collection.","https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        CollectionRequest collection = new CollectionRequest(info, folderList);

        CollectionRootRequest collectionRootRequest = new CollectionRootRequest(collection);


        String collectionUid = given().
                body(collectionRootRequest).
                when().
                post("/collections").
                then().extract().response().path(" collection.uid");
        System.out.println("Collection Uid : "+collectionUid);


        //We need to get the response and deserialize the response using response pojo class that contains response body
        CollectionRootResponse deserializedCollectionRootResponse = given().
                pathParam("collectionUid",collectionUid).
                when().
                get("/collections/{collectionUid}").
                then().
                extract().response().
                as(CollectionRootResponse.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String collectionRootRequestStr = objectMapper.writeValueAsString(collectionRootRequest);
        String deserializedCollectionRootResponseStr = objectMapper.writeValueAsString(deserializedCollectionRootResponse);


        //We are performing json assertions with some customization
        //It uses strict ordering for arrays.
        //It customizes the comparison behavior for values found at the JSON path "collection.item[*].item[*].request.url" by always considering them equal
        //,regardless of their actual values.
        //Here by we are successfully compared the json bodies by excluding unequal values i.e url
        JSONAssert.assertEquals(collectionRootRequestStr, deserializedCollectionRootResponseStr,
                new CustomComparator(JSONCompareMode.STRICT_ORDER,
                        new Customization("collection.item[*].item[*].request.url",
                                new ValueMatcher<Object>()
                                {
                                    public boolean equal(Object o1, Object o2){
                                        return true;
                                    }
                                })));

    }

    @Test
    public void extract_Url_field_from_request_and_response_and_assertIt() throws JsonProcessingException, JSONException {
        //we are going to create request payload only for request pojo classes
        Header header = new Header("Content-Type","application/json");
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(header);

        Body body = new Body("raw","{\\\"data\\\": \\\"123\\\"}");

        RequestRequest request = new RequestRequest("https://postman-echo.com/post","POST", headerList, body,"This is a sample POST Request");

        RequestRootRequest requestRoot = new RequestRootRequest("Sample POST Request",request);
        List<RequestRootRequest> requestRootList = new ArrayList<RequestRootRequest>();
        requestRootList.add(requestRoot);

        FolderRequest folder = new FolderRequest("This is a folder",requestRootList);
        List<FolderRequest> folderList = new ArrayList<FolderRequest>();
        folderList.add(folder);

        Info info = new Info("Sample Collection 03","This is just a sample collection.","https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        CollectionRequest collection = new CollectionRequest(info, folderList);

        CollectionRootRequest collectionRootRequest = new CollectionRootRequest(collection);


        String collectionUid = given().
                body(collectionRootRequest).
                when().
                post("/collections").
                then().extract().response().path(" collection.uid");
        System.out.println("Collection Uid : "+collectionUid);


        //We need to get the response and deserialize the response using response pojo class that contains response body
        CollectionRootResponse deserializedCollectionRootResponse = given().
                pathParam("collectionUid",collectionUid).
                when().
                get("/collections/{collectionUid}").
                then().
                extract().response().
                as(CollectionRootResponse.class);

        //we are using List because folder can have multiple request and
        // multiple requests can have multiple urls,so we can extract all of them and assert it
        List<String> UrlRequestList= new ArrayList<String>();
        List<String> UrlResponseList= new ArrayList<String>();

        //To fetch URL from the request payload we can directly use requestRootList object since it is holding all the requests
        for(RequestRootRequest requestRootRequest:requestRootList)
        {
            //we got all the urls from the request payload and added to the UrlRequestList
           String requestUrl= requestRootRequest.getRequest().getUrl();
           System.out.println("url present in the request : "+ requestUrl);
           UrlRequestList.add(requestUrl);
        }

        //we are getting folder response and storing those folder list in the folderResponseList object
        List<FolderResponse> folderResponseList = deserializedCollectionRootResponse.getCollection().getItem();
        for (FolderResponse folderResponse:folderResponseList)
        {
            //here we are getting request root response list and storing it under requestRootResponseList variable
            List<RequestRootResponse>requestRootresponseList = folderResponse.getItem();

            //from the requestRootResponseList we are getting the URL object from the response body
            for (RequestRootResponse requestRootResponse: requestRootresponseList)
            {
               Url url =  requestRootResponse.getRequest().getUrl();

               //so here we are getting the String url from the url object and storing under the responseUrl variable
               String responseUrl = url.getRaw();
               System.out.println("url present in the response : "+responseUrl);
                UrlResponseList.add(responseUrl);

            }
        }

        //Asserting the url from the request body and response body
        assertThat(UrlResponseList,containsInAnyOrder(UrlRequestList.toArray()));

    }

    @Test
    public void simple_Collection_Creation_and_Assertion() throws JsonProcessingException, JSONException {

        //we are passing empty folder list
        List<FolderRequest> folderList = new ArrayList<FolderRequest>();

        Info info = new Info("Sample Collection 04","This is just a sample collection.","https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        CollectionRequest collection = new CollectionRequest(info, folderList);

        CollectionRootRequest collectionRootRequest = new CollectionRootRequest(collection);


        String collectionUid = given().
                body(collectionRootRequest).
                when().
                post("/collections").
                then().extract().response().path(" collection.uid");
        System.out.println("Collection Uid : "+collectionUid);


        //We need to get the response and deserialize the response using response pojo class that contains response body
        CollectionRootResponse deserializedCollectionRootResponse = given().
                pathParam("collectionUid",collectionUid).
                when().
                get("/collections/{collectionUid}").
                then().
                extract().response().
                as(CollectionRootResponse.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String collectionRootRequestStr = objectMapper.writeValueAsString(collectionRootRequest);
        String deserializedCollectionRootResponseStr = objectMapper.writeValueAsString(deserializedCollectionRootResponse);

        assertThat(objectMapper.readTree(collectionRootRequestStr),
                equalTo(objectMapper.readTree(deserializedCollectionRootResponseStr)));

    }

//    Assignment: by using this collection Pojo we can automate below scenarios for practice
//    1. Collection with multiple folders
//    2. Collection with one folder but multiple requests inside that folder
//    3. Collection with only one request
//    4. Collection with one request with multiple headers


}
