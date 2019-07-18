package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class SeleniumUtils {

        /**
         * @param expectedResult
         * @param actualResult
         * Verify if two strings are equals.
         */
        public static void verifyEquals(String expectedResult, String actualResult) {

                if (expectedResult.equals(actualResult)) {
                        System.out.println("Passed");
                } else {
                        System.out.println("Failed");
                        System.out.println("Expected result: " + expectedResult);
                        System.out.println("Actualresult: " + actualResult);
                }
        }
        public static void waitPlease(int seconds){
                try{
                        Thread.sleep(seconds*1000);
                }catch (Exception e){
                        System.out.println(e.getMessage());
                }

        }
        /**
         *
         * @param element
         * Thsi method verifies whetheran element is displayed
         */
        public static void verifyIsDisplayed(WebElement element){

                if(element.isDisplayed()){
                        System.out.println("Passes");
                        System.out.println(element.getText()+" is visible");
                }else{
                        System.out.println("Failed");
                        System.out.println(element.getText()+" is not visible");
                }

        }

        /**
         * This method is to send keys with Explicit waits
         * you will change time, or elements, this is biggest advantages
         * @param driver
         * @param element
         * @param timeout
         * @param value
         */
        public static void sendKeys(WebDriver driver, WebElement element, int timeout, String value){
                new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));
                element.sendKeys(value);
        }

        /**
         * This method for click button with with Explicit waits
         * @param driver
         * @param element
         * @param timeout
         */

        public static void clickOn(WebDriver driver, WebElement element, int timeout){
                new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element));
                element.click();
        }

        /**
         * This method to verify a Page Title
         * @param driver
         * @param title
         * @param timeout
         */

        public static void checkTitle(WebDriver driver, String title, int timeout){
                new WebDriverWait(driver,timeout).until(ExpectedConditions.titleIs(title));
          //      Assert.assertTrue( driver.getTitle().contains(title));
                  System.out.println( driver.getTitle());

        }



}
