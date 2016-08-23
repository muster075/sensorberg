package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by katja on 18.08.2016
 */
public class LoginPage {

    private static WebElement element;

    private static By email_txtfld = By.name("email");
    private static By password_txtfld = By.name("password");
    private static By login_btn = By.xpath(".//button[@type='submit']");

    public static WebElement getEmail(WebDriver driver){
        element = driver.findElement(email_txtfld);
        return element;
    }

    public static WebElement getPassword(WebDriver driver){
        element = driver.findElement(password_txtfld);
        return element;
    }

    public static WebElement getLogin(WebDriver driver){
        element = driver.findElement(login_btn);
        return element;
    }

    public static void verifyPage(WebDriver driver) throws InterruptedException {
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(login_btn));
        Assert.assertTrue(driver.getCurrentUrl().contains("signin"));
    }

    public static void loginAsUser(WebDriver driver, String email, String password) throws InterruptedException {
        LoginPage.getEmail(driver).sendKeys(email);
        LoginPage.getPassword(driver).sendKeys(password);
        LoginPage.getLogin(driver).click();
        AccountPage.verifyPage(driver);
    }
}
