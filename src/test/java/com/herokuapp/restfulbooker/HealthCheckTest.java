package com.herokuapp.restfulbooker;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HealthCheckTest extends BaseTest{

    @Test
    public void healthCheckTest(){
        given(spec).
        when().
                get("ping").
        then().
                assertThat().
                statusCode(201);
    }

    @Test
    public void headersAndCookiesTest(){
        Response response = given(spec).when().get("ping");

        Headers headers = response.getHeaders();
        Header server = headers.get("Server");

        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertEquals(server.getValue(), "Cowboy");
    }
}
