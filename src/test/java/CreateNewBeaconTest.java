import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;

import java.io.IOException;
import java.net.MalformedURLException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;

/**
 * Created by katja on 20.08.2016
 */
public class CreateNewBeaconTest extends SensorbergTest {

    private static By cell_name = By.xpath(".//*[@data-e2e='table-beacon']/tbody//td[2]");
    private final static String TEST_BEACON_NAME = "test beacon 03";

    public CreateNewBeaconTest() throws MalformedURLException {
    }

    @Before
    public void setup() throws IOException, InterruptedException {
        super.setup();
    }

    @After
    public void teardown() {
        super.teardown();
    }

    @Test
    public void testCreateNewBeacon() throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        AccountPage.getBeacon(driver).click();
        BeaconAddPage.createNewBeacon(driver, TEST_BEACON_NAME);
        AccountPage.checkToastMsg(driver, "Beacon erfolgreich hinzugefügt");

        wait.until(ExpectedConditions.presenceOfElementLocated(cell_name));
        Assert.assertEquals(TEST_BEACON_NAME, driver.findElement(cell_name).getText());

        //test new API
//        String token = loginAndGetToken();
        given()
            .header("X-Auth-Token", "f249d101609a99543a8325272e181c4ea4945b509cec3ff7f2e1d7fe23fa12dd").
        when()
            .get(loadProperties.getProperies("API.BASEURI")+loadProperties.getProperies("API.BEACONS")).
        then()
            .body("name", contains(TEST_BEACON_NAME))
//          .body("apiKey", contains("5b887c5d7618168a8599ca702fb22734679bee4307490f25b58a679966cc5455"))
            .statusCode(200);
    }
}