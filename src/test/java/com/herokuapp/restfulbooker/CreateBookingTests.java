package com.herokuapp.restfulbooker;


import com.herokuapp.restfulbooker.pojo.request.Booking;
import com.herokuapp.restfulbooker.pojo.request.Bookingdates;
import com.herokuapp.restfulbooker.pojo.response.Bookingid;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateBookingTests extends BaseTest{
    SoftAssert softAssert = new SoftAssert();

    @Test
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

    @Test
    public void createBookingWithPojoSerializationTest(){
        Response response = createBookingByPojo();

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

    @Test
    public void createBookingWithPojoDeserializationTest(){
//        Response response = createBookingByPojo();

        Bookingdates bookingDates = new Bookingdates("2025-01-01","2025-01-10");
        Booking booking = new Booking("Alex","Periera",100,true,bookingDates, "Wrestling");
        Response response = RestAssured.given(spec).log().all().contentType(ContentType.JSON).body(booking.toString()).post("booking");

        Bookingid bookingid = response.as(Bookingid.class);

        //asserts
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(bookingid.getBooking().toString(), booking.toString());
    }
}
