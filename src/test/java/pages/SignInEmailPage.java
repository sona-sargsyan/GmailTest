package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInEmailPage extends BasePage {

    private static String SIGNINEMAILPAGE_URL = "https://gmail.com";

    public SignInEmailPage(WebDriver driver) {
        super(driver);
    }

    public SignInEmailPage openSignInPage() {
        driver.get(SIGNINEMAILPAGE_URL);
        return this;
    }

    public void fillEmailIdentifier(String email) {
        WebElement emailIdentifierId = new WebDriverWait(driver, 200)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("identifierId")));
        emailIdentifierId.sendKeys(email);
    }

    public void clickNextButtonOnEmailLogin() {
        WebElement emailNextButton = new WebDriverWait(driver, 200)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("identifierNext")));
        emailNextButton.click();
    }

    public SignInPasswordPage openSignInPasswordPage(String email) {
        fillEmailIdentifier(email);
        clickNextButtonOnEmailLogin();
        new WebDriverWait(driver, 200)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("profileIdentifier")));
        return new SignInPasswordPage(driver);
    }

}