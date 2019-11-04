package com.vytrack.tests.api;

import com.vytrack.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;

public class APIDay3_JsonPath {

     //JSON PATH TO MANIPULATE JSON --FOR MORE COMPLEX ASSERTIONS
    /*
    Given Accept type is Json
    When I send a GEt request to REST URL: http://18.205.114.183:1000/ords/hr/regions

    Then status code is 200
    And response content should be json
    And 4 regions should be returned
    And Americas is actually there

     */

    @Test
    public void testRegion ()throws URISyntaxException {
  //      URI uri = new URI("http://18.205.114.183:1000/ords/hr/regions");

        given().accept(ContentType.JSON)
        .when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"regions")
        .then().assertThat().statusCode(200)
        .and().assertThat().contentType(ContentType.JSON)
        .and().assertThat().body("items.region_id", hasSize(4))
         .and().assertThat().body("items.region_name", hasItem("Europe"))
           .and().assertThat().body("items.region_name", hasItems("Asia", "Americas"));
    }

   /*
    Given Accept type is Json
    And params are limit 100
    When I send get request to URL: http://18.205.114.183:1000/ords/hr/employee
     Then status code is 200
    And response content should be json
    And 100 employess data should be in json response body

     */

   @Test
   public void testWithQueryParametersAndList(){
       given().accept(ContentType.JSON)
       .and().params("limit",100)
       .when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"employee")
        .then().statusCode(200)
       .and().assertThat().contentType(ContentType.JSON)
       .and().assertThat().body("items.empoyees_id",hasSize(100));

   }

 /*
    Given Accept type is Json
    And params are limit 100
    And path params is 110
    When I send get request to URL: http://18.205.114.183:1000/ords/hr/employee
    Then status code is 200
    And response content should be json
    And 100 employess data should be returned :
         "employeeId :110
         first_name : John
         last_name : Chen
         email :JCHEN
     */


        @Test
        public void testWithPathParameter(){

            given().accept(ContentType.JSON)
                    .and().param("limit",100)
                    .and().pathParam("employee_id",110)
                    .baseUri(ConfigurationReader.getProperty("hrapp.baseresturl"))
                    .basePath("employees")
                    .pathParam("employee_id", 110)
                    .when()
                    .get("/{employee_id}")
                    .then().statusCode(200)
                    .and().assertThat().contentType(ContentType.JSON)
                    .and().assertThat().body("employee_id",equalTo(110),
                    "first_name",equalTo("John"),
                    "last_name",equalTo("Chen"),
                    "email",equalTo("JCHEN"));
        }
     /*
    Given Accept type is Json
    And params are limit =100
    When I send get request to URL: http://18.205.114.183:1000/ords/hr/employee
    Then status code is 200
    And response content should be json
    And all employeed id should be returned :

     */


        @Test
    public void testWithJsonPath(){

        Map<String,Integer> rParamMap= new HashMap <>() ;

        rParamMap.put("limit",100);  //limit allows you to set the number of objects returned in one page ?

            Response response=given().accept(ContentType.JSON)   //header
                              .and().params(rParamMap)    //querry /request parameter
                              .and().pathParams("employee_id",177)   //path parameter
                              .when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"employees/{employee_id}");

            JsonPath json= response.jsonPath(); //get json body to assign jsonPath object
            System.out.println(json.getInt("employee_id"));
            System.out.println(json.getString("last_name"));
            System.out.println(json.getString("job_id"));
            System.out.println(json.getString("salary"));
            System.out.println(json.getString("links[1].href")); //get specific element from array--
            // cunku links bir array icinde bir suru element var biz ikinci elementin href ini alicaz


            //assign all hrefs into a list of strings

            List<String> hrefs=json.getList("links.href");
            System.out.println(hrefs);

        }

          /*
    Given Accept type is Json
    And params are limit =100
    When I send get request to URL: http://18.205.114.183:1000/ords/hr/employee
    Then status code is 200
    And response content should be json
    And all employeed data should be returned :

     */
         @Test
          public void testJsonWithLists(){

             Map<String,Integer> rParamMap= new HashMap <>() ;

             rParamMap.put("limit",100);  //limit allows you to set the number of objects returned in one page ?

             Response response=given().accept(ContentType.JSON)   //header
                     .and().params(rParamMap)    //querry /request parameter
                     .when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"employees");

            Assert.assertEquals(response.statusCode(),200);

          // JsonPath json= response.jsonPath(); //get json body to assign jsonPath object

             //or

          //JsonPath json=new JsonPath(FilePath.json);--if we said FilePath, it takes all typep as a parameter from FilePath

             //or

           JsonPath json=new JsonPath(response.asString()) ;

           //get all employee id into an arraylist

           List <Integer>empIds= json.getList("items.employee_id")  ;  //"items.employee_id give me the all employee_id

           System.out.println(empIds);

           Assert.assertEquals(empIds.size(),100);

           //get all email id into an arraylist

           List <String> empEmail=json.getList("items.email");

           System.out.println(empEmail);

             Assert.assertEquals(empEmail.size(),100);

           //get all employee ids greate than 150

           List <String>empIdGr150= json.getList("items.findAll{it.employee_id > 150}.employee_id")  ;

           System.out.println(empIdGr150);

          //get all employee lastnames, whole salary is more than 700

          List < String> lastName=   json.getList("items.findAll{it.salary>700}.last_name");
             System.out.println(lastName);

         }



}
