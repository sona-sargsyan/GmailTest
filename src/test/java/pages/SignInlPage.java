package pages;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInlPage extends BasePage {

    private static String SIGNINEMAILPAGE_URL = "https://gmail.com";

    public SignInlPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "next")
    private WebElement nextButton;

    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement passwordIdentifier;

    public SignInlPage openSignInPage() {
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

    public InboxPage login(User user) {
        fillEmailIdentifier(user.getUsername());
        clickNextButtonOnEmailLogin();
        fillPasswordIdentifier(user.getPassword());
        clickNextButtonOnPasswordIdentifier();
        return new InboxPage(driver);
    }
}

