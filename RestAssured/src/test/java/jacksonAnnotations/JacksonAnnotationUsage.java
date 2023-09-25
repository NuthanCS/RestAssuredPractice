package jacksonAnnotations;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.workspace.MainWorkspacePojo;
import pojo.workspace.WorkspacePojo;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class JacksonAnnotationUsage {

    @BeforeClass
    public void execute()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.getpostman.com").
                setContentType("application/json").
                addHeader("X-API-Key","PMAK-64f888ba98f63100392e6ddd-bcc3b50b3e2ba7e70cb0adc9b0e714612d").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder =new ResponseSpecBuilder().
                expectContentType("application/json").
                expectStatusCode(200).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();

    }


    @Test
    public void nonNullUsage()
    {
        WorkspacePojo workspacePojo =new WorkspacePojo("myFifthWorkspace","personal","Rest assured created this");
        MainWorkspacePojo mainWorkspacePojo = new MainWorkspacePojo(workspacePojo);

        given().
                body(mainWorkspacePojo).
                when().
                post("/workspaces").
                then().
                body("workspace.name", equalTo("myFifthWorkspace"));
    }

    @Test
    public void nonDefaultUsage()
    {
        WorkspacePojo workspacePojo =new WorkspacePojo("myFifthWorkspace","personal","Rest assured created this");
        MainWorkspacePojo mainWorkspacePojo = new MainWorkspacePojo(workspacePojo);

        given().
                body(mainWorkspacePojo).
                when().
                post("/workspaces").
                then().
                body("workspace.name", equalTo("myFifthWorkspace"));
    }

    @Test
    public void nonEmptyUsage()
    {
        WorkspacePojo workspacePojo =new WorkspacePojo("myFifthWorkspace","personal","Rest assured created this");
        HashMap<String, String> hashMap = new HashMap<String, String>();
        workspacePojo.setHashMap(hashMap);
        MainWorkspacePojo mainWorkspacePojo = new MainWorkspacePojo(workspacePojo);

        given().
                body(mainWorkspacePojo).
                when().
                post("/workspaces").
                then().
                body("workspace.name", equalTo("myFifthWorkspace"));
    }

    @Test
    public void jsonIgnoreAndJsonIgnoreProperties()
    {
        WorkspacePojo workspacePojo =new WorkspacePojo("myFifthWorkspace","personal","Rest assured created this");
        MainWorkspacePojo mainWorkspacePojo = new MainWorkspacePojo(workspacePojo);

        MainWorkspacePojo deserializedPojo = given().
                body(mainWorkspacePojo).
                when().
                post("/workspaces").
                then().extract().response().
                as(MainWorkspacePojo.class);

        assertThat(deserializedPojo.getWorkspace().getName(),
                equalTo(mainWorkspacePojo.getWorkspace().getName()));
        assertThat(deserializedPojo.getWorkspace().getId(),
                matchesPattern("^[a-z0-9-]{36}$"));


    }

}
