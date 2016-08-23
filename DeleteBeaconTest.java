import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.AccountPage;
import pageObjects.BeaconMgmtPage;
import pageObjects.LoginPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by katja on 20.08.2016
 */
public class DeleteBeaconTest extends SensorbergTest {

    private WebDriverWait wait = new WebDriverWait(driver, 10);
    // Variable
    private final static String TEST_BEACON_NAME = "test beacon 03";
    // XPath
    private static By beacon_table = By.xpath(".//table[@data-e2e='table-beacon']");

    public DeleteBeaconTest() throws IOException {
    }

    @Before
    public void setup() throws IOException {
        super.setup();
    }

    @After
    public void teardown() {
        super.teardown();
    }


    @org.junit.Test
    public void testDeleteAppFromList() throws InterruptedException, IOException {
        LoginPage.loginAsUser(driver, loadProperties.getProperies("EMAIL_KK"), loadProperties.getProperies("PASSWORD_KK"));
        wait.until(ExpectedConditions.presenceOfElementLocated(AccountPage.usermenu_link));

        AccountPage.getBeacon(driver).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(beacon_table));
        BeaconMgmtPage.getSelectItem(driver, TEST_BEACON_NAME).click();
        BeaconMgmtPage.getDeleteBeaconPerBulk(driver).click();

        AccountPage.confirmDeleteDlg(driver, TEST_BEACON_NAME);
        AccountPage.checkToastMsg(driver, "Ein Beacon wurde gelöscht");

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        Assert.assertTrue("The deleted item is still in der list", !driver.getPageSource().contains(TEST_BEACON_NAME));
    }
}
