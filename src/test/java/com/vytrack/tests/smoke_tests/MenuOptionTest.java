package com.vytrack.tests.smoke_tests;

import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import com.vytrack.utilities.BrowserFactory;

import java.util.concurrent.TimeUnit;

public class MenuOptionTest {


    WebDriver driver;

   String usernameLocation="prependedInput";
   String passwordLocation="prependedInput2";
   String enterClickLocation="_submit";

   String fleetLocation="(//li[@class='dropdown dropdown-level-1'])[1]";
   String vehicleLocation="(//span[@class='title title-level-2'][contains(text(),'Vehicles')])[1]";
   String customerLocator="(//li[@class='dropdown dropdown-level-1'])[2]";
   String accountLocation="//span[@class='title title-level-2'][contains(text(),'Accounts')]";
   String contactsLocator="//span[@class='title title-level-2'][contains(text(),'Contacts')]";
   String activitiesLocator="//span[@class='title title-level-1'][contains(text(),'Activities')] ";
   String calenderLocators="//span[contains(text(),'Calendar Events')]";

   String dashboardsLocation="//span[@class='title title-level-1'][contains(text(),'Dashboards')]"; //tab
   String dashboardLocator= "//span[@class='title title-level-2'][contains(text(),'Dashboard')][1]"; //module

   String salesLocator="//li[@class='dropdown dropdown-level-1'][3]";
   String opportunitiesLocator="//span[@class='title title-level-2'][contains(text(),'Opportunities')]";
   String headerLocation="//h1[@class='oro-subtitle']";

   String callsLocator="//span[contains(text(),'Calls')]";
   String calenderEventsLocators="//span[contains(text(),'Calendar Events')]";

    @BeforeGroups (groups= {"a"})   //we have two type of User so we do group for each one

    public void setUpUser1(){

        driver  = BrowserFactory.getDriver("chrome");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");

        //find username location
        WebElement username= driver.findElement(By.id(usernameLocation));
        BrowserUtils.sendKeys(driver,username,20,"User151");
        //public static void sendKeys(WebDriver driver, WebElement element, int timeout, String value){
        // new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));

        //find password location
        WebElement password= driver.findElement(By.id(passwordLocation));
        BrowserUtils.sendKeys(driver,password,20,"UserUser123");

        //find click button location
        WebElement click =driver.findElement(By.id (enterClickLocation));
        BrowserUtils.clickOn(driver, click,20);

    }

    //    2.Navigate	to	Fleet	àVehicles,	verify	page	title	Car	-Entities	-System	-Car	-Entities	-System,	page	name	All	Cars(updated)
    @Test (groups= {"a"})

    public void UserTest1(){ //we test

     //feature fleet location

        WebElement fleet1 =driver.findElement(By.xpath(fleetLocation));
        BrowserUtils.clickOn(driver,fleet1,20);

    //find location for Vehicle (it is under Fleet)
        WebElement vehicle = driver.findElement(By.xpath(vehicleLocation));
        BrowserUtils.clickOn(driver,vehicle,20);

        String expectedTitle="Car - Entities - System - Car - Entities - System";

        BrowserUtils.checkTitle(driver,expectedTitle,20);

    }

    //    3.Navigate	to	Customers	àAccounts,	verify	page	title	Accounts	-Customers,	verify	page	name	Accounts
    @Test (groups= {"a"})

    public void UserTest2(){

        //Customers locator
        WebElement customers = driver.findElement(By.xpath(customerLocator));
        BrowserUtils.clickOn(driver,customers,20);

        //account under Customer
        WebElement accounts = driver.findElement(By.xpath(accountLocation));
        BrowserUtils.clickOn(driver,accounts,20);

        String expectedTitle="Accounts - Customers";

        BrowserUtils.checkTitle(driver,expectedTitle,20);

    }
//    4.Navigate	to	Customers	àContacts,	verify	page	title	Contacts-Customers,	verify	page	name	Contacts(updated)

    @Test (groups= {"a"})

    public void UserTest3(){

        //Customers locator
        WebElement customers = driver.findElement(By.xpath(customerLocator));
        BrowserUtils.clickOn(driver,customers,20);

        //Contacts locator
        WebElement contacts = driver.findElement(By.xpath(contactsLocator));
        BrowserUtils.clickOn(driver,contacts,20);

        String expectedTitle="Contacts - Customers";
        BrowserUtils.checkTitle(driver,expectedTitle,20);

    }

//    5.Navigate	to	Activities	àCalendar	Events,	verify	page	title	Calendar	Events	-Activities,	page	name	Calendar	Events

    @Test (groups= {"a"})

    public void UserTest4() {

        //Activities locator
        WebElement activities = driver.findElement(By.xpath(activitiesLocator));
        BrowserUtils.clickOn(driver,activities,20);

        //Calender Events locator
        WebElement calender = driver.findElement(By.xpath(calenderLocators));
        BrowserUtils.clickOn(driver,calender,10);

        String expectedTitle="Calendar Events - Activities";
        BrowserUtils.checkTitle(driver,expectedTitle,20);
        driver.quit();
    }

    @BeforeGroups (groups= {"b"})   //we have two type of User so we do group for each one

