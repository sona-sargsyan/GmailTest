package testscenarios;

import factory.EmailFactory;
import model.Email;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ComposeEmailPage;
import pages.EmailDetailsPage;

import static service.TestDataReader.getTestData;
import static util.Constants.Properties.*;

public class DraftEmailTest extends BaseTest {

    @Test
    public void draftMessageTest() {
        SoftAssert softAssert = new SoftAssert();
        Email email = EmailFactory.composeEmailWithMessage(getTestData(EMAIL_MESSAGE));

        inboxPageObject.waitForThePageToLoad();
        Assert.assertTrue(driver.getCurrentUrl().contains("inbox"));
        ComposeEmailPage composeEmailPage = new ComposeEmailPage(driver);
        composeEmailPage.composeAnEmail(email);
        composeEmailPage.saveAndCloseMessageForm();
        inboxPageObject.openFolder("Drafts");
        inboxPageObject.clickOnFirstDaft();
        softAssert.assertEquals(composeEmailPage.getAddress(), getTestData(EMAIL_RECIPIENT));
        softAssert.assertEquals(composeEmailPage.getEmailSubject(), getTestData(EMAIL_SUBJECT));
        softAssert.assertEquals(composeEmailPage.getTextMessage(), getTestData(EMAIL_MESSAGE));
        composeEmailPage.sendEmail();
        softAssert.assertTrue(inboxPageObject.isDraftsFolderEmpty());
        inboxPageObject.openFolder("Sent");
        inboxPageObject.clickOnFirstSent();
        EmailDetailsPage emailDetailsPage = new EmailDetailsPage(driver);
        softAssert.assertEquals(emailDetailsPage.getRecipientFromEmailDetailsPage(), getTestData(EMAIL_RECIPIENT));
        softAssert.assertEquals(emailDetailsPage.getSubjectFromEmailDetailsPage(), getTestData(EMAIL_SUBJECT));
        softAssert.assertEquals(emailDetailsPage.getMessageTextFromEmailDetailsPage(), getTestData(EMAIL_MESSAGE));
        softAssert.assertAll();
    }
}
