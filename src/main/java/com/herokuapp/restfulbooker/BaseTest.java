package com.herokuapp.restfulbooker;

import com.herokuapp.restfulbooker.pojo.request.Booking;
import com.herokuapp.restfulbooker.pojo.request.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    //reference: https://restful-booker.herokuapp.com/apidoc/index.html
    RequestSpecification spec;

    @BeforeMethod
    public void setUp(){
        spec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com/").
                build();
    }

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
        return RestAssured.given(spec).body(body.toString()).contentType(ContentType.JSON).post("booking");
    }

    protected Response createBookingByPojo(){
        //create json body
        Bookingdates bookingDates = new Bookingdates("2025-01-01","2025-01-10");
        Booking booking = new Booking("Alex","Periera",100,true,bookingDates, "Wrestling");
        return RestAssured.given(spec).log().all().contentType(ContentType.JSON).body(booking.toString()).post("booking");
    }

    protected Response createBooking(String firstname, String lastname){
        //create json body
        JSONObject body = new JSONObject();
        body.put("firstname", firstname);
        body.put("lastname", lastname);
        body.put("depositpaid", true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2025-01-01");
        bookingdates.put("checkout", "2025-01-10");

        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Wrestling");

        //get response
        return RestAssured.given(spec).body(body.toString()).contentType(ContentType.JSON).post("booking");
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
        return RestAssured.given(spec).body(body.toString()).contentType(ContentType.JSON).auth().preemptive().basic("admin","password123").put("booking/" + bookingId);
    }

    protected Response partialUpdateBooking(int bookingId){
        JSONObject body = new JSONObject();
        body.put("firstname", "Conor");
        body.put("lastname", "McGregor");

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2025-02-01");
        bookingDates.put("checkout", "2025-02-10");
        body.put("bookingdates", bookingDates);

        return RestAssured.given(spec).body(body.toString()).contentType(ContentType.JSON).auth().preemptive().basic("admin","password123").patch("booking/" + bookingId);
    }

    protected Response deleteBooking(int bookingId){
        return RestAssured.given(spec).auth().preemptive().basic("admin", "password123").delete("booking/" + bookingId);
    }

    protected Response getBooking(int bookingId){
        return RestAssured.given().get("https://restful-booker.herokuapp.com/booking/" + bookingId);
    }
}
