package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTests extends BaseTest{
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void getBookingAndVerifyResponseTest(){

        Response responseCreateBooking = createBooking();

        spec.pathParam("bookingId", responseCreateBooking.jsonPath().getInt("bookingid"));
        Response response = RestAssured.given(spec).get("booking/ {bookingId}");

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

    @Test
    public void getBookingAndVerifyXmlResponseTest(){

        Response responseCreateBooking = createBooking();

        spec.pathParam("bookingId", responseCreateBooking.jsonPath().getInt("bookingid"))
                .header("Accept","application/xml");
        Response response = RestAssured.given(spec).get("booking/ {bookingId}");

        Assert.assertEquals(response.getStatusCode(), 200);

        XmlPath xmlPath = new XmlPath(response.asString());
        softAssert.assertEquals(xmlPath.getString("booking.firstname"), "Alex");
        softAssert.assertEquals(xmlPath.getString("booking.lastname"), "Periera");
        softAssert.assertEquals(xmlPath.getInt("booking.totalprice"), 100);
        softAssert.assertTrue(xmlPath.getBoolean("booking.depositpaid"));
        softAssert.assertEquals(xmlPath.getString("booking.bookingdates.checkin"), "2025-01-01");
        softAssert.assertEquals(xmlPath.getString("booking.bookingdates.checkout"), "2025-01-10");
        softAssert.assertEquals(xmlPath.getString("booking.additionalneeds"),"Wrestling");
        softAssert.assertAll();

    }
}
