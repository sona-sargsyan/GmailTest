package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPasswordPage extends BasePage {

    @FindBy(name = "password")
    private WebElement passwordIdentifier;

    public SignInPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void fillPasswordIdentifier(String password) {
        new WebDriverWait(driver, 200)
                .until(ExpectedConditions.visibilityOf(passwordIdentifier));
        passwordIdentifier.sendKeys(password);
    }

    public void clickNextButtonOnPasswordIdentifier() {
        WebElement passwordNextButton = new WebDriverWait(driver, 200)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("passwordNext")));
        passwordNextButton.click();
    }

    public InboxPage loginByPassword(String password) {
        fillPasswordIdentifier(password);
        clickNextButtonOnPasswordIdentifier();
        return new InboxPage(driver);
    }
}
