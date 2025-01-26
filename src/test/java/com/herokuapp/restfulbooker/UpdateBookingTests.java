package com.herokuapp.restfulbooker;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateBookingTests extends BaseTest{
    SoftAssert softAssert = new SoftAssert();

    @Test(enabled = true)
    public void updateBookingTest(){
        Response createBookingResponse = createBooking();
        int bookingId = createBookingResponse.jsonPath().getInt("bookingid");

        Response response = updateBooking(bookingId);

        Assert.assertEquals(response.getStatusCode(), 200);

        softAssert.assertEquals(response.jsonPath().getString("firstname"), "Alex2");
        softAssert.assertEquals(response.jsonPath().getString("lastname"), "Periera2");
        softAssert.assertEquals(response.jsonPath().getInt("totalprice"), 102);
        softAssert.assertEquals((String.valueOf(response.jsonPath().getBoolean("depositpaid"))), "false");
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), "2025-02-01");
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), "2025-02-10");
        softAssert.assertEquals(response.jsonPath().getString("additionalneeds"), "Wrestling2");
        softAssert.assertAll();
    }
}
