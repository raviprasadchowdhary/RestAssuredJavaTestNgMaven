package com.herokuapp.restfulbooker;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PartialUpdateBookingTests extends BaseTest{
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void partialUpdateBooking(){
        Response responseCreateBooking = createBooking();
        int bookingId = responseCreateBooking.jsonPath().getInt("bookingid");

        Response response = partialUpdateBooking(bookingId);

        Assert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(response.jsonPath().getString("firstname"), "Conor");
        softAssert.assertEquals(response.jsonPath().getString("lastname"), "McGregor");
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), "2025-02-01");
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), "2025-02-10");
        softAssert.assertAll();

    }
}
