package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.util.List;

public class InboxPage extends BasePage {

    public LocalDateTime scheduledTime;

    @FindBy(xpath = "//div[text()=\"Compose\"]")
    private WebElement composeEmail;

    @FindBy(xpath = "//textarea[@name=\"to\"]")
    private WebElement recipientAddress;

    @FindBy(xpath = "//input[@name=\"subjectbox\"]")
    private WebElement emailSubject;

    @FindBy(xpath = "//div[@aria-label=\"Message Body\"]")
    private WebElement emailMessage;

    @FindBy(xpath = "//div[text()=\"Send\"]")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//img[@aria-label=\"Save & close\"]")
    private WebElement messageSaveAndCloseButton;

    @FindBy(xpath = "//tbody//tr[1]//td//div[@class=\"yW\"]//span[text()=\"Draft\"]")
    private WebElement firstRowOfTheDrifts;

    @FindBy(xpath = "//tbody//tr[1]//div[text()=\"To: \"]")
    private WebElement firstRowOfTheSent;

    @FindBy(xpath = "//tbody//td[text()=\"You don't have any saved drafts.\"]")
    private WebElement draftFolderEmptyText;

    @FindBy(xpath = "//div[@aria-label=\"More send options\"]")
    private WebElement moreSendOptions;

    @FindBy(xpath = "//div[@selector=\"scheduledSend\"]")
    private WebElement scheduledSend;

    @FindBy(xpath = "//div[@id=\"sbddm\"]")
    private WebElement scheduleSendButton;

    @FindBy(xpath = "//div[text()=\"Pick date & time\"]")
    private WebElement pickDateAndTime;

    @FindBy(name = "datetimepicker_dialog_confirm")
    private WebElement scheduledEmailSendButton;

    @FindBy(xpath = "//div[@class=\"a3s aXjCH \"]/div[1]")
    private WebElement messageTextFromSchedulePage;

    @FindBy(xpath = "//span[@class=\"hb\"]/span")
    private WebElement recipientFromEmailDetailsPage;

    @FindBy(xpath = "//a[contains(@aria-label,\"Google Account\")]")
    private WebElement account;

