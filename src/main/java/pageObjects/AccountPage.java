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
public class AccountPage {

    private static WebElement element;
    private static WebDriverWait wait;

    protected static By logout_link = By.xpath(".//a[@ng-click='signOut()']");
    public static By usermenu_link = By.xpath(".//li[@ng-if='signedIn']");
    private static By toastMesssage = By.xpath(".//div[@class='toast-message']");

    private static By deleteConfirmDlg = By.xpath("//div[@class='modal-dialog']");
    private static By deleteConfirmDlg_msg = By.xpath("//div[@class='modal-body ng-scope']");
    private static By deleteConfirmDlg_btn = By.xpath("//button[text()='Löschen']");

    // side-menu
    private static By app_link = By.xpath("//li[contains(@class, 'applications-tab')]/a");
    private static By beacon_link = By.xpath("//li[contains(@class, 'beacons-tab')]/a");


    public static WebElement getUsermenu(WebDriver driver){
        element = driver.findElement(usermenu_link);
        return element;
    }

    public static WebElement getLogout(WebDriver driver){
        getUsermenu(driver).click();
        element = driver.findElement(logout_link);
        return element;
    }

    public static WebElement getApp(WebDriver driver){
        element = driver.findElement(app_link);
        return element;
    }
    public static WebElement getBeacon(WebDriver driver){
        element = driver.findElement(beacon_link);
        return element;
    }

    public static WebElement getDeleteItemConfirmDlgMsg(WebDriver driver) {
        element = driver.findElement(deleteConfirmDlg_msg);
        return element;
    }

    public static WebElement getDeleteItemConfirmDlg(WebDriver driver) {
        element = driver.findElement(deleteConfirmDlg_btn);
        return element;
    }

    public static void verifyPage(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(usermenu_link));
        Assert.assertTrue(driver.getCurrentUrl().contains("applications"));
    }

    public static void checkToastMsg(WebDriver driver, String toastMsgText) {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(toastMesssage));
        Assert.assertEquals(toastMsgText, driver.findElement(toastMesssage).getText());
    }

    public static void confirmDeleteDlg(WebDriver driver, String itemName) {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(deleteConfirmDlg));
        if (getDeleteItemConfirmDlgMsg(driver).getText()
                .equals(String.format("Wollen Sie \"%s\" wirklich löschen?", itemName)))
            getDeleteItemConfirmDlg(driver).click();
    }
}
