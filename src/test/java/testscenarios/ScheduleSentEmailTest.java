package testscenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InboxPage;
import util.DateUtil;

public class ScheduleSentEmailTest extends BaseTest{

    @Test
    public void scheduleSentEmailTest() {

        inboxPageObject = new InboxPage(driver);
        inboxPageObject.waitForThePageToLoad();
        inboxPageObject.composeAnEmail(EMAIL, SUBJECT, MESSAGE);
        inboxPageObject.openMoreSendOptions();
        inboxPageObject.openScheduledSendFrame();
        inboxPageObject.pickDateAndTime();
        inboxPageObject.scheduledEmailSend();
        String formatedTime = DateUtil.formatTime(inboxPageObject.scheduledTime);
        inboxPageObject.openFolder("Scheduled");
        inboxPageObject.clickOnFirstScheduled();
        Assert.assertEquals(inboxPageObject.getSubjectFromEmailDetailsPage(), SUBJECT);
        Assert.assertEquals(inboxPageObject.getRecipientFromEmailDetailsPage(), EMAIL);
        Assert.assertEquals(inboxPageObject.getMessageTextFromEmailDetailsPage(), MESSAGE);
        Assert.assertEquals(inboxPageObject.getScheduledTimeFromEmailDetailsPage(), formatedTime);

    }

}
