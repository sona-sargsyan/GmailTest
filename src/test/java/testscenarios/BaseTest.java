package testscenarios;

import driver.DriverSingletonFactory;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.InboxPage;
import pages.SignInlPage;
import service.UserBuilder;
import util.TestListener;

import static service.TestDataReader.getTestData;
import static util.Constants.Properties.USERNAME;

@Listeners({TestListener.class})
public class BaseTest {

    protected WebDriver driver;
    protected InboxPage inboxPageObject;

    @BeforeMethod(alwaysRun = true)
    public void browserSetupAndLogin() {
        driver = DriverSingletonFactory.getDriver();
        User testUser = UserBuilder.withCredentialsFromProperty();
        inboxPageObject = new SignInlPage(driver)
                .openSignInPage().login(testUser);
    }

    @AfterMethod(alwaysRun = true)
    public void SignOutAndTearDownBrowser() {
        inboxPageObject.clickOnTheAccount();
        inboxPageObject.clickSignOut(getTestData(USERNAME) + "@gmail.com");
        DriverSingletonFactory.closeDriver();
    }
}
