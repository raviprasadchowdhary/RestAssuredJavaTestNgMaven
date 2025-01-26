package com.herokuapp.restfulbooker;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTests extends BaseTest{

    @Test
    public void deleteBookingTest(){
        Response responseCreateBooking = createBooking();

        Response response = deleteBooking(responseCreateBooking.jsonPath().getInt("bookingid"));

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.getBody().asString(), "Created");
    }
}
