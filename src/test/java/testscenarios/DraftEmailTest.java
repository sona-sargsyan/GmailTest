package testscenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DraftEmailTest extends BaseTest {
//    public static String EMAIL = "son.sargsyan@gmail.com";
//    public static String SUBJECT = "mentoring";
//    public static String MESSAGE = "massage text to ss";

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
}
