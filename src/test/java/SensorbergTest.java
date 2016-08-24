import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageObjects.AccountPage;
import pageObjects.LoginPage;
import utils.LoadProperties;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by katja on 19.08.2016
 */
public class SensorbergTest {
    protected WebDriver driver = new RemoteWebDriver(
            new URL("http://localhost:4444/wd/hub"),
            DesiredCapabilities.firefox());

    protected LoadProperties loadProperties = new LoadProperties();

    public SensorbergTest() throws MalformedURLException {
    }

    @Before
    public void setup() throws IOException, InterruptedException {
        driver.get(loadProperties.getProperies("LOGIN_URL"));
        driver.manage().window().maximize();
        LoginPage.loginAsUser(driver, loadProperties.getProperies("EMAIL_MM"), loadProperties.getProperies("PASSWORD_MM"));
        AccountPage.verifyPage(driver);
    }

    @After
    public void teardown() {
        driver.quit();
    }


    public String loginAndGetToken() throws IOException {
        Response response = RestAssured.given()
                .formParam("username", loadProperties.getProperies("EMAIL_KK"))
                .formParam("password", loadProperties.getProperies("PASSWORT_KK"))
                .request().post(loadProperties.getProperies("LOGIN_URL"));
        response.then().contentType(ContentType.JSON).statusCode(200);
        response.then().contentType(ContentType.JSON).body("token", Matchers.notNullValue());
        response.then().contentType(ContentType.JSON).body("token", Matchers.containsString("."));
        //get JsonWebToken from response:
        String token = response.body().jsonPath().get("token");
        return token;
    }
}
