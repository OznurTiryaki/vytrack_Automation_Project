package com.vytrack.tests.api;

import com.vytrack.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.json.Json;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class APIDay4_PostMethod {

   /* given content type is Json
    And Accept type is Json
    When I send POST request to
    http://34.223.219.142:1212/ords/hr/regions
    with request body :
    {
        "region_id" : 5,
            "region_name" : "murodil's region"
    }
    Then status code should be 200
    And response body should match request body

    {"region_id" : 5,"region_name" : "murodil's region"}   */

    @Test
   public void postNewRegion () {
        String url = " http://34.223.219.142:1212/ords/hr/regions";

//        String requestJson = "{\n" +
//                "\"region_id \": 389,\n" +
//                "\"region_name\" : \"Karakush's region\"\n" +
//                "}";

        Map requestMap=new HashMap<>(); // bir map table yapip icine key ve value yu ekledik
        requestMap.put("region_id",3569);
        requestMap.put("region_name","Karakush's region");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().post(url);

        System.out.println(response.statusLine());
        response.prettyPrint();

        //then status code should be 201
        //and response body should match request body
        Assert.assertEquals(response.statusCode(),201);
        Map responseMap=response.body().as(Map.class);

        Assert.assertEquals(responseMap.get("region_id"),requestMap.get("region_id"));
    }

    @Test
    public void testUsingPOJO(){
   String url= ConfigurationReader.getProperty("hrapp.baseresturl")+"regions/";

   Region region=new Region();
   region.setRegion_id(555);
   region.setRegion_name("Nora's region");

   Response response= given().log().all().accept(ContentType.JSON)
                     .and().contentType(ContentType.JSON)
                     .and().body(region) //region objesini body  icin kullaniyoruz
                     .when().post(url);

        Assert.assertEquals(response.statusCode(),201);
        RegionResponse responseRegion=response.body().as(RegionResponse.class);

        //And response body should be match request body
        //region id and region name must match

        Assert.assertEquals(responseRegion.getRegion_id(),region.getRegion_id());
        Assert.assertEquals(responseRegion.getRegion_name(),region.getRegion_name());

    }
    }
