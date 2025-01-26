package com.herokuapp.restfulbooker;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateBookingTests extends BaseTest{
    SoftAssert softAssert = new SoftAssert();

    @Test(enabled = true)
    public void createBookingTest(){
        Response response = createBooking();

        //asserts
        Assert.assertEquals(response.getStatusCode(), 200);

        softAssert.assertEquals(response.jsonPath().getString("booking.firstname"), "Alex");
        softAssert.assertEquals(response.jsonPath().getString("booking.lastname"), "Periera");
        softAssert.assertEquals(response.jsonPath().getInt("booking.totalprice"), 100);
        softAssert.assertTrue(response.jsonPath().getBoolean("booking.depositpaid"));
        softAssert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"), "2025-01-01");
        softAssert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"), "2025-01-10");
        softAssert.assertEquals(response.jsonPath().getString("booking.additionalneeds"), "Wrestling");
        softAssert.assertAll();
    }
}
