package com.vytrack.tests.api;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class APIDay5_POSTthenGET {

  /*  Given Content type and Accept type is Json
    When I post a new Employee with "1234" id
    Then Status code is 201
    And Resonse Json should contain Employee info
    When I send a Get request with "1234"id
    Then status code is 200
    And Employee JSon Response Data should match the posted JSON data
 {
            "employee_id": 100,
            "first_name": "Steven",
            "last_name": "King",
            "email": "SKING",
            "phone_number": "515.123.4567",
            "hire_date": "2003-06-17T04:00:00Z",
            "job_id": "AD_PRES",
            "salary": 24000,
            "commission_pct": null,
            "manager_id": null,
            "department_id": 90,
            "links": [
                {
                    "rel": "self",
                    "href": "http://18.205.114.183:1000/ords/hr/employees/100"
                }
  */

  @Test

    public void PostEmployeeThenGetEmployee(){

    int randomId= new Random().nextInt(99999);   //id icin random bir sayi istedik

    String url="http://18.205.114.183:1000/ords/hr/employees/";

    Map reqEmployee=new HashMap <>();  //verilen bilgilerin hepsini map e ekledik/Yapacagimiz sey java koddan json kod a donusturmek
    reqEmployee.put("employee_id", randomId);
    reqEmployee.put("first_name", "POSTNAME");  //  make sure every key need to be unique in map
    reqEmployee.put("last_name", "POSTLNAME");
    reqEmployee.put("hire_data", "512.244.5432");
    reqEmployee.put("phone_number", "2003-06-17T04:00:00Z");
    reqEmployee.put("job_id", "AD_PRES");
    reqEmployee.put("salary", "7000");
    reqEmployee.put( "commission_pct", null);
    reqEmployee.put("manager_id", null);
    reqEmployee.put("department_id", 90);


    //Given Content type and Accept type is Json

    Response response= given().accept(ContentType.JSON)
    .and() .contentType(ContentType.JSON)
    .and().body(reqEmployee)
    .when().post(url+randomId)  ;

    Assert.assertEquals(response.statusCode(),201);
    Map postResEmployee=response.body().as(Map.class);
    //Any response JSon should contain Employee info

    //we get from collection one by one
    for(Object key :reqEmployee.keySet()){   //we compare request body and post request body is same or different
      System.out.println(postResEmployee.get(key)+" <> "+reqEmployee.get(key));
      Assert.assertEquals(postResEmployee.get(key),reqEmployee.get(key));
    }

    //Whenever we sent it, we have to test it later from response
    response=given().accept(ContentType.JSON)
            .when().get(url+randomId);

    Assert.assertEquals(response.statusCode(),200);  //ekledikten sonra codunun  200 olmasi gerekiyor

    Map getResMap=response.body().as(Map.class);

    for(Object key :reqEmployee.keySet()){
      System.out.println(key+" :"+ reqEmployee.get(key)+ "<>" +getResMap.get(key));
      Assert.assertEquals(getResMap.get(key),reqEmployee.get(key));


    }


  }
    }

