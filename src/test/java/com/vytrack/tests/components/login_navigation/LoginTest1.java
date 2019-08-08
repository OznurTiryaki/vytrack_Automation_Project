package com.vytrack.tests.components.login_navigation;

import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LoginTest1 extends TestBase {



//1. Login to Vytrack as a store manager
//2. Verify name of the store manager is displayed on top right
//3. Verify Dashboad page is open
//4. Log out
//5. Login to Vytrack as a sales manager
//6. Verify Dashboad page is open
//7. A different name should be displayed on top right
//8. Log out
//9. Login to Vytrack as a driver
//10. Verify Dashboad/Quick Launchpad page is open
//11. A different name should be displayed on top right

//  WebDriver driver;

    LoginPage loginPage=new LoginPage();



    String headerLocation="//h1[@class='oro-subtitle']";
    String messageDisplayedLocation="//div[contains(text(),'Invalid user name or password')]";



    @Test (priority=1)

//1. Login to Vytrack as a store manager

    public void storeManagerTestName() {


        //find username location
        String username = ConfigurationReader.getProperty("storemanagerusername");

        //find password location
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //find click button location

        loginPage.login(username, password);


//2. Verify name of the store manager is displayed on top right

        loginPage.verifyUser("storemanager");

 //3. Verify Dashboard page is open

        loginPage.verifyDashboard("Dashboard");

//4. Log out

       loginPage.logOut();

}
//
//    @Test (priority=2)
////6. Verify Dashboad page is open
//    public void salesManagerTestName() {
//
//        //find username location
//        String username= ConfigurationReader.getProperty("salesmanagerusername");
//
//        //find password location
//        String password=ConfigurationReader.getProperty("salesmanagerpassword");
//        //find click button location
//
//        loginPage.login(username,password);
//
//
//        //7. A different name should be displayed on top right
//
//        WebElement salesManager = driver.findElement(By.xpath(rightUserLocation));
//
//        String expectedText = "Clifton Russel";
//        BrowserUtils.waitPlease(5);
//        System.out.println(salesManager.getText());
//        BrowserUtils.waitPlease(5);
//        Assert.assertTrue(salesManager.getText().contains(expectedText));
//
////4. Log out
//        BrowserUtils.clickOn(driver, salesManager,20);
//        WebElement logOut = driver.findElement(By.xpath(logOutLocator));
//        BrowserUtils.clickOn(driver, logOut,20);
//
//    }
//
//    @Test (priority=3)
//
////9. Login to Vytrack as a driver
//    public void DriverTestName() {
//
//        //find username location
//        String username= ConfigurationReader.getProperty("truckdriverusername");
//
//        //find password location
//        String password=ConfigurationReader.getProperty("truckdriverpassword");
//        //find click button location
//
//        loginPage.login(username,password);
//
//
////10. Verify Dashboad/Quick Launchpad page is open
//
//        WebElement header = driver.findElement(By.xpath(headerLocation));
//        String expectedTextHeader = "Quick Launchpad";
//        //System.out.println(header.getText());
//        Assert.assertTrue(header.getText().contains(expectedTextHeader));
//
//        String expectedTitle = "Dashboard";
//        BrowserUtils.checkTitle(driver,expectedTitle,20);
//
////11. A different name should be displayed on top right
//        WebElement driverName = driver.findElement(By.xpath(rightUserLocation));
//
//        String expectedName = "Clifton Russel";
//        Assert.assertFalse(driverName.getText().contains(expectedName));
//        BrowserUtils.waitPlease(5);
//
////12. Log out
//        BrowserUtils.clickOn(driver, driverName,20);
//        WebElement logOut = driver.findElement(By.xpath(logOutLocator));
//        BrowserUtils.clickOn(driver, logOut,20);
//
//    }
//    //TEST CASE: Login test (negative)
////2. Enter valid username and invalid password information
//    @Test (priority=4)
//    public void loginNegative(){
//
//        String beforeLogInUrl=driver.getCurrentUrl();
//        String beforeLogInTitle=driver.getTitle();
//
//
//        //find username location
//        WebElement username= driver.findElement(By.id(usernameLocation));
//        BrowserUtils.sendKeys(driver,username,20,"storemanager85");
//
//        //find password location
//        WebElement password= driver.findElement(By.id(passwordLocation));
//        BrowserUtils.sendKeys(driver,password,20,"Cybertek123");
//
//        //find click button location
//        WebElement click =driver.findElement(By.id (enterClickLocation));
//        BrowserUtils.clickOn(driver, click,20);
//
////4. Message Invalid user name or password. should be displayed
//
//
//        WebElement messageDisplayed=driver.findElement(By.xpath(messageDisplayedLocation));
//
//        String expectedmessageDisplayedText="Invalid user name or password";
//        Assert.assertTrue(messageDisplayed.getText().contains(expectedmessageDisplayedText));
//        BrowserUtils.waitPlease(5);
//
////5. Page title and url should be same
//
//        String afterLogInUrl=driver.getCurrentUrl();
//        Assert.assertEquals(beforeLogInUrl,afterLogInUrl);
//        BrowserUtils.waitPlease(5);
//        String afterLogInTitle=driver.getTitle();
//        Assert.assertEquals(beforeLogInTitle,afterLogInTitle);
//    }

   @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}