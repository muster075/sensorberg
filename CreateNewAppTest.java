import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.AccountPage;
import pageObjects.AppAddPage;
import pageObjects.LoginPage;

import java.io.IOException;
import java.net.MalformedURLException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by katja on 20.08.2016
 */
public class CreateNewAppTest extends SensorbergTest{

    private WebDriverWait wait = new WebDriverWait(driver, 10);

    private static By cell_name = By.xpath(".//*[@data-e2e='table-applications']/tbody//td[2]");
    private final static String TEST_APP_NAME = "test app 03";
    private final static String OS_IOS = "ios";

    public CreateNewAppTest() throws MalformedURLException {
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
    public void testCreateNewApp_ios() throws IOException, InterruptedException {
        LoginPage.loginAsUser(driver, loadProperties.getProperies("EMAIL_KK"), loadProperties.getProperies("PASSWORD_KK"));

        AppAddPage.createNewApp(driver, TEST_APP_NAME, OS_IOS);
        AccountPage.checkToastMsg(driver, "App wurde erfolgreich hinzugefügt");

        wait.until(ExpectedConditions.presenceOfElementLocated(cell_name));
        Assert.assertEquals(TEST_APP_NAME, driver.findElement(cell_name).getText());

        //test new API
        given()
            .header("X-Auth-Token", "7556c7f02f1ac7c7dd5c931b1c032eb38d23f83e45f75e642df9455679ced78c").
        when()
                .get(loadProperties.getProperies("API.BASEURI")+loadProperties.getProperies("API.APPLICATIONS")).
        then()
            .body("name", contains(TEST_APP_NAME))
//          .body("apiKey", contains("5b887c5d7618168a8599ca702fb22734679bee4307490f25b58a679966cc5455"))
            .statusCode(200);
    }

}
