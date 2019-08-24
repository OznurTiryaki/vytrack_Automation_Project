package com.vytrack.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DBUtility {


    private static Connection connection;

    private static Statement statement;

    private static ResultSet resultSet;

    private static int rowSCount;

    public static void establishConnection(DBType dbType) throws SQLException{

        switch(dbType){
            case ORACLE:
                connection = DriverManager.getConnection(ConfigurationReader.getProperty("oracledb.url"),
                                                         ConfigurationReader.getProperty("oracledb.user"),
                                                         ConfigurationReader.getProperty("oracledb.password"));
                 break;
            default:
               connection=null;
        }

    }

    public static int getRowsCount (String sql) throws SQLException {

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        resultSet = statement.executeQuery(sql);

        resultSet.last();

        return resultSet.getRow();

    }


    public static List <Map<String,Object>> runSQLQuery(String sql) throws SQLException {

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        resultSet = statement.executeQuery(sql);


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
        return list;
    }

    public static void closeConnections() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        }

    }
