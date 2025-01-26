package com.herokuapp.restfulbooker;

import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HealthCheckTest extends BaseTest{

    @Test(enabled = true)
    public void healthCheckTest(){
        given(spec).
        when().
                get("ping").
        then().
                assertThat().
                statusCode(201);
    }

    @Test(enabled = true)
    public void headersAndCookiesTest(){
        Header someHeader = new Header("some_header_name","some_header_value");
        spec.header(someHeader);

        Cookie someCookie = new Cookie.Builder("some cookie name", "some cookie value").build();
        spec.cookie(someCookie);

        Response response = given(spec).cookie("cookie","cookie").header("header","header").log().all().when().get("ping");

        Headers headers = response.getHeaders();
        Header server = headers.get("Server");

        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertEquals(server.getValue(), "Cowboy");
    }
}
