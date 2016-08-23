package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static pageObjects.BeaconMgmtPage.addBeacon_header_link;

/**
 * Created by katja on 19.08.2016
 */
public class BeaconAddPage {
    private static WebElement element;
    public static WebDriverWait wait;

    private static By name_txtfld = By.name("name");
    private static By save_btn = By.xpath(".//button[text()='Speichern']");


    public static WebElement name_textfld(WebDriver driver){
        element = driver.findElement(name_txtfld);
        return element;
    }

    public static WebElement save_btn(WebDriver driver){
        element = driver.findElement(save_btn);
        return element;
    }

    public static void createNewBeacon(WebDriver driver, String appName) throws InterruptedException {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(addBeacon_header_link));
        BeaconMgmtPage.getAddBeacon(driver).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(name_txtfld));
        BeaconAddPage.name_textfld(driver).sendKeys(appName);
        BeaconAddPage.save_btn(driver).click();
    }

    public void verifyPage(WebDriver driver) {
        Assert.assertTrue(driver.getCurrentUrl().contains("beacon/add"));
    }
}