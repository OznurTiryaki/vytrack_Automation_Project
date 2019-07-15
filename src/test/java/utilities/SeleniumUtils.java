package utilities;

import org.openqa.selenium.WebElement;

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
}
