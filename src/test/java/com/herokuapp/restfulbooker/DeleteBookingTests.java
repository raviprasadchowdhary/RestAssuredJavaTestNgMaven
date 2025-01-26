package com.herokuapp.restfulbooker;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTests extends BaseTest{

    @Test(enabled = true)
    public void deleteBookingTest(){
        Response responseCreateBooking = createBooking();

        Response response = deleteBooking(responseCreateBooking.jsonPath().getInt("bookingid"));

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.getBody().asString(), "Created");

        Response responseGetBooking = getBooking(responseCreateBooking.jsonPath().getInt("bookingid"));

        Assert.assertEquals(responseGetBooking.getStatusCode(), 404);
        Assert.assertEquals(responseGetBooking.getBody().asString(), "Not Found");

    }
}
