package tests.flatSearchUiTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
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
        
        // Читаем из системных свойств (передаются через gradle или Jenkins)
        String selenoidHost = System.getProperty("selenoid.host");
        String selenoidLogin = System.getProperty("selenoid.login");
        String selenoidPassword = System.getProperty("selenoid.password");
        Configuration.baseUrl = "https://trendrealty.ru/";
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
        
        Configuration.timeout = 10000;
        Configuration.pollingInterval = 200;
        Configuration.fastSetValue = false;
    }
}