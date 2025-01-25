package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTests extends BaseTest{
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void getBookingAndVerifyResponseTest(){

        Response responseCreateBooking = createBooking();
        int bookingId = responseCreateBooking.jsonPath().getInt("bookingid");
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingId);

        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = new JsonPath(response.asString());
        softAssert.assertEquals(jsonPath.getString("firstname"), "Alex");
        softAssert.assertEquals(jsonPath.getString("lastname"), "Periera");
        softAssert.assertEquals(jsonPath.getInt("totalprice"), 100);
        softAssert.assertTrue(jsonPath.getBoolean("depositpaid"));
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2025-01-01");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2025-01-10");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Wrestling");
        softAssert.assertAll();

    }
}
