package com.vytrack.tests.api;

import com.vytrack.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.mortbay.util.ajax.JSON;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class APIDay3_GSON {

    //we convert from json to hashmap    ->  DE-SERIALIZATION:Convert Json—>  Java Object Using map
  //what is purpose--> you can modify data,
    @Test
    public void testWithJsonToHashMap() {

        Response response = given().accept(ContentType.JSON)

                .when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"employees/120");

              //  .when().get("http://18.205.114.183:1000/ords/hr/employees/120");

        HashMap<String, String> map = response.as(HashMap.class);

        System.out.println(map.keySet());

        System.out.println(map.values());

        Assert.assertEquals(map.get("employee_id"), 120);
        Assert.assertEquals(map.get("job_id"), "AC_MGR");
    }

  //  JSON TO LIST
     @Test

       public void convertJsonToListOfMaps() {

         Response response = given().accept(ContentType.JSON)
                 .when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"departments");

         //convert the response that contains department information into list of maps
       //we need multiple item right now, thats why we get List

         List<Map>listOfMaps=response.jsonPath().getList("items",Map.class);
         //every item is inside map, also we put all map inside list

         System.out.println(listOfMaps.get(0));

//                 {department_id=10, //for map we never make sure about order
//                 manager_id=200,
//                 department_name=Administration,
//                 links=[{rel=self,
//                 href=http://18.205.114.183:1000/ords/hr/departments/10}], location_id=1700}

       Assert.assertEquals(listOfMaps.get(0).get("department_name"),"Administration");


    }
}
