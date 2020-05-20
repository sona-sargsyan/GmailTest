package testscenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.ElementHighlighter;

public class SendEmailTest extends BaseTest {
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
    public void highlightComposeEmail() {
        ElementHighlighter.highlightWebElement(driver, new WebDriverWait(driver, 200)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()=\"Compose\"]"))));
    }

}
