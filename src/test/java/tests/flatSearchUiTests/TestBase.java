package tests.flatSearchUiTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.UUID;

public class TestBase {

    @BeforeAll
    public static void beforeAll() {

        String selenoidHost = System.getProperty("selenoid_host", "selenoid.autotests.cloud");
        String selenoidLogin = System.getProperty("selenoid_login", "user1");
        String selenoidPassword = System.getProperty("selenoid_password", "1234");
        String browserVersion = System.getProperty("browserVersion", "127.0");
        String BROWSER = System.getProperty("browser", "chrome");
        String BROWSER_SIZE = System.getProperty("browser.size", "2560x1440");

        Configuration.browser = BROWSER;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = BROWSER_SIZE;

        Configuration.baseUrl = "https://trendrealty.ru/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
                selenoidLogin,
                selenoidPassword,
                selenoidHost);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true,
                "name", "Test: " + UUID.randomUUID()
        ));
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        RestAssured.baseURI = "https://api.trendrealty.ru/";
    }
}