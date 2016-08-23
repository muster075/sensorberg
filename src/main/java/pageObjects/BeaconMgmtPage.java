package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by katja on 19.08.2016
 */
public class BeaconMgmtPage extends AccountPage {
    private static WebElement element;

    protected static By addBeacon_header_link = By.xpath(".//h3//a[@data-e2e='beacon-link-add']");
    private static String selectItem_chkbx = ".//input[contains(@title, '%s')]";
    private static By deleteBeacon_bulk_link = By.xpath(".//a[text()='Löschen']");


    public static WebElement getAddBeacon(WebDriver driver){
        element = driver.findElement(addBeacon_header_link);
        return element;
    }

    public static WebElement getSelectItem(WebDriver driver, String appName) {
        element = driver.findElement(By.xpath(String.format(selectItem_chkbx, appName)));
        return element;
    }

    public static WebElement getDeleteBeaconPerBulk(WebDriver driver) {
        element = driver.findElement(deleteBeacon_bulk_link);
        return element;
    }
}