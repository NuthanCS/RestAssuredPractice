package automate;

import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class UploadAndDownloadFile {

    @Test
    public void uploadFile()
    {
        String attributeJson = "{\"name\":\"tempt.txt\",\"parent\":{\"id\":\"111\"}}";
        given().baseUri("https://postman-echo.com/").
                multiPart("file", new File("src/main/resources/temp.txt")).
                multiPart("attributes", attributeJson, "application/json").log().all().
                when().
                post("/post").
                then().
                assertThat().statusCode(200).log().all();
    }

    @Test
    public void downloadFile() throws IOException {

        byte[] bytes = given().baseUri("https://raw.githubusercontent.com").log().all().

                when().
                post("/appium/appium/master/packages/appium/sample-code/apps/ApiDemos-debug.apk").
                then().log().all().extract().response().asByteArray();

        //converting bytes to a file and we need to write into it a file
        OutputStream os =new FileOutputStream(new File("src/main/resources/ApiDemos-debug.apk"));
        os.write(bytes);
        os.close();
    }

}
