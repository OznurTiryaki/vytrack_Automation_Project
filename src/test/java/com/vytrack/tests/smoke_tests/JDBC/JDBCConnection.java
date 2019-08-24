package com.vytrack.tests.smoke_tests.JDBC;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCConnection {


    String oracleDbUrl = "jdbc:oracle:thin:@ec2-18-223-131-8.us-east-2.compute.amazonaws.com:1521:xe";
    //put your sql url from amazon
    // for connect to database--> jdbc:oracle
    String oracleDBUserName = "hr";
    String oracleDBPassword = "hr";

    @Test(enabled = true)
    public void oracleJDBC() throws SQLException {

        // Connection -> helps connect to database
        Connection connection = DriverManager.getConnection(oracleDbUrl, oracleDBUserName, oracleDBPassword);

        // Statement -> helps write sql query and execute
        // Statement statement=connection.createStatement();

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //ResultSet.CONCUR_UPDATABLE --its mean if they update , I dont care later

        //ResultSet -> data that came from database will be stored in ResultSet format.
        ResultSet resultSet = statement.executeQuery("Select * From countries");
        //we want o get that info row by row


//       resultSet.next();
//
//       System.out.println(resultSet.getString(1));
//       System.out.println(resultSet.getString("country_name"));
//       System.out.println(resultSet.getInt("region_id"));
//
//      resultSet.next();
//
//       System.out.println(resultSet.getString(1));
//       System.out.println(resultSet.getString("country_name"));
//       System.out.println(resultSet.getInt("region_id"));
//
//
//          resultSet.next();
//        System.out.println(resultSet.getString(1));
//        System.out.println(resultSet.getString("country_name"));
//        System.out.println(resultSet.getInt("region_id"));

//        while(resultSet.next()){
//            System.out.println(resultSet.getString(1) + " - " + resultSet.getString("country_name")
//                    + " - " +  (resultSet.getInt("region_id")));
//        }
//          //boylece tum datayi cekmis oluyoruz databaseden


        int rowCount = resultSet.getRow();
        System.out.println("Number of rows: " + rowCount); //Number of rows: 25

        //last() -> goes to last row--if we use the last one, we cannot print it with loop again, we need to go at beginning.
        resultSet.last();

        resultSet.beforeFirst();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " - " + resultSet.getString("country_name")
                    + " - " + (resultSet.getInt("region_id")));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test

    public void ojdbcMetadata() throws Exception {


        Connection connection = DriverManager.getConnection(oracleDbUrl, oracleDBUserName, oracleDBPassword);

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        String sql = "Select employee_id,last_name, job_id, salary from employees";

        ResultSet resultSet = statement.executeQuery(sql);

        //Database metadata

        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User: " + dbMetadata.getUserName());  //User: HR

        System.out.println("User: " + dbMetadata.getDatabaseProductName());  //User: Oracle


        //ResultSet metadata

        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        System.out.println("Columns count: " + rsMetaData.getColumnCount()); //Columns count: 4

        System.out.println("Columns name: " + rsMetaData.getColumnName(1)); //Columns name: EMPLOYEE_ID

        //print all column names using loop

        for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
            System.out.println(i + " -->  " + rsMetaData.getColumnName(i));

//              0 -->  __Oracle_JDBC_interal_ROWID__
//              1 -->  EMPLOYEE_ID
//              2 -->  LAST_NAME
//              3 -->  JOB_ID

        }
        //Throw result set into a list of MAps
        //Create a List of Maps

        //every row item for list in table
        //all column item inside map

        List<Map<String, Object>> list = new ArrayList<>();

        ResultSetMetaData rsMdata = resultSet.getMetaData();

        int colCount = rsMdata.getColumnCount();

        while (resultSet.next()) {  //next ile rowlari ilerliyoruz

            //her bir row asagi indiginde bir map objesi olusturuyor
            Map<String, Object> rowMap = new HashMap<>();

            //loop ile table daki column name ve objectlerini key ve value olarak map e ekliyor
            for (int col = 1; col <= colCount; col++) {
                rowMap.put(rsMdata.getColumnName(col), resultSet.getObject(col));
                //inside all map object  ex:   JOB_ID=AC_ACCOUNT, EMPLOYEE_ID=206, SALARY=8300, LAST_NAME=Gietz}]

            }
            //loop ile element ekledigimiz map objectlerini list e ekliyor

            list.add(rowMap);

        }
        System.out.println(list);

        //Task: print all employee ids from a of maps

        for(Map<String,Object > emp:list){
            System.out.println(emp.get("EMPLOYEE_ID"));
        }

        resultSet.close();

    }



}
