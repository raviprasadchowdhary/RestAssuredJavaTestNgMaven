package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTests {

    @Test
    public void getBookingAndVerifyResponseTest(){
        SoftAssert softAssert = new SoftAssert();

        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/1");

        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = new JsonPath(response.asString());

        softAssert.assertEquals(jsonPath.getString("firstname"), "James");
        softAssert.assertEquals(jsonPath.getString("lastname"), "Brown");
        softAssert.assertEquals(jsonPath.getInt("totalprice"), 111);
        softAssert.assertTrue(jsonPath.getBoolean("depositpaid"));
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast");

    }
}
