package testscenarios;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import sun.plugin2.os.windows.Windows;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        DesiredCapabilities capabilities= DesiredCapabilities.chrome();
        capabilities.setPlatform(Platform.WINDOWS);
        try {
            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"),capabilities);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        driver.quit();
        driver = null;
    }
}
