package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetBookingIdsTests extends BaseTest{

    @Test
    public void getBookingIdsWithoutFilterTest(){
        // Get response
        Response response = RestAssured.given(spec).get("booking");

        // verify response 200
        Assert.assertEquals(response.getStatusCode(), 200);

        // verify at least 1 booking id in response
        List <Integer> bookingidList = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingidList.isEmpty());

    }

    @Test
    public void getBookingIdsWithFilterTest(){
        createBooking("Alex","Periera");
        //add query parameter
        spec.queryParam("firstname", "Alex").queryParam("lastname", "Periera");

        // Get response
        Response response = RestAssured.given(spec).get("booking");

        // verify response 200
        Assert.assertEquals(response.getStatusCode(), 200);

        // verify at least 1 booking id in response
        List <Integer> bookingidList = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingidList.isEmpty());

    }
}
