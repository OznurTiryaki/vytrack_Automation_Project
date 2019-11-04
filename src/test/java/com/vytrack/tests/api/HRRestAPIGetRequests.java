package com.vytrack.tests.api;

import io.restassured.config.MatcherConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.*;

public class HRRestAPIGetRequests {


    /*When I send a GET request to REST URl http://18.205.114.183:1000/ords/hr/employees
    Then response status should be 200

     */

    @Test
    public void simpleGet(){

        when().get("http://18.205.114.183:1000/ords/hr/employees").then().statusCode(200);
    }


    /*When I send a GET request to REST URl http://18.205.114.183:1000/ords/hr/countries
        then I should see JSON Respnse
         */
    @Test
    public void sprintResonse() {
        when().get("http://18.205.114.183:1000/ords/hr/countries").body().prettyPrint();

//            "items": [
//            {
//                "country_id": "AR",
//                    "country_name": "Argentina",
//                    "region_id": 2,
//                    "links": [
//                {
//                    "rel": "self",
//                        "href": "http://18.205.114.183:1000/ords/hr/countries/AR"
//                }
//            ]     it print all country like this

            }

    /*When I send a GET request to REST URl http://18.205.114.183:1000/ords/hr/countries/US
       AND Accept type us "application/json"
       Then response status should be 200
   */
    @Test
    public void getWithHeaders() {

        with().accept(ContentType.JSON).
        when().get("http://18.205.114.183:1000/ords/hr/countries/US").
        then().statusCode(200);

    }
    /*When I send a GET request to REST URl http://18.205.114.183:1000/ords/hr/employees/1234
   Then response status should be 404--CLints false
   */
    @Test
    public void Get404() {

//    when().get("http://18.205.114.183:1000/ords/hr/employees/1234")
//            .then().statusCode(404);

        Response response=when().get("http://18.205.114.183:1000/ords/hr/employees/1234");

        Assert.assertEquals(response.statusCode(),404);  //statusune bakiyoru

        Assert.assertTrue(response.asString().contains("Not Found"));  //not found yazip yazmadigina bakiyoruz.

        response.prettyPrint();

    }

    /*When I send a GEt request to RESST URL: http://18.205.114.183:1000/ords/hr/employees/100

    And Accept type is json
    Then status code is 200
    And response content shoul be json

    WITH, WHEN, GET, ASSERTTHAT, ACCEPT,CONTENTTYPE

     */

    @Test
    public void VerifyContentTypeWithAssertThat() {
        given().accept(ContentType.JSON)
        .when().get("http://18.205.114.183:1000/ords/hr/employees/100")
        .then().assertThat().statusCode(200)
        .and().contentType(ContentType.JSON);  //ContentType.XML yazarsak fail eder turlu content bize json olarak geri donuyor.
    }

     /*Given Accept type is JSON

     When I send a GEt request to REST URL: http://18.205.114.183:1000/ords/hr/employees/100

    Then status code is 200
    And response content should be json
    And first name should be "Steven"
    and emloyee id 100

    WITH, WHEN, GET, ASSERTTHAT, ACCEPT,CONTENTTYPE

     */
     @Test
     public void VerifyFirstName() throws URISyntaxException {

         URI uri=new URI("http://18.205.114.183:1000/ords/hr/employees/100");
         given().accept(ContentType.JSON)
        .when().get(uri)
        .then().assertThat().statusCode(200)
         .and().contentType(ContentType.JSON)
         .and().assertThat().body("first_name", Matchers.equalTo("Steven"))
         .and().assertThat().body("employee_id",Matchers.equalTo(100));
     }



}
