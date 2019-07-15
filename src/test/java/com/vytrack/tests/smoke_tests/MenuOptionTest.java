package com.vytrack.tests.smoke_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BrowserFactory;
import utilities.SeleniumUtils;

import java.util.concurrent.TimeUnit;

public class MenuOptionTest {


    WebDriver driver;


    @BeforeGroups (groups= {"a"})   //we have two type of User so we do group for each one

    public void setUpUser1(){

        driver  = BrowserFactory.getDriver("chrome");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");

        //find username location
        driver.findElement(By.id("prependedInput")).sendKeys("User151");
        //find password location
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        //find click button location
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.waitPlease(5);

    }


    //    2.Navigate	to	Fleet	àVehicles,	verify	page	title	Car	-Entities	-System	-Car	-Entities	-System,	page	name	All	Cars(updated)
    @Test (groups= {"a"})

    public void UserTest1(){ //we test

        SeleniumUtils.waitPlease(5);
        //feature fleet location
        WebElement fleet1 =driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[1]"));
        SeleniumUtils.waitPlease(5);
        fleet1.click();


        SeleniumUtils.waitPlease(5);

        //find location for Fleet
        WebElement vehicle = driver.findElement(By.xpath("(//span[@class='title title-level-2'][contains(text(),'Vehicles')])[1]"));
        SeleniumUtils.waitPlease(5);
        vehicle.click();
        SeleniumUtils.waitPlease(5);

        //find location for Vehicle (it is under Fleet)
        String expectedTitle="Car - Entities - System - Car - Entities - System";
        System.out.println( driver.getTitle());

        SeleniumUtils.waitPlease(5);

        Assert.assertTrue( driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);

    }

    //    3.Navigate	to	Customers	àAccounts,	verify	page	title	Accounts	-Customers,	verify	page	name	Accounts
    @Test (groups= {"a"})

    public void UserTest2(){
        SeleniumUtils.waitPlease(5);
        //Customers locator
        WebElement customers = driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[2]"));
        customers.click();
        SeleniumUtils.waitPlease(5);

        //account under Customer
        WebElement accounts = driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Accounts')]"));
        accounts.click();

        String expectedTitle="Accounts - Customers";
        System.out.println( driver.getTitle());

        SeleniumUtils.waitPlease(5);

        Assert.assertTrue( driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);



    }
//    4.Navigate	to	Customers	àContacts,	verify	page	title	Contacts-Customers,	verify	page	name	Contacts(updated)

    @Test (groups= {"a"})

    public void UserTest3(){
        SeleniumUtils.waitPlease(5);
        //Customers locator
        WebElement customers = driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[2]"));
        customers.click();
        SeleniumUtils.waitPlease(5);

        //Contacts locator
        WebElement contacts = driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Contacts')]"));
        contacts.click();
        SeleniumUtils.waitPlease(5);

        String expectedTitle="Contacts - Customers";
        System.out.println( driver.getTitle());


        Assert.assertTrue( driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);

    }

//    5.Navigate	to	Activities	àCalendar	Events,	verify	page	title	Calendar	Events	-Activities,	page	name	Calendar	Events

    @Test (groups= {"a"})

    public void UserTest4() {
        SeleniumUtils.waitPlease(5);
        //Activities locator
        WebElement activities = driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Activities')] "));
        activities.click();
        SeleniumUtils.waitPlease(5);

        //Calender Events locator
        WebElement calender = driver.findElement(By.xpath("//span[contains(text(),'Calendar Events')]"));
        calender.click();
        SeleniumUtils.waitPlease(5);

        String expectedTitle="Calendar Events - Activities";
        System.out.println( driver.getTitle());

        Assert.assertTrue( driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);
        driver.quit();
    }

    @BeforeGroups (groups= {"b"})   //we have two type of User so we do group for each one

    public void setUpStoreManager(){
        SeleniumUtils.waitPlease(5);

        driver  = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");

        //find username location
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
        //find password location
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        //find click button location
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.waitPlease(5);

    }
    // Navigate to Dashboards à Dashboard, verify page title Dashboard - Dashboards, verify page name Dashboard
    @Test (groups= {"b"})

    public void StoreManagerTest1() {
        SeleniumUtils.waitPlease(5);
        //dashboards locator
        WebElement dashboards = driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Dashboards')] "));
        dashboards.click();
        SeleniumUtils.waitPlease(5);

        //dashboard locator
        WebElement dashboard=  driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Dashboard')][1]")) ;
        dashboard.click();
        SeleniumUtils.waitPlease(5);

        String expectedTitle="Dashboard - Dashboards";
        System.out.println( driver.getTitle());

        Assert.assertTrue( driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);

    }

    //Navigate to FleetàVehicles, verify page title All - Car - Entities - System - Car - Entities - System, page name All Cars (updated)
    @Test (groups= {"b"})

