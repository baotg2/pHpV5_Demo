package MetaFox.browserConfig;

import MetaFox.support.DataExecutor;
import MetaFox.support.EnumDataValue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**------------------------------------------------------------------------------------------------------------------------------------------------
 * @since 04-05-2022
 * @Author  baotg2
 * @purpose: Index is class set up browser
 * @package browserConfig
 * @version 1.0.1
 ------------------------------------------------------------------------------------------------------------------------------------------------*/
public class Index {

    DataExecutor dataExecutor = new DataExecutor();

    public static WebDriver driver;
    public static String selectPlatform;
    public String AUTOMATE_USERNAME = dataExecutor.readConstants("BROWSERSTACK_USERNAME");
    public String AUTOMATE_ACCESS_KEY = dataExecutor.readConstants("BROWSERSTACK_ACCESS_KEY");
    public String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public Index (WebDriver driver){
        Index.driver = driver;
    }

    /**-------------------------------------------------------------------------------------------------------------------------------------------
     * @since 04-05-2022
     * @Author  baotg2
     * @purpose getDriver is function to call the driver from another class
     -------------------------------------------------------------------------------------------------------------------------------------------*/
    public static WebDriver getDriver(){
       return driver;
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------
     * @since 04-05-2022
     * @param enumDataValue is param to set open browser
     * @param selectFlatForm is param to get test cases run on Platform
     * @throws MalformedURLException thrown when the built-in URL class encounters an invalid URL
     * @purpose openBrowser is a function to set up test cases run on local or cloud
     ------------------------------------------------------------------------------------------------------------------------------------------*/
    public void openBrowser(EnumDataValue enumDataValue, EnumDataValue selectFlatForm) throws MalformedURLException{
        Index.selectPlatform = String.valueOf(selectFlatForm);
        switch (selectFlatForm) {
            case BROWSERSTACK:
                switch (enumDataValue){
                    case FIREFOX:
                        DesiredCapabilities caps = new DesiredCapabilities();
                        caps.setCapability("os_version", "10");
                        caps.setCapability("browser", enumDataValue);
                        caps.setCapability("browser_version", "latest");
                        caps.setCapability("os", "Windows");
                        caps.setCapability("build", dataExecutor.readConstants("TestCaseRunner")); // test name
                        caps.setCapability("name", "BrianDeleteBlogOnUserProfile"); // CI/CD job or build name
                        caps.setCapability("browserstack.debug", "true");
                        driver = new RemoteWebDriver(new URL(URL), caps);
                        break;
                    case SAFARI:
                        DesiredCapabilities caps_SF = new DesiredCapabilities();
                        caps_SF.setCapability("os_version", "Big Sur");
                        caps_SF.setCapability("browser", enumDataValue);
                        caps_SF.setCapability("browser_version", "latest");
                        caps_SF.setCapability("os", "OS X");
                        caps_SF.setCapability("build", dataExecutor.readConstants("TestCaseRunner")); // test name
                        caps_SF.setCapability("name", "BrianDeleteBlogOnUserProfile"); // CI/CD job or build name
                        caps_SF.setCapability("browserstack.debug", "true");
                        driver = new RemoteWebDriver(new URL(URL), caps_SF);
                        break;
                }
            case LOCAL:
                switch (enumDataValue){
                    case CHROME:
                        driver = new ChromeDriver();
                        break;
                    case FIREFOX:
                        driver = new FirefoxDriver();
                        break;
                }
                break;
            default:
                driver = new ChromeDriver();
        }
        driver.get(dataExecutor.readConstants("URL"));
        driver.manage().window().maximize();
    }
}
