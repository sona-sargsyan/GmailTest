package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInEmailPage extends BasePage {

    private static String SIGNINEMAILPAGE_URL = "https://gmail.com";

    public SignInEmailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "next")
    private WebElement nextButton;

    @FindBy(id = "Email")
    private WebElement email;

    public SignInEmailPage openSignInPage() {
        driver.get(SIGNINEMAILPAGE_URL);
        return this;
    }

    public void fillEmailIdentifier(String email) {
        try {
            WebElement emailIdentifierId = new WebDriverWait(driver, 200)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("identifierId")));
            emailIdentifierId.sendKeys(email);
        } catch (NoSuchElementException e) {
            this.email.sendKeys(email);
            e.printStackTrace();
        }

    }

    public void clickNextButtonOnEmailLogin() {
        try {
            WebElement emailNextButton = new WebDriverWait(driver, 200)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("identifierNext")));
            emailNextButton.click();
        } catch (NoSuchElementException e) {
            nextButton.click();
            e.printStackTrace();
        }
    }

    public SignInPasswordPage openSignInPasswordPage(String email) {
        fillEmailIdentifier(email);
        clickNextButtonOnEmailLogin();
        new WebDriverWait(driver, 200)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("profileIdentifier")));
        return new SignInPasswordPage(driver);
    }

}