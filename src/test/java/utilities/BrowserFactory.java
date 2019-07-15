package utilities;


    /**
     * @param browser name
     * @return browser object, otherwise throw exception to prevent test run
     */import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

    public class BrowserFactory {



        public static  WebDriver getDriver(String browser) {

            /**
             * @param browser name
             * @return browser object otherwise throw exception to prevent test return
             *
             */

            if (browser.equals("chrome")) {
                WebDriverManager.chromedriver().setup();//WebDriverManager comes from bonigarcia, it is dependency
                return new ChromeDriver();
            } else if (browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            } else {
                throw new IllegalArgumentException("Wrong browser name!");

            }
        }}


