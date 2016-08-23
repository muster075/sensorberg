package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static pageObjects.AppMgmtPage.addApp_header_link;

/**
 * Created by katja on 19.08.2016
 */
public class AppAddPage {
    private static WebElement element;

    private static By name_txtfld = By.name("name");
    private static By save_btn = By.xpath(".//button[text()='Speichern']");


    public static WebElement getName(WebDriver driver){
        element = driver.findElement(name_txtfld);
        return element;
    }

    public static WebElement getOs(WebDriver driver, String os){
        element = driver.findElement(By.xpath(String.format(".//input[@value='%s']", os)));
        return element;
    }

    public static WebElement getSave(WebDriver driver){
        element = driver.findElement(save_btn);
        return element;
    }

    public static void createNewApp(WebDriver driver, String appName, String os) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(addApp_header_link));
        AppMgmtPage.getAddAppHeader(driver).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(name_txtfld));
        AppAddPage.getName(driver).sendKeys(appName);
        AppAddPage.getOs(driver, os).click();
        AppAddPage.getSave(driver).click();
    }
}
