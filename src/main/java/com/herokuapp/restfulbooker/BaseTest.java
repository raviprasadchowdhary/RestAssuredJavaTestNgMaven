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

    protected Response updateBooking(int bookingId){
        //create json body
        JSONObject body = new JSONObject();
        body.put("firstname", "Alex2");
        body.put("lastname", "Periera2");
        body.put("totalprice",102 );
        body.put("depositpaid", false);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2025-02-01");
        bookingdates.put("checkout", "2025-02-10");

        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Wrestling2");

        //get response
        return RestAssured.given().body(body.toString()).contentType(ContentType.JSON).auth().preemptive().basic("admin","password123").put("https://restful-booker.herokuapp.com/booking/" + bookingId);
    }

    protected Response partialUpdateBooking(int bookingId){
        JSONObject body = new JSONObject();
        body.put("firstname", "Conor");
        body.put("lastname", "McGregor");

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2025-02-01");
        bookingDates.put("checkout", "2025-02-10");
        body.put("bookingdates", bookingDates);

        return RestAssured.given().body(body.toString()).contentType(ContentType.JSON).auth().preemptive().basic("admin","password123").patch("https://restful-booker.herokuapp.com/booking/" + bookingId);
    }

    protected Response deleteBooking(int bookingId){
        return RestAssured.given().auth().preemptive().basic("admin", "password123").delete("https://restful-booker.herokuapp.com/booking/" + bookingId);
    }
}
