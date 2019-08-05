package com.vytrack.tests.components.activities;

import com.google.common.base.Verify;
import com.vytrack.pages.LoginPage;
import com.vytrack.pages.activities.CalendarEventsPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class CalenderEventsTest extends TestBase {

    //BeforeMethod is coming from TestBase

    CalendarEventsPage calendarPage = new CalendarEventsPage();


// 1) Date Time, End date auto adjust  (PASSED)

    @Test
    public void Test1() {

// 1. Log in as Valid user
// 2.Go to Activities -> Calendar Events
// 3.Click on create new calendar event
        calendarPage.createNewEvent();

// 4.Change the start date to future date
        calendarPage.startDateLocator.clear();
        BrowserUtils.sendKeys(Driver.getDriver(), calendarPage.startDateLocator, 20, "Aug 8, 2019");

        //  5.Verify that end date changes to the same date
        Assert.assertEquals(calendarPage.startDateLocator.getText(), calendarPage.endDateLocator.getText());

// 6.Change back the start date to today’s date
        calendarPage.startDateLocator.clear();
        BrowserUtils.clickWithJS(calendarPage.todayDateButtonLocator);

        // 7.Verify that end date changes back to today’s date
        Assert.assertEquals(calendarPage.startDateLocator.getText(), calendarPage.endDateLocator.getText());


    }
//2) Date Time, End time auto adjust

    @Test
    public void Test2() {  //(PASSED)

//1. Log in as Valid user
//2. Go to Activities -> Calendar Events
//3. Click on create new calendar event

        calendarPage.createNewEvent();

//4. Change the start time to any other time

        BrowserUtils.waitPlease(3);

        calendarPage.startTimeLocator.click();

        BrowserUtils.waitPlease(3);

        List<WebElement> times = driver.findElements(By.cssSelector("li[class*='ui-timepicker']"));
        String desiredTime = "6:30 PM";
        for (WebElement each : times) {
            if (each.getText().equals(desiredTime)) {
                each.click();
                break;
            }
        }

//5. Verify that end time changes exactly 1 hours later

        BrowserUtils.waitForVisibility(calendarPage.endTimeLocator, 15);

        String start = calendarPage.startTimeLocator.getAttribute("value").replaceAll("[^\\d]", "");
        String end = calendarPage.endTimeLocator.getAttribute("value").replaceAll("[^\\d]", "");

        int startT = Integer.parseInt(start);
        int endT = Integer.parseInt(end);

        System.out.println(startT);

        System.out.println(endT);

        Assert.assertTrue(endT - startT == 100);
    }



//    3) Date Time, End date/time auto adjust

    @Test
    public void Test3() {  //(PASSED)

//1. Log in as Valid user
//2. Go to Activities -> Calendar Events
//3. Click on create new calendar event

        calendarPage.createNewEvent();

//4. Change the start time to 11.30 PM

        calendarPage.pickStartTime("pm", "11:30 PM");


//5. Verify that end date shows tomorrows date

        BrowserUtils.waitFor(3);

        BrowserUtils.waitForVisibility(calendarPage.startDateLocator, 20);

        String start = calendarPage.startDateLocator.getAttribute("value").replaceAll("[^\\d]", "");

        int startDay = Integer.parseInt(start);

        BrowserUtils.waitForVisibility(calendarPage.endDateLocator, 20);

        String end = calendarPage.endDateLocator.getAttribute("value").replaceAll("[^\\d]", "");

        int endDay = Integer.parseInt(end);

        Assert.assertTrue(endDay - startDay == 10000);

//6. Verify that end time is 12:30 AM

        System.out.println(calendarPage.endTimeLocator.getAttribute("value"));

        Assert.assertEquals(calendarPage.endTimeLocator.getAttribute("value"), "12:30 AM");
    }

//    Daily Repeat Tests
//1) Daily repeat option, Repeat every, summary

    @Test
    public void Test4() {   //(PASSES)

//1. Log in as Valid user
//2. Go to Activities -> Calendar Events
//3. Click on create new calendar event
//4. Click on Repeat checkbox
//5. Verify that Daily is selected by default
//6. Verify day(s) checkbox is selected and default value is 1
//7. Verify summary says Daily every 1 day

        Test5();

//8. Check the weekday checkbox

        BrowserUtils.clickOn(driver, calendarPage.weekdayLocator, 10);

//9. Verify that days input now disabled

        Assert.assertFalse(calendarPage.repeatEveryLocator.isEnabled());

//10. Verify summary says Daily every weekday

        String expected = "Daily, every weekday";
        System.out.println(calendarPage.summaryLocator.getText());
        Assert.assertEquals(calendarPage.summaryLocator.getText(), expected);
    }

//     Daily repeat option, Repeat every, default values

    @Test
    public void Test5() {   // (PASSED)

//1. Log in as Valid user
//2. Go to Activities -> Calendar Events
//3. Click on create new calendar event
//4. Click on Repeat checkbox
     calendarPage.repeatCheckBox();

//5. Verify that Daily is selected by default
      Assert.assertTrue(calendarPage.getRepeatOptions().get(0).equals("Daily"));  // we try to get first element from List

//6. Verify day(s) checkbox is selected and default value is 1

      Assert.assertTrue(calendarPage.repeatEveryLocator.getAttribute("value").equals("1"));

//7. Verify summary says Daily every 1 day

        String expected = "Daily every 1 day";
        System.out.println(calendarPage.summaryLocator.getText());
        Assert.assertEquals(calendarPage.summaryLocator.getText(), expected);

    }

// Daily repeat option, Repeat every day(s), error messages


    @Test
    public void Test6() {  //(PASSED)

//1. Log in as Valid user
//2. Go to Activities -> Calendar Events
//3. Click on create new calendar event
//4. Click on Repeat checkbox

    calendarPage.repeatCheckBox();

     BrowserUtils.waitFor(5);

//5. Test the day(s) input entering different values (boundary value analysis)

     calendarPage.boundryValueRepeatEvery("30");

//more than 99. occur when values are too big or small

     calendarPage.boundryValueRepeatEvery("100");

//6. Verify error messages The value have not to be less than 1. and The value have not to be

     calendarPage.boundryValueRepeatEvery("0");

//7. Verify that error messages disappear when valid values are entered

     calendarPage.boundryValueRepeatEvery("30");

     Assert.assertTrue(calendarPage.boundyValueAttentionRepeatEvery.getText().equals(""));


    }

//4. Daily repeat option, Repeat every day(s), functionality

    @Test
    public void Test7() {  // (PASSED)

//1. Log in as Valid user
//2. Go to Activities -> Calendar Events
//3. Click on create new calendar event
//4. Click on Repeat checkbox

        calendarPage.repeatCheckBox();

//        5. Enter random value to the day(s) field
//        6. Verify that Summary says Daily every <random number> day

        calendarPage.repeatEveryDaySummaryNotes();

//  7. Enter another random value to the day(s) field
//  8. Verify that Summary updated with Daily every <random number> day

        calendarPage.repeatEveryDaySummaryNotes();
    }

//    5) Daily repeat option, blank fields

    @Test
    public void Test8() {

//1. Log in as Valid user
//2. Go to Activities -> Calendar Events
//3. Click on create new calendar event
//4. Click on Repeat checkbox

        calendarPage.repeatCheckBox();

//5. Clear the value of the day(s) field

        calendarPage.repeatEveryLocator.clear();

//6. Message This value should not be blank. should come up

        System.out.println(calendarPage.boundyValueAttentionRepeatEvery.getText());

        String blankAttention = "This value should not be blank.";

        Assert.assertEquals(calendarPage.boundyValueAttentionRepeatEvery.getText(), blankAttention);

//7. Enter a valid value to the day(s) field the

        int num = (int) (Math.random() * 100 + 1);

        String n = num + "";

        BrowserUtils.waitPlease(3);

        BrowserUtils.sendKeys(calendarPage.repeatEveryLocator, 10, n);


        calendarPage.repeatEveryLocator.click();

//8. Message This value should not be blank. should disappear

        Assert.assertTrue(calendarPage.boundyValueAttentionRepeatEvery.getText().equals(""));

        BrowserUtils.waitFor(3);

//9. Clear the value of the After occurrences field

//11. Enter a valid value to the After occurrences field the


        calendarPage.afterOccurenceLocator.click();

        BrowserUtils.sendKeys(calendarPage.afterOccurenceLocator, 10, n);

        calendarPage.afterOccurenceLocator.clear();

//10. Message This value should not be blank. should come up

        String blankAttention1 = "This value should not be blank.";

        Assert.assertTrue(calendarPage.boundyValueAttentionAfterOccurences.getText().contains( blankAttention));

//11. Enter a valid value to the After occurrences field the

        int num2 = (int) (Math.random() * 1000 + 1);

        String n2 = num2 + "";

        BrowserUtils.sendKeys(calendarPage.afterOccurenceLocator, 10, n2);

//12. Message This value should not be blank. should disappear

        BrowserUtils.waitFor(3);

        Assert.assertTrue(calendarPage.boundyValueAttentionAfterOccurences.getText().equals(""));
    }


//6) Daily repeat option, Ends, error messages

    @Test
    public void Test9() {  //(PASSED)

//1. Log in as Valid user
//2. Go to Activities -> Calendar Events
//3. Click on create new calendar event
//4. Click on Repeat checkbox

     calendarPage.repeatCheckBox();

//5. Test the After occurrences input entering different values (boundary value analysis)
//6. Verify error messages The value have not to be less than 1. and The value have not to be

     calendarPage.boundryValueRepeatEvery("0");

//more than 99. occur when values are too big or small

     calendarPage.boundryValueRepeatEvery("100");

//7. Verify that error messages disappear when valid values are entered

     calendarPage.boundryValueRepeatEvery("56");

     Assert.assertTrue(calendarPage.boundyValueAttentionRepeatEvery.getText().equals(""));

    }


//7) Daily repeat option, Ends, functionality


    @Test
    public void Test10() {

//1. Log in as Valid user
//2. Go to Activities -> Calendar Events
//3. Click on create new calendar event
//4. Click on Repeat checkbox

     calendarPage.repeatCheckBox();

//5. Enter random value to the After occurrences field
//6. Verify that Summary says Daily every <random number> day
//7. Enter another random value to the After occurrences field
//8. Verify that Summary updated with Daily every <random number> day

    }

}

//@Test
//public void verifyRepeatOptions(){
//
//        //we are reading username from .properties file
//        String username = ConfigurationReader.getProperty("storemanagerusername");
//        //we are reading password from .properties file
//        String password = ConfigurationReader.getProperty("storemanagerpassword");
//
//        BrowserUtils.login(Driver.getDriver(), username, password); //login
//
//        //go to Calendar Events
//        BrowserUtils.navigateToModule(Driver.getDriver(), "Activities", "Calendar Events");
//        //since vytrack displays overlay screen during loading of the page
//        //we have wait, until that overlay screen disappear
//
//        BrowserUtils.waitUntilLoaderScreenDisappear(Driver.getDriver());
//
//        driver.findElement(By.xpath(calendarPage.createCalendarEventBtnLocator)).click();
//
//        //since vytrack displays overlay screen during loading of the page
//        //we have wait, until that overlay screen disappear
//        BrowserUtils.waitUntilLoaderScreenDisappear(driver);



        //AfterMethod id coming from TestBase