    @FindBy(xpath = "//a[text()=\"Sign out\"]")
    private WebElement signout;

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public void deleteEmailFromEmailDetails() {
        Actions hover = new Actions(driver);
        WebElement deleteEmailFromEmailDetails = new WebDriverWait(driver, 2000)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\"Delete\"])[last()]")));
        hover.moveToElement(deleteEmailFromEmailDetails).build().perform();
        WebElement deleteEmailFromEmailDetails1 = new WebDriverWait(driver, 100)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-label=\"Delete\"]")));
        deleteEmailFromEmailDetails1.click();
    }

    public void deleteEmailFromEmailDetailsByJS() {

        WebElement deleteEmailFromEmailDetails = new WebDriverWait(driver, 100)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\"Delete\"])[last()]")));
        Actions hover = new Actions(driver);
        hover.moveToElement(deleteEmailFromEmailDetails).build().perform();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        WebElement deleteEmailFromEmailDetails1 = new WebDriverWait(driver, 100)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-label=\"Delete\"]")));
        javascriptExecutor.executeScript("arguments[0].click();", deleteEmailFromEmailDetails1);

    }

    public void hoverAndClickAdvancedSearchButton() {
        Actions hover = new Actions(driver);
        WebElement searchDropDownButton = new WebDriverWait(driver, 2000)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label=\"Advanced search options\"]")));
        hover.moveToElement(searchDropDownButton).click().build().perform();
    }

    public void checkHasAttachmentCheckBox(){
       WebElement hasAttachmentCheckBox = new WebDriverWait(driver,2000)
               .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()=\"Has attachment\"]")));
       hasAttachmentCheckBox.click();
    }

    public void clickAdvancedSearch(){
        WebElement advancedSearch = new WebDriverWait(driver,2000)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"SK ZF-zT\"]//div[text()=\"Search\"]")));
        advancedSearch.click();
    }

    public void clickOnTheAccount() {
        account.click();
        new WebDriverWait(driver, 2000).until(ExpectedConditions.visibilityOf(signout));
    }

    public void clickSignOut(String email) {
        signout.click();
        try {
            Alert alert = new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
            alert.accept();
        }
        catch (TimeoutException e){
            e.printStackTrace();
        }
            finally {
            new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-identifier=\"mentor.mentroevich@gmail.com\"]")));
        }

    }

    public String getSubjectFromEmailDetailsPage() {
        WebElement subjectFromSchedulePage = new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class=\"hP\"]")));
        return subjectFromSchedulePage.getText();
    }

    public String getMessageTextFromEmailDetailsPage() {
        return messageTextFromSchedulePage.getText();
    }

    public String getRecipientFromEmailDetailsPage() {
        return recipientFromEmailDetailsPage.getAttribute("data-hovercard-id");
    }

    public String getScheduledTimeFromEmailDetailsPage() {
        WebElement scheduledTimeFromEmailDetails = new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),\"Send scheduled for\")]")));
        return scheduledTimeFromEmailDetails.getText().substring(19, 26);
    }

    public void pickDateAndTime() {
        new WebDriverWait(driver, 200).until(ExpectedConditions.visibilityOf(pickDateAndTime));
        pickDateAndTime.click();
    }

    public void scheduledEmailSend() {
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.name("datetimepicker_dialog_confirm")));
        scheduledTime = LocalDateTime.now().plusHours(1);
        scheduledEmailSendButton.click();
    }

    public void openScheduledSendFrame() {
        new WebDriverWait(driver, 200).until(ExpectedConditions.visibilityOf(scheduleSendButton));
        scheduledSend.click();
    }

    public void openMoreSendOptions() {
        moreSendOptions.click();
    }

    public void fillEmailSubject(String emailSubject) {
        this.emailSubject.sendKeys(emailSubject);
    }

    public String getEmailSubject() {
        return this.emailSubject.getAttribute("value");
    }

    public void fillAddress(String address) {
        recipientAddress.sendKeys(address);
    }

    public String getAddress() {
        WebElement filledRecipientAddress = new WebDriverWait(driver, 20000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"aoD hl\"]//span")));
        return filledRecipientAddress.getText();
    }

    public void fillTextMessage(String message) {
        emailMessage.sendKeys(message);
    }

    public String getTextMessage() {
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-label=\"Message Body\"]")));
        return emailMessage.getText();
    }

    public void sendEmail() {
        sendEmailButton.click();
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=\"Message sent.\"]")));
    }

    public void openNewMessageForm() {
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()=\"Compose\"]")));
        composeEmail.click();
    }

    public void waitForThePageToLoad() {
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()=\"Compose\"]")));
        new WebDriverWait(driver, 200).until(ExpectedConditions.urlContains("inbox"));
    }

    public void clickTabAndEnterButtons(){
        Actions tabAndEnter = new Actions(driver);
        tabAndEnter.sendKeys(Keys.TAB).build().perform();
        tabAndEnter.sendKeys(Keys.ENTER).build().perform();
    }

    public void composeAnEmail(String address, String subject, String message) {
        waitForThePageToLoad();
        openNewMessageForm();
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@name=\"to\"]")));
        fillAddress(address);
        fillEmailSubject(subject);
        fillTextMessage(message);
    }

    public void saveAndCloseMessageForm() {
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"aYF\" and text()=\"Draft saved\"]")));
        messageSaveAndCloseButton.click();
    }

    public void openFolder(String folderName) {
        WebElement folder = new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()=\"" + folderName + "\"]")));
        folder.click();
        new WebDriverWait(driver, 200).until(ExpectedConditions.urlContains(folderName.toLowerCase()));
    }

    public boolean isDraftsFolderEmpty() {
        return draftFolderEmptyText.isDisplayed();
    }

    public void clickOnFirstDaft() {
        new WebDriverWait(driver, 200).until(ExpectedConditions.urlContains("drafts"));
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody//tr[1]//td//span[text()=\"Draft\"]")));
        firstRowOfTheDrifts.click();
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"M9\"]")));
    }

    public void clickOnFirstSent() {
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody//tr[1]//div[text()=\"To: \"]")));
        firstRowOfTheSent.click();
    }

    public void clickOnFirstScheduled() {
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody//tr[1]//div[text()=\"To: \"]")));
        firstRowOfTheSent.click();
    }

    public void clickOnFirstEmail() {
       WebElement firstRowOfInbox = new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody//tr[1]//div[@title=\"Inbox\"]")));
        firstRowOfInbox.click();
    }

    public void clickOnEmailBySubject(String subject) {
        List<WebElement> firstRowOfTheEmailList = new WebDriverWait(driver, 200)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[text()=\"" + subject + "\"]")));
        for (WebElement firstEmail : firstRowOfTheEmailList) {
            if (firstEmail.isDisplayed())
                firstEmail.click();
        }

    }

    public boolean attachmentIsPresent(){

        if (new WebDriverWait(driver,2000).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"ii gt\"]//img")))!=null)
            return true;
        else
            return false;
    }

    public boolean isEmailPresentsInList(String subject) {
        List<WebElement> firstRowOfTheEmailList = new WebDriverWait(driver, 200)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[text()=\"" + subject + "\"]")));
        return (firstRowOfTheEmailList.size() > 0);
    }

    public void undoEmailDeletion() {
        WebElement undoButton = new WebDriverWait(driver, 200)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=\"Undo\"]")));
        undoButton.click();
        new WebDriverWait(driver, 200).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=\"Action undone.\"]")));
    }

    public boolean isEmailSend() {
        if (new WebDriverWait(driver, 2000)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=\"Message sent.\"]"))) != null) {
            return true;
        } else return false;
    }


}
