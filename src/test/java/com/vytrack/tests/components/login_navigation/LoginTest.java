package com.vytrack.tests.components.login_navigation;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.BrowserFactory;
import utilities.SeleniumUtils;

import java.util.concurrent.TimeUnit;

public class LoginTest {

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

    WebDriver driver;

    @BeforeMethod
    public void storeManagersetUp() {
        driver = BrowserFactory.getDriver("chrome");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");

    }

    @Test (priority=1)

//1. Login to Vytrack as a store manager
    public void storeManagerTestName() {
        //find username location
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
        //find password location
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        //find click button location
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.waitPlease(5);

//2. Verify name of the store manager is displayed on top right
        WebElement storeManager = driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Pearl Wuckert')]  "));

        String expectedText = "Pearl Wuckert";
        System.out.println(storeManager.getText());
        SeleniumUtils.waitPlease(5);

        Assert.assertTrue(storeManager.getText().contains(expectedText));
        SeleniumUtils.waitPlease(5);

//3. Verify Dashboard page is open
        String expectedTitle = "Dashboard";
        System.out.println(driver.getTitle());
        SeleniumUtils.waitPlease(5);

        Assert.assertTrue(driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);

//4. Log out
        storeManager.click();
        WebElement logOut = driver.findElement(By.xpath("//a[@class='no-hash'][contains(text(),'Logout')]"));
        logOut.click();
    }

    @Test (priority=2)
//6. Verify Dashboad page is open
    public void salesManagerTestName() {
        //find username location
        driver.findElement(By.id("prependedInput")).sendKeys("salesmanager252");
        //find password location
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        //find click button location
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.waitPlease(5);

        //7. A different name should be displayed on top right

        WebElement salesManager = driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Clifton Russel')]  "));

        String expectedText = "Pearl Wuckert";
        System.out.println(salesManager.getText());
        SeleniumUtils.waitPlease(5);

        Assert.assertFalse(salesManager.getText().contains(expectedText));
        SeleniumUtils.waitPlease(5);

//8. Log out
        salesManager.click();
        WebElement logOut = driver.findElement(By.xpath("//a[@class='no-hash'][contains(text(),'Logout')]"));
        logOut.click();
    }

    @Test (priority=3)

//9. Login to Vytrack as a driver
    public void DriverTestName() {

        //find username location
        driver.findElement(By.id("prependedInput")).sendKeys("User151");
        //find password location
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        //find click button location
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.waitPlease(5);

//10. Verify Dashboad/Quick Launchpad page is open

        WebElement header = driver.findElement(By.xpath("//h1[@class='oro-subtitle'][contains(text(),'Quick Launchpad')]"));
        String expectedTextHeader = "Quick Launchpad";
        Assert.assertTrue(header.getText().contains(expectedTextHeader));

        String expectedTitle = "Dashboard";
        Assert.assertTrue(driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);


//11. A different name should be displayed on top right
        WebElement driverName = driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Abdiel Howe')]"));

        String expectedName = "Clifton Russel";

        Assert.assertFalse(driverName.getText().contains(expectedName));
        SeleniumUtils.waitPlease(5);

//12. Log out
        driverName.click();
        WebElement logOut = driver.findElement(By.xpath("//a[@class='no-hash'][contains(text(),'Logout')]"));
        logOut.click();

    }
//TEST CASE: Login test (negative)
//2. Enter valid username and invalid password information
    @Test (priority=4)
    public void loginNegative(){

        String beforeLogInUrl=driver.getCurrentUrl();
        String beforeLogInTitle=driver.getTitle();
        //find username location
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
        //find password location
        driver.findElement(By.id("prependedInput2")).sendKeys("Cybertek123");
        //find click button location
//3. Click login button

        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.waitPlease(5);

//4. Message Invalid user name or password. should be displayed

        WebElement messageDisplayed=driver.findElement(By.xpath("//div[contains(text(),'Invalid user name or password')]"));

        String expectedmessageDisplayedText="Invalid user name or password";
        Assert.assertTrue(messageDisplayed.getText().contains(expectedmessageDisplayedText));
        SeleniumUtils.waitPlease(5);

//5. Page title and url should be same
        String afterLogInUrl=driver.getCurrentUrl();
        Assert.assertEquals(beforeLogInUrl,afterLogInUrl);
        SeleniumUtils.waitPlease(5);
        String afterLogInTitle=driver.getTitle();
        Assert.assertEquals(beforeLogInTitle,afterLogInTitle);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();

    }

}