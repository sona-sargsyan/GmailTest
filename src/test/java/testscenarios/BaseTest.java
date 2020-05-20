package testscenarios;

import driver.DriverSingleton;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.InboxPage;
import pages.SignInlPage;
import service.UserBuilder;
import util.TestListener;

@Listeners({TestListener.class})
public class BaseTest {
    public static String EMAIL = "son.sargsyan@gmail.com";
    public static String SUBJECT = "mentoring";
    public static String MESSAGE = "massage text to ss";

    protected WebDriver driver;
    protected InboxPage inboxPageObject;

    @BeforeMethod(alwaysRun = true)
    public void browserSetupAndLogin() {
         driver = DriverSingleton.getDriver();
        User testUser = UserBuilder.withCredentialsFromProperty();
        inboxPageObject = new SignInlPage(driver)
                .openSignInPage().login(testUser);
    }

    @AfterMethod(alwaysRun = true)
    public void SignOutAndTearDownBrowser() {
        inboxPageObject.clickOnTheAccount();
        inboxPageObject.clickSignOut(UserBuilder.USERNAME + "@gmail.com");
        DriverSingleton.closeDriver();
    }
}
