package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtils {

    static RequestSpecification reqSpec = RestAssured.given();
    //add all details related our requirement
    //reqSpec for Given, response for when, then
    static String endpoint;
    //
    static Response response;
    //all details about response

    public static void setEndpoint(String endpoint){
        RestAssuredUtils.endpoint = endpoint;
    }
    public static void setBody(String body){
        reqSpec = reqSpec.body(body);
    }
    public static void setHeader(String key, String value){
    reqSpec = reqSpec.header(key, value);
    }
    public static void get(){
        response = reqSpec.get(endpoint);
    }
    public static int getStatusCode(){
        return response.getStatusCode();
    }

    public static void post() {
        response = reqSpec.post(endpoint);
    }
    public static void put() {
        response = reqSpec.put(endpoint);

        }
        public static String getResponseField(String path){
            JsonPath jsonPath = new JsonPath(response.asString());
            return jsonPath.getString(path);

        }
}

