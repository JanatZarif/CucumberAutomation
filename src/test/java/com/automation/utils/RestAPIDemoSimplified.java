package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.automation.utils.RestAPIDemo.readDataFromFile;

public class RestAPIDemoSimplified {
    public static void main(String[] args) throws FileNotFoundException {
        String body = readDataFromFile("src/test/resources/data/create_user_body.json");

        // Create a blank container to store request
        RequestSpecification reqSpecs = RestAssured.given();

        // Set up or create a request
        reqSpecs = reqSpecs.baseUri("https://restful-booker.herokuapp.com");
        reqSpecs = reqSpecs.body(body);
        reqSpecs = reqSpecs.header("Content-Type","application/json");

        // Send request and store response
        Response response = reqSpecs.log().all().post("/booking");

        //Pojo classes we are using, so it is easier to parse json response
        //instead of using the JsonPath to retrieve filed one by one
        CreateBookingPojo createBookingPojo = response.as(CreateBookingPojo.class);
        System.out.println(createBookingPojo.getBooking().getBookingdates().getCheckin());

    }
    public static String readDataFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\\Z");
        return sc.next();
    }
}
