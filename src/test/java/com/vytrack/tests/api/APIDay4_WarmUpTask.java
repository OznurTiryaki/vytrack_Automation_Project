package com.vytrack.tests.api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;

import java.util.*;

import static io.restassured.RestAssured.given;

public class APIDay4_WarmUpTask {

    /*
    Given Content type is Json
    And Limit is 10
    When I send request to Rest APi url: REST URL: http://18.205.114.183:1000/ords/hr/regions
    Then check status code is 2000
    Then I should see following data: (All region)
     */

    @Test
    public void getRegions () {
        Response response = given().accept(ContentType.JSON)
                .and().params("limit", 10)
                .when().get("http://18.205.114.183:1000/ords/hr/regions");

        Assert.assertEquals(response.statusCode(), 200);

        JsonPath json = new JsonPath(response.asString());

        List<Map> region = json.getList("items", Map.class);

        Map<Integer, String> expectedRegions = new HashMap<>();
        expectedRegions.put(1, "Europe");
        expectedRegions.put(2, "Americas");
        expectedRegions.put(3, "Asia");
        expectedRegions.put(4, "Middle East and Africa");


        for (int i = 0; i < region.size(); i++) {

            Assert.assertEquals(region.get(i).get("region_id"),i+1);  //region.get(i bize map i veriyor, get("region_id") bu isimda map icindeki id yi veriyor.
            Assert.assertEquals(region.get(i).get("region_name"),expectedRegions.get(i+1));


//        Assert.assertEquals(json.getInt("items[0].region_id"),1);
//        Assert.assertEquals(json.getString("items[0].region_name"),"Europe");
//
//        Assert.assertEquals(json.getInt("items[1].region_id"),2);
//        Assert.assertEquals(json.getString("items[1].region_name"),"Americas");
//
//        Assert.assertEquals(json.getInt("items[2].region_id"),3);
//        Assert.assertEquals(json.getString("items[2].region_name"),"Asia");
//
//        Assert.assertEquals(json.getInt("items[3].region_id"),4);
//        Assert.assertEquals(json.getString("items[3].region_name"),"Middle East and Africa");
        }
    }
    @Test
    public void getRegions2 (){

        Response response2= given().accept(ContentType.JSON)
                .and() .params("limit",10)
                .when().get("http://18.205.114.183:1000/ords/hr/regions")   ;

        Assert.assertEquals(response2.statusCode(),200);

        List<Map>regions=response2.jsonPath().getList("items",Map.class);

        System.out.println(regions);

       Map  <Integer, String> expectedRegions=new HashMap<>();
       expectedRegions.put(1,"Europe");
       expectedRegions.put(2,"Americas");
       expectedRegions.put(3,"Asia");
       expectedRegions.put(4,"Middle East and Africa");


       for(Integer regionId: expectedRegions.keySet()){

           System.out.println("looking for region: " +regionId);  //each loop takes each Map(key and value)

           for (Map map :regions){
               if(map.get("region_id")==regionId){  //burada da tek tek hepsinin i sini aliyoruz
                   Assert.assertEquals(map.get("region_name"),expectedRegions.get(regionId)); //region name ve id eslesiyor mu diye kontrol ediyoruz.
               }
           }
       }
    }


}
