package com.vytrack.tests.components.login_navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserFactory;
import utilities.SeleniumUtils;

import java.util.concurrent.TimeUnit;

public class PageAccessTest {

    WebDriver driver;

    @BeforeMethod
    public void storeManagersetUp() {
        driver = BrowserFactory.getDriver("chrome");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");
    }
//  1.  Login to Vytrack as a store manager

    @Test (priority = 1)
     public void storeManagerVehicleContracts(){
            //find username location
            driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
            //find password location
            driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
            //find click button location
            driver.findElement(By.id("_submit")).click();
            SeleniumUtils.waitPlease(5);

//2. Verify that you can access Vehicle contracts page

      //find location for Fleet
        WebElement fleet1 =driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[1]"));
        SeleniumUtils.waitPlease(5);
        fleet1.click();
        SeleniumUtils.waitPlease(5);

     //find Vehicle Contracts
        WebElement vehicleContracts=driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Vehicle Contracts')]"));
        vehicleContracts.click();
        SeleniumUtils.waitPlease(5);
        System.out.println(driver.getTitle());
      String expectedTitle= "All - Vehicle Contract - Entities - System - Car - Entities - System";
      Assert.assertTrue(driver.getTitle().contains(expectedTitle));

    }
    @Test (priority = 2)
    public void salesManagerVehicleContracts(){

        //find username location
        driver.findElement(By.id("prependedInput")).sendKeys("salesmanager252");
        //find password location
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        //find click button location
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.waitPlease(5);

//2. Verify that you can access Vehicle contracts page

        //find location for Fleet
        WebElement fleet1 =driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[1]"));
        SeleniumUtils.waitPlease(5);
        fleet1.click();
        SeleniumUtils.waitPlease(5);

        //find Vehicle Contracts
        WebElement vehicleContracts=driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Vehicle Contracts')]"));
        vehicleContracts.click();
        SeleniumUtils.waitPlease(5);
        System.out.println(driver.getTitle());
        String expectedTitle= "All - Vehicle Contract - Entities - System - Car - Entities - System";
        Assert.assertTrue(driver.getTitle().contains(expectedTitle));

    }
    @Test (priority = 3)
    public void DriverVehicleContracts() {

        //find username location
        driver.findElement(By.id("prependedInput")).sendKeys("User152");
        //find password location
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        //find click button location
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.waitPlease(5);

//2. Verify that you can access Vehicle contracts page

        //find location for Fleet
        WebElement fleet1 =driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[1]"));
        SeleniumUtils.waitPlease(5);
        fleet1.click();
        SeleniumUtils.waitPlease(5);


        //find Vehicle Contracts
        WebElement contract = driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Vehicle Contracts')]"));
        SeleniumUtils.waitPlease(3);
        contract.click();
        SeleniumUtils.waitPlease(3);

        String actualmessage = driver.findElement(By.xpath("//div[@class='message'][contains(text(),'You do not have permission to perform this action.')]")).getText();
        String expectedmessage = "You do not have permission to perform this action.";
        System.out.println(actualmessage);
        Assert.assertEquals(expectedmessage, actualmessage);
    }

    @AfterMethod
    public void tearDown () {
        driver.quit();
    }
}
