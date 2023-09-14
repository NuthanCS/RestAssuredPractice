package pojoTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojoExample.workspacePojo.MainWorkspacePojo;
import pojoExample.workspacePojo.WorkspacePojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class WorkspaceTest {

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
      public void serializeAndDeserializeWorkspace(){

         MainWorkspacePojo mainWorkspacePojo =
                 new MainWorkspacePojo(new WorkspacePojo("myFourthWorkspace","personal","Rest assured created this"));

         MainWorkspacePojo deserializedMainWorkspacePojo = given().
                 body(mainWorkspacePojo).
                 when().
                 post("/workspaces").
                 then().extract().response().as(MainWorkspacePojo.class);

         assertThat(deserializedMainWorkspacePojo.getWorkspace().getName(),
                 equalTo(mainWorkspacePojo.getWorkspace().getName()));

         assertThat(deserializedMainWorkspacePojo.getWorkspace().getId(),matchesPattern("^[a-z0-9-]{36}$"));

     }

    @Test(dataProvider = "workspace")
    public void parameterisedWorkspaceUsingTestNgDataProvider(String name, String type, String description) {

        WorkspacePojo workspacePojo = new WorkspacePojo(name, type, description);
        MainWorkspacePojo mainWorkspacePojo = new MainWorkspacePojo(workspacePojo);

        MainWorkspacePojo deserializedMainWorkspacePojo = given().
                body(mainWorkspacePojo).
                when().
                post("/workspaces").
                then().extract().response().as(MainWorkspacePojo.class);

        assertThat(deserializedMainWorkspacePojo.getWorkspace().getName(),
                equalTo(mainWorkspacePojo.getWorkspace().getName()));

        assertThat(deserializedMainWorkspacePojo.getWorkspace().getId(), matchesPattern("^[a-z0-9-]{36}$"));
    }

        @DataProvider(name = "workspace")
        public Object[][] getWorkspace() {

            return new Object[][]{
                    {
                            "myFourthWorkspace", "personal", "Rest assured created this"
                    },
                    {
                            "myFourthWorkspace", "team", "Rest assured created this"
                    }

            };
        }

}