    public void StoreManagerTest2() {

        SeleniumUtils.waitPlease(5);

        //find location for Fleet
        WebElement fleet1 =driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[1]"));
        SeleniumUtils.waitPlease(5);
        fleet1.click();
        SeleniumUtils.waitPlease(5);

        //find location for Vehicle
        WebElement vehicle = driver.findElement(By.xpath("(//span[@class='title title-level-2'][contains(text(),'Vehicles')])[1]"));
        SeleniumUtils.waitPlease(5);
        vehicle.click();
        SeleniumUtils.waitPlease(5);

        String expectedTitle="All - Car - Entities - System - Car - Entities - System";
        System.out.println( driver.getTitle());

        SeleniumUtils.waitPlease(5);

        Assert.assertTrue( driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);
    }

    //  Navigate to Customersà Accounts, verify page title All - Accounts – Customers, verify page name All Accounts(updated)
    @Test (groups= {"b"})

    public void StoreManagerTest3() {

        SeleniumUtils.waitPlease(5);
        //Customers locator
        WebElement customers = driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[2]"));
        customers.click();
        SeleniumUtils.waitPlease(5);

        //account under Customer
        WebElement accounts = driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Accounts')]"));
        accounts.click();
        SeleniumUtils.waitPlease(5);

        String expectedTitle="All - Accounts - Customers";
        System.out.println( driver.getTitle());

        SeleniumUtils.waitPlease(5);

        Assert.assertTrue( driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);

    }
    //  Navigate to CustomersàContacts, verify page title All - Contacts - Customers, verify page name All Contacts (updated)
    @Test (groups= {"b"})

    public void StoreManagerTest4() {
        SeleniumUtils.waitPlease(5);
        //Customers locator
        WebElement customers = driver.findElement(By.xpath("(//li[@class='dropdown dropdown-level-1'])[2]"));
        customers.click();
        SeleniumUtils.waitPlease(5);

        //Contacts under Customer
        WebElement accounts = driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Contacts')]"));
        accounts.click();
        SeleniumUtils.waitPlease(5);

        String expectedTitle="All - Contacts - Customers";
        System.out.println( driver.getTitle());

        SeleniumUtils.waitPlease(5);

        Assert.assertTrue( driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);

    }
    //Navigate to Sales à Opportunities, verify page title Open Opportunities - Opportunities - Sales, verify page name Open Opportunities

    @Test (groups= {"b"})

    public void StoreManagerTest5() {
        SeleniumUtils.waitPlease(5);
        //sales locator
        WebElement sales= driver.findElement(By.xpath("//li[@class='dropdown dropdown-level-1'][3]"));
        sales.click();
        SeleniumUtils.waitPlease(5);

        //Opportunies
        WebElement opportunies= driver.findElement(By.xpath("//span[@class='title title-level-2'][contains(text(),'Opportunities')]"));
        opportunies.click();
        SeleniumUtils.waitPlease(5);

        String expectedTitle="Open Opportunities - Opportunities";
        System.out.println( driver.getTitle());

        SeleniumUtils.waitPlease(5);

        Assert.assertTrue( driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);

    }
    //Navigate to Activitiesa ̀Calls verify page title All - Calls - Activities, page name All Calls
    @Test (groups= {"b"})

    public void StoreManagerTest6() {

        SeleniumUtils.waitPlease(5);

        //Activities locators
        WebElement activies= driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Activities')]"));
        activies.click();
        SeleniumUtils.waitPlease(5);

        //Calls locators
        WebElement calls= driver.findElement(By.xpath("//span[contains(text(),'Calls')]"));
        calls.click();
        SeleniumUtils.waitPlease(5);

        String expectedTitle="All - Calls - Activities";
        System.out.println( driver.getTitle());

        SeleniumUtils.waitPlease(5);

        Assert.assertTrue( driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);


    }
    //Navigate to ActivitiesàCalendar Events, verify page title Calendar Events - Activities, page name All Calendar Events (updated)
    @Test (groups= {"b"})

    public void StoreManagerTest7() {

        SeleniumUtils.waitPlease(5);
        //Activities locators
        WebElement activies= driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Acti')]"));
        activies.click();
        SeleniumUtils.waitPlease(5);

        //CalenderEvents locators
        WebElement CalenderEvents= driver.findElement(By.xpath("//span[contains(text(),'Calendar Events')]"));
        CalenderEvents.click();
        SeleniumUtils.waitPlease(5);

        String expectedTitle="All - Calendar Events - Activities";
        System.out.println( driver.getTitle());

        SeleniumUtils.waitPlease(5);

        Assert.assertTrue( driver.getTitle().contains(expectedTitle));
        SeleniumUtils.waitPlease(5);
        driver.quit();
    }
//    @AfterGroups (groups= {"a"})
//
//    public void tearDown(){
//        driver.quit();
//    }
//    @AfterGroups (groups= {"b"})
//
//    public void tearDown1(){
//        driver.quit();
//    }

}


