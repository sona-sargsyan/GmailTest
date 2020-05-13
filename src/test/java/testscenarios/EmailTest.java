package testscenarios;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InboxPage;
import pages.SignInEmailPage;
import pages.SignInPasswordPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class EmailTest extends BaseTest {

    public static String USERNAME = "mentor.mentroevich";
    public static String PASSWORD = "mentroevich12";
    public static String EMAIL = "son.sargsyan@gmail.com";
    public static String SUBJECT = "mentoring";
    public static String MESSAGE = "massage text to ss";

    InboxPage inboxPageObject;

    @Test
    public void draftMessageTest() {
        SoftAssert softAssert = new SoftAssert();

        inboxPageObject.waitForThePageToLoad();
        Assert.assertTrue(driver.getCurrentUrl().contains("inbox"));
        inboxPageObject.composeAnEmail(EMAIL, SUBJECT, MESSAGE);
        inboxPageObject.saveAndCloseMessageForm();
        inboxPageObject.openFolder("Drafts");
        inboxPageObject.clickOnFirstDaft();
        softAssert.assertEquals(inboxPageObject.getAddress(), EMAIL);
        softAssert.assertEquals(inboxPageObject.getEmailSubject(), SUBJECT);
        softAssert.assertEquals(inboxPageObject.getTextMessage(), MESSAGE);
        inboxPageObject.sendEmail();
        softAssert.assertTrue(inboxPageObject.isDraftsFolderEmpty());
        inboxPageObject.openFolder("Sent");
        inboxPageObject.clickOnFirstSent();
        softAssert.assertEquals(inboxPageObject.getRecipientFromEmailDetailsPage(), EMAIL);
        softAssert.assertEquals(inboxPageObject.getSubjectFromEmailDetailsPage(), SUBJECT);
        softAssert.assertEquals(inboxPageObject.getMessageTextFromEmailDetailsPage(), MESSAGE);
        softAssert.assertAll();
    }

    @Test
    public void undoMessageDeletionTest() {
        inboxPageObject = new InboxPage(driver);
        inboxPageObject.waitForThePageToLoad();
        inboxPageObject.clickOnEmailBySubject("module 6-7");
        inboxPageObject.deleteEmailFromEmailDetails();
        inboxPageObject.undoEmailDeletion();
        inboxPageObject.openFolder("Inbox");
        Assert.assertTrue(inboxPageObject.isEmailPresentsInList("module 6-7"));
    }

    @Test
    public void scheduleSentEmailTest() {

        inboxPageObject = new InboxPage(driver);
        inboxPageObject.waitForThePageToLoad();
        inboxPageObject.composeAnEmail(EMAIL, SUBJECT, MESSAGE);
        inboxPageObject.openMoreSendOptions();
        inboxPageObject.openScheduledSendFrame();
        inboxPageObject.pickDateAndTime();
        inboxPageObject.scheduledEmailSend();
        inboxPageObject.openFolder("Scheduled");
        inboxPageObject.clickOnFirstScheduled();
        Assert.assertEquals(inboxPageObject.getSubjectFromEmailDetailsPage(), SUBJECT);
        Assert.assertEquals(inboxPageObject.getRecipientFromEmailDetailsPage(), EMAIL);
        Assert.assertEquals(inboxPageObject.getMessageTextFromEmailDetailsPage(), MESSAGE);
        Assert.assertEquals(inboxPageObject.getScheduledTimeFromEmailDetailsPage(), formatTime(inboxPageObject.scheduledTime));

    }

    @Test
    public void searchEmailTest() {

        inboxPageObject = new InboxPage(driver);
        inboxPageObject.waitForThePageToLoad();
        inboxPageObject.hoverAndClickAdvancedSearchButton();
        inboxPageObject.checkHasAttachmentCheckBox();
        inboxPageObject.clickAdvancedSearch();
        inboxPageObject.clickOnFirstEmail();
        Assert.assertTrue(inboxPageObject.attachmentIsPresent());

    }

    @Test
    public void sendEmailByEnterButtonTest() {

        inboxPageObject.waitForThePageToLoad();
        Assert.assertTrue(driver.getCurrentUrl().contains("inbox"));
        inboxPageObject.composeAnEmail(EMAIL, SUBJECT, MESSAGE);
        inboxPageObject.clickTabAndEnterButtons();
        Assert.assertTrue(inboxPageObject.isEmailSend());
        inboxPageObject.openFolder("Sent");
        inboxPageObject.clickOnFirstSent();
        Assert.assertEquals(inboxPageObject.getRecipientFromEmailDetailsPage(), EMAIL);
        Assert.assertEquals(inboxPageObject.getSubjectFromEmailDetailsPage(), SUBJECT);
        Assert.assertEquals(inboxPageObject.getMessageTextFromEmailDetailsPage(), MESSAGE);

    }

    @Test
    public void clickByJavaScript() {

        inboxPageObject = new InboxPage(driver);
        inboxPageObject.waitForThePageToLoad();
        inboxPageObject.clickOnEmailBySubject("module 6-7");
        inboxPageObject.deleteEmailFromEmailDetailsByJS();
        inboxPageObject.undoEmailDeletion();
        inboxPageObject.openFolder("Inbox");
        Assert.assertTrue(inboxPageObject.isEmailPresentsInList("module 6-7"));

    }

    @Test
    public void highlightComposeEmail() {
        highlightWebElement(driver, new WebDriverWait(driver, 200)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()=\"Compose\"]"))));
    }

    @BeforeMethod
    public void login() {
        inboxPageObject = new InboxPage(driver);
        SignInPasswordPage loginObject = new SignInEmailPage(driver)
                .openSignInPage()
                .openSignInPasswordPage(USERNAME);
        loginObject.loginByPassword(PASSWORD);
    }

    @AfterMethod
    public void signout() {
        inboxPageObject.clickOnTheAccount();
        inboxPageObject.clickSignOut(USERNAME + "@gmail.com");
    }

    public String formatTime(LocalDateTime scheduledTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        return scheduledTime.format(formatter);
    }

    public void highlightWebElement(WebDriver driver, WebElement element) {
        String backgroundColor = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor ='" + "yellow" + "'", element);
        js.executeScript("arguments[0].style.backgroundColor = '" + backgroundColor + "'", element);
    }
}
