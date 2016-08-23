import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.AccountPage;
import pageObjects.AppMgmtPage;
import pageObjects.LoginPage;

import java.io.IOException;


/**
 * Created by katja on 20.08.2016
 */
public class DeleteNewAppTest extends SensorbergTest {

    private WebDriverWait wait = new WebDriverWait(driver, 10);

    private final static String TEST_APP_NAME = "test app 03";

    private static By app_table = By.xpath(".//table[@data-e2e='table-applications']");

    public DeleteNewAppTest() throws IOException {
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
        AccountPage.getApp(driver).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(app_table));
        AppMgmtPage.getSelectItem(driver, TEST_APP_NAME).click();
        AppMgmtPage.getDeleteAppPerBulk(driver).click();

        AccountPage.confirmDeleteDlg(driver, TEST_APP_NAME);
        AccountPage.checkToastMsg(driver, "Eine App wurde gelöscht");

        Assert.assertTrue("Deleted item still present in table", !driver.getPageSource().contains(TEST_APP_NAME));
    }
}
