package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by katja on 19.08.2016
 */
public class AppMgmtPage extends AccountPage {
    private static WebElement element;

    protected static By addApp_header_link = By.xpath(".//h3//a[@data-e2e='applications-link-add']");
    private static By deleteApp_bulk_link = By.xpath(".//a[text()='Löschen']");
    private static String selectItem_chkbx = ".//input[contains(@title, '%s')]";  //input: title="Wähle "test app 01" für Listenoperation aus"

    public static WebElement getAddAppHeader(WebDriver driver){
        element = driver.findElement(addApp_header_link);
        return element;
    }

    public static WebElement getSelectItem(WebDriver driver, String appName) {
        element = driver.findElement(By.xpath(String.format(selectItem_chkbx, appName)));
        return element;
    }

    public static WebElement getDeleteAppPerBulk(WebDriver driver) {
        element = driver.findElement(deleteApp_bulk_link);
        return element;
    }
}
