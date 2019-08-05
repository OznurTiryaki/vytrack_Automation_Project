package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {


    String userNameLocator = "prependedInput";
    String passwordLocator = "prependedInput2";
    String loginButtonLocator = "_submit";

    String rightUserLocation = "(//a[@class='dropdown-toggle'])[1]";
    String salesManagerLocation = "//a[@class='dropdown-toggle'][contains(text(),'Clifton Russel')]";


    String messageDisplayedLocation = "//div[contains(text(),'Invalid user name or password')]";

    String headerLocation = "//h1[@class='oro-subtitle']";

    public void login(String userName, String password) {
        Driver.getDriver().findElement(By.id(userNameLocator)).sendKeys(userName);
        Driver.getDriver().findElement(By.id(passwordLocator)).sendKeys(password);
        Driver.getDriver().findElement(By.id(loginButtonLocator)).click();

    }
    public String menuIconLocator = "fa-caret-down";



    public void logOut(){

        BrowserUtils.waitPlease(4);
        Driver.getDriver().findElement(By.className(menuIconLocator)).click();
        BrowserUtils.waitPlease(2);
        Driver.getDriver().findElement(By.linkText("Logout")).click();

    }
    public void verifyUser(String userName) {


        String expectedText = ConfigurationReader.getProperty(userName);  //expectedname'i congfigurationdan cekiyoruz
        String actual = Driver.getDriver().findElement(By.xpath(rightUserLocation)).getText(); //sag ustteki isim yerine gelip tikliyoruz
        Assert.assertTrue(actual.contains(expectedText));

    }

    public void verifyDashboard(String ExpectedHeaderTitle) {

        BrowserUtils.waitForPageToLoad(20);

        BrowserUtils.checkTitle(Driver.getDriver(),ExpectedHeaderTitle,20); //title i kontrol ediyoruz


    }
}