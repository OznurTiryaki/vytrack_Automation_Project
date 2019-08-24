package com.vytrack.tests.smoke_tests.JDBC;

import com.vytrack.utilities.DBType;
import com.vytrack.utilities.DBUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EmployeesDBTest {

@Test
    public void countTest() throws SQLException {
    //Connect the oracle database
    //run followinf sql query
    //Select *from employees where job_id ='IT_PROG'
    //More than 0 recods shoul be returns


    String sql = "Select * From employees Where job_id='IT_PROG'";

    //maka connection with database

    DBUtility.establishConnection(DBType.ORACLE);


    //count row
    int rowsCount = DBUtility.getRowsCount(sql);

    Assert.assertTrue(rowsCount > 0);
    DBUtility.closeConnections();
}
     @Test
        public void nameTestByID() throws SQLException {

    //Connect to oracle database
         //employees fullname with Employee id 105 should be David Austin


         String sql="Select employee_id, first_name,last_name From employees WHERE  employee_id =105";

         DBUtility.establishConnection(DBType.ORACLE);

         List<Map<String,Object>> empData= DBUtility.runSQLQuery(sql);

         Assert.assertEquals( empData.get(0).get("FIRST_NAME"),"David");
         Assert.assertEquals( empData.get(0).get("LAST_NAME"),"Austin");


         DBUtility.closeConnections();

    }




}


