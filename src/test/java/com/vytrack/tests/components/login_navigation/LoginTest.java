package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {



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

   String usernameLocation="prependedInput";
   String passwordLocation="prependedInput2";
    String enterClickLocation="_submit";

    String logOutLocator="//a[@class='no-hash'][contains(text(),'Logout')]";

    String rightUserLocation="//a[@class='dropdown-toggle']";
    String salesManagerLocation="//a[@class='dropdown-toggle'][contains(text(),'Clifton Russel')]";

    String headerLocation="//h1[@class='oro-subtitle']";
    String messageDisplayedLocation="//div[contains(text(),'Invalid user name or password')]";

//    @BeforeMethod
//    public void storeManagersetUp() {
//
//        driver = BrowserFactory.getDriver("chrome");
//
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.get("http://qa2.vytrack.com/user/login");
//
//    }

    @Test (priority=1)

//1. Login to Vytrack as a store manager

    public void storeManagerTestName() {

        //find username location
        WebElement username= driver.findElement(By.id(usernameLocation));
        BrowserUtils.sendKeys(driver,username,20,"storemanager85");

        //find password location
        WebElement password= driver.findElement(By.id(passwordLocation));
        BrowserUtils.sendKeys(driver,password,20,"UserUser123");

        //find click button location
        WebElement click =driver.findElement(By.id (enterClickLocation));
        BrowserUtils.clickOn(driver, click,20);



//2. Verify name of the store manager is displayed on top right

        WebElement storeManager = driver.findElement(By.xpath(rightUserLocation));


        String expectedText = "Pearl Wuckert";
        BrowserUtils.waitPlease(5);    //buradaki sure sikintisi uzerine calis
        System.out.println(storeManager.getText());
        BrowserUtils.waitPlease(5);
        Assert.assertTrue(storeManager.getText().contains(expectedText));


//3. Verify Dashboard page is open
        String expectedTitle = "Dashboard";
        BrowserUtils.checkTitle(driver,expectedTitle,20);
        BrowserUtils.waitPlease(7);
//4. Log out
        BrowserUtils.clickOn(driver, storeManager,20);
        WebElement logOut = driver.findElement(By.xpath(logOutLocator));
        BrowserUtils.clickOn(driver, logOut,20);

    }

    @Test (priority=2)
//6. Verify Dashboad page is open
    public void salesManagerTestName() {

        //find username location
        WebElement username= driver.findElement(By.id(usernameLocation));
        BrowserUtils.sendKeys(driver,username,20,"salesmanager252");

        //find password location
        WebElement password= driver.findElement(By.id(passwordLocation));
        BrowserUtils.sendKeys(driver,password,20,"UserUser123");

        //find click button location
        WebElement click =driver.findElement(By.id (enterClickLocation));
        BrowserUtils.clickOn(driver, click,20);


        //7. A different name should be displayed on top right

        WebElement salesManager = driver.findElement(By.xpath(rightUserLocation));

        String expectedText = "Clifton Russel";
        BrowserUtils.waitPlease(5);
        System.out.println(salesManager.getText());
        BrowserUtils.waitPlease(5);
        Assert.assertTrue(salesManager.getText().contains(expectedText));

//4. Log out
        BrowserUtils.clickOn(driver, salesManager,20);
        WebElement logOut = driver.findElement(By.xpath(logOutLocator));
        BrowserUtils.clickOn(driver, logOut,20);

    }

    @Test (priority=3)

//9. Login to Vytrack as a driver
    public void DriverTestName() {

        //find username location
        WebElement username= driver.findElement(By.id(usernameLocation));
        BrowserUtils.sendKeys(driver,username,20,"User151");

        //find password location
        WebElement password= driver.findElement(By.id(passwordLocation));
        BrowserUtils.sendKeys(driver,password,20,"UserUser123");

        //find click button location
        WebElement click =driver.findElement(By.id (enterClickLocation));
        BrowserUtils.clickOn(driver, click,20);

//10. Verify Dashboad/Quick Launchpad page is open

        WebElement header = driver.findElement(By.xpath(headerLocation));
        String expectedTextHeader = "Quick Launchpad";
        //System.out.println(header.getText());
        Assert.assertTrue(header.getText().contains(expectedTextHeader));

        String expectedTitle = "Dashboard";
        BrowserUtils.checkTitle(driver,expectedTitle,20);

//11. A different name should be displayed on top right
        WebElement driverName = driver.findElement(By.xpath(rightUserLocation));

        String expectedName = "Clifton Russel";
        Assert.assertFalse(driverName.getText().contains(expectedName));
        BrowserUtils.waitPlease(5);

//12. Log out
        BrowserUtils.clickOn(driver, driverName,20);
        WebElement logOut = driver.findElement(By.xpath(logOutLocator));
        BrowserUtils.clickOn(driver, logOut,20);

    }
//TEST CASE: Login test (negative)
//2. Enter valid username and invalid password information
    @Test (priority=4)
    public void loginNegative(){

        String beforeLogInUrl=driver.getCurrentUrl();
        String beforeLogInTitle=driver.getTitle();


        //find username location
        WebElement username= driver.findElement(By.id(usernameLocation));
        BrowserUtils.sendKeys(driver,username,20,"storemanager85");

        //find password location
        WebElement password= driver.findElement(By.id(passwordLocation));
        BrowserUtils.sendKeys(driver,password,20,"Cybertek123");

        //find click button location
        WebElement click =driver.findElement(By.id (enterClickLocation));
        BrowserUtils.clickOn(driver, click,20);

//4. Message Invalid user name or password. should be displayed


        WebElement messageDisplayed=driver.findElement(By.xpath(messageDisplayedLocation));

        String expectedmessageDisplayedText="Invalid user name or password";
        Assert.assertTrue(messageDisplayed.getText().contains(expectedmessageDisplayedText));
        BrowserUtils.waitPlease(5);

//5. Page title and url should be same

        String afterLogInUrl=driver.getCurrentUrl();
        Assert.assertEquals(beforeLogInUrl,afterLogInUrl);
        BrowserUtils.waitPlease(5);
        String afterLogInTitle=driver.getTitle();
        Assert.assertEquals(beforeLogInTitle,afterLogInTitle);
    }

//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//
//    }

}