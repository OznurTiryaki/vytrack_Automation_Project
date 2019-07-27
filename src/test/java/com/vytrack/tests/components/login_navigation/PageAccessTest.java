package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.vytrack.utilities.BrowserFactory;

import java.util.concurrent.TimeUnit;

public class PageAccessTest {

    WebDriver driver;

    String usernameLocation="prependedInput";
    String passwordLocation="prependedInput2";
    String enterClickLocation="_submit";

    String fleetLocation="(//li[@class='dropdown dropdown-level-1'])[1]";
    String vehicleContractsLocation="//span[@class='title title-level-2'][contains(text(),'Vehicle Contracts')]";
    String messageLocation="//div[@class='message'][contains(text(),'You do not have permission to perform this action.')]";

    @BeforeMethod
    public void storeManagersetUp() {
        driver = BrowserFactory.getDriver("chrome");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");
    }
//  1.  Login to Vytrack as a store manager

    @Test (priority = 1)
     public void storeManagerVehicleContracts(){

        //find username location

        WebElement username= driver.findElement(By.id(usernameLocation));
        BrowserUtils.sendKeys(driver,username,20,"storemanager85");

        //find password location
        WebElement password= driver.findElement(By.id(passwordLocation));
        BrowserUtils.sendKeys(driver,password,20,"UserUser123");

        //find click button location
        WebElement click =driver.findElement(By.id (enterClickLocation));
        BrowserUtils.clickOn(driver, click,20);

//2. Verify that you can access Vehicle contracts page

     //feature fleet location
        BrowserUtils.waitPlease(5);
        WebElement fleet1 =driver.findElement(By.xpath(fleetLocation));
        BrowserUtils.clickOn(driver,fleet1,20);

     //find Vehicle Contracts
        WebElement vehicleContracts=driver.findElement(By.xpath(vehicleContractsLocation));
        BrowserUtils.clickOn(driver,vehicleContracts,20);

        //verify title
        System.out.println(driver.getTitle());
      String expectedTitle= "All - Vehicle Contract - Entities - System - Car - Entities - System";
        BrowserUtils.checkTitle(driver,expectedTitle,20);

    }
    @Test (priority = 2)
    public void salesManagerVehicleContracts(){

        //find username location

        WebElement username= driver.findElement(By.id(usernameLocation));
        BrowserUtils.sendKeys(driver,username,20,"salesmanager252");

        //find password location
        WebElement password= driver.findElement(By.id(passwordLocation));
        BrowserUtils.sendKeys(driver,password,20,"UserUser123");

        //find click button location
        WebElement click =driver.findElement(By.id (enterClickLocation));
        BrowserUtils.clickOn(driver, click,20);

//2. Verify that you can access Vehicle contracts page

        //feature fleet location
        BrowserUtils.waitPlease(5);
        WebElement fleet1 =driver.findElement(By.xpath(fleetLocation));
        BrowserUtils.clickOn(driver,fleet1,20);

        //find Vehicle Contracts
        WebElement vehicleContracts=driver.findElement(By.xpath(vehicleContractsLocation));
        BrowserUtils.clickOn(driver,vehicleContracts,20);

        String expectedTitle= "All - Vehicle Contract - Entities - System - Car - Entities - System";
        BrowserUtils.checkTitle(driver,expectedTitle,20);


    }
    @Test (priority = 3)
    public void DriverVehicleContracts() {

        //find username location

        WebElement username= driver.findElement(By.id(usernameLocation));
        BrowserUtils.sendKeys(driver,username,20,"User152");

        //find password location
        WebElement password= driver.findElement(By.id(passwordLocation));
        BrowserUtils.sendKeys(driver,password,20,"UserUser123");

        //find click button location
        WebElement click =driver.findElement(By.id (enterClickLocation));
        BrowserUtils.clickOn(driver, click,20);

//2. Verify that you can access Vehicle contracts page

        //feature fleet location

        WebElement fleet1 =driver.findElement(By.xpath(fleetLocation));
        BrowserUtils.clickOn(driver,fleet1,20);

        //find Vehicle Contracts

        WebElement vehicleContracts=driver.findElement(By.xpath(vehicleContractsLocation));
        BrowserUtils.clickOn(driver,vehicleContracts,20);

        String actualmessage = driver.findElement(By.xpath(messageLocation)).getText();
        String expectedmessage = "You do not have permission to perform this action.";
        System.out.println(actualmessage);
        Assert.assertEquals(expectedmessage, actualmessage);
    }

    @AfterMethod
    public void tearDown () {
        driver.quit();
    }
}
