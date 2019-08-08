package com.vytrack.pages.activities;

import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CalendarEventsPage {

    private WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();


    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEventBtn;

    @FindBy(css = "[id^='oro_calendar_event_form_title']")
    public WebElement titleInputLocator;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDateLocator;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDateLocator;

    @FindBy(css = ".ui-datepicker-current.ui-state-default.ui-priority-secondary.ui-corner-all")
    public WebElement todayDateButtonLocator;

    @FindBy(css = ".input-small.timepicker-input.start.ui-timepicker-input")
    public WebElement startTimeLocator;

    @FindBy(css = ".ui-timepicker-pm.ui-timepicker-selected")
    public WebElement selectedTime;

    @FindBy(css = ".input-small.timepicker-input.end.ui-timepicker-input")
    public WebElement endTimeLocator;

    @FindBy(css = "input[id^='recurrence-repeat-view']")
    public WebElement repeatCheckBoxLocator;

    @FindBy(css = "select[id^='recurrence-repeats-view']")
    public WebElement repeatsDropdownLocator;

    @FindBy(css = "label[class='fields-row']>input[value='daily']+input")
    public WebElement repeatEveryLocator;

    @FindBy(css = "div[class='controls'][data-name='recurrence-summary']>div>span")
    public WebElement summaryLocator;

    @FindBy(xpath = "//span[contains(text(),'Weekday')]  ")
    public WebElement weekdayLocator;

    @FindBy(css="label[class='fields-row']>input[data-related-field='occurrences']")
    public WebElement afterOccurenceLocator;

    @FindBy(xpath = "//span[@id='temp-validation-name-564-error']//span[contains(text(),'This value should not be blank.')]" )
    public WebElement boundyValueAttentionAfterOccurences;

    @FindBy(xpath = "//span[@class='validation-failed']")
    public WebElement boundyValueAttentionRepeatEvery;


    public CalendarEventsPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void createNewEvent() {

        String username = ConfigurationReader.getProperty("storemanagerusername");
        //find password location
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //find click button location

        loginPage.login(username, password);

        // 2.Go to Activities -> Calendar Events
        //  BrowserUtils.waitForPageToLoad(20);
        BrowserUtils.navigateToModule(Driver.getDriver(), ConfigurationReader.getProperty("Activities"), ConfigurationReader.getProperty("ActivitiesModules2"));

        // 3.Click on create new calendar event
        //  BrowserUtils.waitForPageToLoad(20);
        BrowserUtils.waitForClickablility(createCalendarEventBtn, 20).click();

    }
     //IT TAKES FROM FIRST STEP TO REPEAT CHECK BOX
    public void repeatCheckBox() {
        createNewEvent();
        BrowserUtils.waitFor(5);
        BrowserUtils.waitForClickablility(repeatCheckBoxLocator, 20).click();

    }
    //WITH THAT METHOD WE WILL PICK ANY START TIME

    public void pickStartTime(String am_pm, String time) {
        BrowserUtils.waitForPageToLoad(20);
        BrowserUtils.clickWithJS(startTimeLocator);
        WebElement startTime = Driver.getDriver().findElement(By.xpath("//li[@class='ui-timepicker-" + am_pm + "'][text()='" + time + "']"));
        BrowserUtils.waitForClickablility(startTime, 20).click();
    }

    //WITH THAT METHOD WE WILL PICK ANY END TIME
    public void pickEndingTime(String am_pm, String time) {
        BrowserUtils.waitForPageToLoad(20);
        BrowserUtils.waitPlease(3);
        BrowserUtils.clickWithJS(endTimeLocator);
        WebElement endTime = Driver.getDriver().findElement(By.xpath("//li[@class='ui-timepicker-" + am_pm + "'][contains(text(),'" + time + " ')]"));
        BrowserUtils.waitForPageToLoad(20);
        BrowserUtils.waitForClickablility(endTime, 20).click();
    }

   //WE WILL GET ANY REPEAT OPTION FROM DROPDOWN

    public List<String> getRepeatOptions() {
        //we crated select object to work with dropdown

        Select select = new Select(repeatsDropdownLocator);

        //.getOptions(); return all available options in the dropdown.
        //every option is a webelement

        List<WebElement> options = select.getOptions();
        //this is a collection that will store text of every option
        List<String> optionsTextList = new ArrayList<>();
        for (WebElement option : options) {
            //go through every option and retrieve text
            //add that text into collection of text options
            optionsTextList.add(option.getText());
        }
        return optionsTextList;
    }

    //WE GIVE A ONE NUMBER AS STRING AND IT CHECK THE ATTENTION BOX TEXT
    //IT IS A BOUNDRY VALUE TEST
           public void boundryValueRepeatEvery(String value) {

               BrowserUtils.waitForPageToLoad(10);

               repeatEveryLocator.clear();

               BrowserUtils.sendKeys(driver, repeatEveryLocator, 5, value);


               int val = Integer.parseInt(value);

               if (val < 1 || val > 99) {

                   System.out.println(boundyValueAttentionRepeatEvery.getText());

                   if (val < 1) {
                       Assert.assertTrue(boundyValueAttentionRepeatEvery.getText().equals("The value have not to be less than 1."));
                   } else {
                       Assert.assertTrue(boundyValueAttentionRepeatEvery.getText().equals("The value have not to be more than 99."));
                   }

               } else {

                   System.out.println(repeatEveryLocator.getAttribute("value"));
                   Assert.assertTrue(Integer.parseInt(repeatEveryLocator.getAttribute("value")) == val);

               }
           }
           //CHECK AFTER THE ENTER ANY NUMBER "For Repeat Every Box" HOW CHANGE THE SUMMARY BOX

               public void repeatEveryDaySummaryNotes(){

              //     BrowserUtils.waitFor(3);
                   int num = (int) (Math.random() * 100 + 1);
                   String n = num + "";
                   repeatEveryLocator.clear();
                   BrowserUtils.sendKeys(driver,repeatEveryLocator, 10, n);
                   repeatEveryLocator.click();
                   BrowserUtils.waitFor(3);


                   String expected ="";

                   if(n.equals("1")){
                       expected.contains("Daily every"+n+" day");
                   }else{
                       expected = "Daily every "+n+" days";
                   }
                   System.out.println(summaryLocator.getText());
                   Assert.assertEquals(summaryLocator.getText(), expected);

               }

    //CHECK AFTER THE ENTER ANY NUMBER "For after Occurence Box" HOW CHANGE THE SUMMARY BOX
    public void afterOccurencesSummaryNotes(){

        //     BrowserUtils.waitFor(3);
        int num = (int) (Math.random() * 1000 + 1);
        String n = num + "";
        afterOccurenceLocator.clear();
        BrowserUtils.sendKeys(driver,afterOccurenceLocator, 10, n);
        afterOccurenceLocator.click();
        BrowserUtils.waitFor(3);


        String expected ="";

        if(n.equals("1")){
            expected = "Daily every "+n+" day";
        }else{
            expected = "Daily every "+n+" days";
        }
        System.out.println(summaryLocator.getText());
        Assert.assertEquals(summaryLocator.getText(), expected);

    }

    //WE GIVE A ONE NUMBER AS STRING AND IT CHECK THE "after Occurence Box" TEXT
    //IT IS A BOUNDRY VALUE TEST

    public void afterOccurencesBoundryValue(String value) {


        afterOccurenceLocator.clear();

        BrowserUtils.sendKeys(driver, afterOccurenceLocator, 5, value);


        int val = Integer.parseInt(value);

        if (val < 1 || val > 999) {

            System.out.println(boundyValueAttentionRepeatEvery.getText());

            if (val < 1) {
                Assert.assertTrue(boundyValueAttentionRepeatEvery.getText().equals("The value have not to be less than 1."));
            } else {
                Assert.assertTrue(boundyValueAttentionRepeatEvery.getText().equals("The value have not to be more than 999."));
            }

        } else {

            System.out.println(afterOccurenceLocator.getAttribute("value"));
            Assert.assertTrue(Integer.parseInt(afterOccurenceLocator.getAttribute("value")) == val);

        }
    }
}