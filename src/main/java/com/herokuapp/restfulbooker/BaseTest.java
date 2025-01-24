package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class BaseTest {

    protected Response createBooking(){
        //create json body
        JSONObject body = new JSONObject();
        body.put("firstname", "Alex");
        body.put("lastname", "Periera");
        body.put("totalprice",100 );
        body.put("depositpaid", true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2025-01-01");
        bookingdates.put("checkout", "2025-01-10");

        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Wrestling");

        //get response
        return RestAssured.given().body(body.toString()).contentType(ContentType.JSON).post("https://restful-booker.herokuapp.com/booking");
    }
}
