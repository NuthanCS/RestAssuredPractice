package handlngCookies;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieUsage {

    @BeforeClass
    public void execute() {
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setRelaxedHTTPSValidation().
                setBaseUri("https://localhost:8443/").build();
    }

    @Test
    public void sendingCookie()
    {
        //fetching cookie from the server using sessionFilter class
        SessionFilter filter = new SessionFilter();

        given().auth().form("dan", "dan123",
                new FormAuthConfig("/signin","txtUsername","txtPassword").
                        withAdditionalField("_csrf")).
                filter(filter).
                log().all().
                when().
                get("/login").
                then().
                log().all().assertThat().statusCode(200);

        given().
                //Sending that fetched cookie as a part of below request
                cookie("JSESSIONID", filter.getSessionId()).
                log().all().
                when().
                get("/profile/index").
                then().
                log().all().assertThat().
                body("html.body.div.p",equalTo("This is User Profile\\Index. Only authenticated people can see this"));

    }

    @Test
    public void sendingCookieUsingCookieBuilder()
    {
        //fetching cookie from the server using sessionFilter class
        SessionFilter filter = new SessionFilter();

        given().auth().form("dan", "dan123",
                        new FormAuthConfig("/signin","txtUsername","txtPassword").
                                withAdditionalField("_csrf")).
                filter(filter).
                log().all().
                when().
                get("/login").
                then().
                log().all().assertThat().statusCode(200);

        Cookie cookie = new Cookie.Builder("JSESSIONID", filter.getSessionId()).
                //these methods are optional
                setSecured(true).setHttpOnly(true).setComment("My Cookie").build();

        given().
                //Sending that fetched cookie as a part of below request
                        cookie(cookie).
                log().all().
                when().
                get("/profile/index").
                then().
                log().all().assertThat().
                body("html.body.div.p",equalTo("This is User Profile\\Index. Only authenticated people can see this"));

    }

    @Test
    public void sendMultipleCookies()
    {
        SessionFilter filter = new SessionFilter();

        given().auth().form("dan","dan123",
                new FormAuthConfig("/signin","txtUsername","txtPassword").
                        withAdditionalField("_csrf")).
                filter(filter).log().all().
                when().get("/login").
                then().log().all().assertThat().statusCode(200);

        Cookie cookie = new Cookie.Builder("JSESSIONID", filter.getSessionId()).setSecured(true).build();
        Cookie cookie1 = new Cookie.Builder("dummyCookie", "dummyValue").build();

        Cookies cookies = new Cookies(cookie,cookie1);

        given().
                cookies(cookies).log().all().
                when().
                get("/profile/index").
                then().
                log().all().assertThat().
                body("html.body.div.p",equalTo("This is User Profile\\Index. Only authenticated people can see this"));
    }

    @Test
    public void fetchSingleCookie()
    {
        Response response = given().log().all().
                when().
                get("/profile/index").
                then().
                log().all().extract().response();

        //fetch only cookie value not attributes
        System.out.println("cookie value : "+response.getCookie("JSESSIONID"));

        //fetch cookie value along with the attributes
        System.out.println("cookie value with attributes : "+response.getDetailedCookie("JSESSIONID"));
    }

    @Test
    public void fetchMultipleCookies()
    {
        Response response = given().log().all().
                when().
                get("/profile/index").
                then().
                log().all().extract().response();

        Map<String, String> cookies = response.getCookies();
        for(Map.Entry<String, String> entry : cookies.entrySet())
        {
            System.out.println("cookie name : "+entry.getKey());
            System.out.println("cookie value : "+entry.getValue());
        }


        Cookies cookies1 = response.getDetailedCookies();
        List<Cookie> cookieList = cookies1.asList();

        for(Cookie cookie : cookieList)
        {
            System.out.println("Cookie with attributes : "+cookie.toString());
        }
    }

}