    public void setUpStoreManager(){

        driver  = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");

        //find username location
        WebElement username= driver.findElement(By.id(usernameLocation));
        BrowserUtils.sendKeys(driver,username,20,"storemanager85");

        //find password location
        WebElement password= driver.findElement(By.id(passwordLocation));
        BrowserUtils.sendKeys(driver,password,20,"UserUser123");

        //find click button location
        WebElement click =driver.findElement(By.id (enterClickLocation));
        BrowserUtils.clickOn(driver, click,20);
        BrowserUtils.waitPlease(7);

    }
    // Navigate to Dashboards à Dashboard, verify page title Dashboard - Dashboards, verify page name Dashboard

    @Test (groups= {"b"})

    public void StoreManagerTest1() {


        //dashboards locator
        WebElement dashboards = driver.findElement(By.xpath(dashboardsLocation));
        BrowserUtils.clickOn(driver,dashboards,20);

        //dashboard locator

        WebElement dashboard=  driver.findElement(By.xpath(dashboardLocator)) ;
        BrowserUtils.clickOn(driver,dashboard,20);

        String expectedTitle="Dashboard - Dashboards";
        BrowserUtils.checkTitle(driver,expectedTitle,20);

    }

    //Navigate to FleetàVehicles, verify page title All - Car - Entities - System - Car - Entities - System, page name All Cars (updated)

    @Test (groups= {"b"})

    public void StoreManagerTest2() {

        //find location for Fleet
        WebElement fleet =driver.findElement(By.xpath(fleetLocation));
        BrowserUtils.clickOn(driver,fleet,20);

        //find location for Vehicle
        WebElement vehicle = driver.findElement(By.xpath(vehicleLocation));
        BrowserUtils.clickOn(driver,vehicle,20);

        String expectedTitle="All - Car - Entities - System - Car - Entities - System";
        BrowserUtils.checkTitle(driver,expectedTitle,20);
    }

    //  Navigate to Customersà Accounts, verify page title All - Accounts – Customers, verify page name All Accounts(updated)
    @Test (groups= {"b"})

    public void StoreManagerTest3() {

        //Customers locator
        WebElement customers = driver.findElement(By.xpath(customerLocator));
        BrowserUtils.clickOn(driver,customers,20);

        //account under Customer
        WebElement accounts = driver.findElement(By.xpath(accountLocation));
        BrowserUtils.clickOn(driver,accounts,20);


        String expectedTitle="All - Accounts - Customers";
        BrowserUtils.checkTitle(driver,expectedTitle,20);


    }
    //  Navigate to CustomersàContacts, verify page title All - Contacts - Customers, verify page name All Contacts (updated)
    @Test (groups= {"b"})

    public void StoreManagerTest4() {

        //Customers locator
        WebElement customers = driver.findElement(By.xpath(customerLocator));
        BrowserUtils.clickOn(driver,customers,20);

        //Contacts locator
        WebElement contacts = driver.findElement(By.xpath(contactsLocator));
        BrowserUtils.clickOn(driver,contacts,20);

        String expectedTitle="All - Contacts - Customers";
        BrowserUtils.checkTitle(driver,expectedTitle,20);


    }
    //Navigate to Sales à Opportunities, verify page title Open Opportunities - Opportunities - Sales, verify page name Open Opportunities

    @Test (groups= {"b"})

    public void StoreManagerTest5() {

        //sales locator
        WebElement sales= driver.findElement(By.xpath(salesLocator));
        BrowserUtils.clickOn(driver, sales,20);

        //Opportunies

        WebElement opportunies= driver.findElement(By.xpath(opportunitiesLocator));
        BrowserUtils.clickOn(driver,opportunies,20);

        //Verify title
        String expectedTitle="Open Opportunities - Opportunities - Sales";
        BrowserUtils.checkTitle(driver,expectedTitle,20);

        //verify Page Name
        WebElement opportuniesHeader=driver.findElement(By.xpath(headerLocation));
        String expectedPageName="Open Opportunities";
        Assert.assertEquals(opportuniesHeader.getText(),expectedPageName);


    }
    //Navigate to Activitiesa ̀Calls verify page title All - Calls - Activities, page name All Calls
    @Test (groups= {"b"})

    public void StoreManagerTest6() {

        //Activities locators

        WebElement activies= driver.findElement(By.xpath(activitiesLocator));
        BrowserUtils.clickOn(driver,activies,20);

        //Calls locators
        WebElement calls= driver.findElement(By.xpath(callsLocator));
        BrowserUtils.clickOn(driver,calls,20);

        //Verify title
        String expectedTitle="All - Calls - Activities";
        BrowserUtils.checkTitle(driver,expectedTitle,20);

        //verify Page Name
        WebElement callsHeader=driver.findElement(By.xpath(headerLocation));
        String expectedPageName="All Calls";
        Assert.assertEquals(callsHeader.getText(),expectedPageName);


    }
    //Navigate to ActivitiesàCalendar Events, verify page title Calendar Events - Activities, page name All Calendar Events (updated)
    @Test (groups= {"b"})

    public void StoreManagerTest7() {

     //Activities locators

        WebElement activies= driver.findElement(By.xpath(activitiesLocator));
        BrowserUtils.clickOn(driver,activies,20);

     //CalenderEvents locators

        WebElement calenderEvents= driver.findElement(By.xpath(calenderEventsLocators));
        BrowserUtils.clickOn(driver,calenderEvents,20);

     //Verify title
        String expectedTitle="All - Calendar Events - Activities";
        BrowserUtils.checkTitle(driver,expectedTitle,20);


     //verify Page Name
        WebElement eventsHeader=driver.findElement(By.xpath(headerLocation));
        String expectedPageName="All Calendar Events";
        Assert.assertEquals(eventsHeader.getText(),expectedPageName);
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


