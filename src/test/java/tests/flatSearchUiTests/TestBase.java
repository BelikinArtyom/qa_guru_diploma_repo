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

        String BROWSER = System.getProperty("browser", "chrome");
        String BROWSER_SIZE = System.getProperty("browser.size", "2560x1440");
        Configuration.browser = BROWSER;
        Configuration.browserSize = BROWSER_SIZE;

        Configuration.baseUrl = "https://trendrealty.ru/";
//        Configuration.pageLoadStrategy = "eager";
//        Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
//                selenoidLogin,
//                selenoidPassword,
//                selenoidHost);
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.of(
//                "enableVNC", true,
//                "enableVideo", true,
//                "name", "Test: " + UUID.randomUUID()
//        ));
//        Configuration.browserCapabilities = capabilities;
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

//        RestAssured.baseURI = "https://api.trendrealty.ru/";
    }
}