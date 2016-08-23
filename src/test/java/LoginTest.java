import org.junit.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.AccountPage;
import pageObjects.LoginPage;
import java.io.IOException;

import static java.lang.Thread.sleep;


/**
 * Created by katja on 20.08.2016
 */
public class LoginTest extends SensorbergTest {

    WebDriverWait wait;
    public LoginTest() throws IOException {
    }

    @Before
    public void setup() throws IOException {
        super.setup();
    }

    @After
    public void teardown() {
        super.teardown();
    }

    @Test
    public void testLoginWithValidCredentials() throws InterruptedException, IOException {
        LoginPage.loginAsUser(driver, loadProperties.getProperies("EMAIL_KK"), loadProperties.getProperies("PASSWORD_KK"));
        AccountPage.getLogout(driver).click();
        LoginPage.verifyPage(driver);
    }
}