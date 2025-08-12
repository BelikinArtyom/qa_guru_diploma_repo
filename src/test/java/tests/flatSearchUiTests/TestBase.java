package tests.flatSearchUiTests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void beforeAll() {

        String BROWSER = System.getProperty("browser", "chrome");
        String BROWSER_SIZE = System.getProperty("browser.size", "2560x1440");
        Configuration.browser = BROWSER;
        Configuration.browserSize = BROWSER_SIZE;
        String selenoidHost = System.getProperty("selenoid_host", "selenoid.autotests.cloud");
        String selenoidLogin = System.getProperty("selenoid_login", "user1");
        String selenoidPassword = System.getProperty("selenoid_password", "1234");
        Configuration.baseUrl = "https://trendrealty.ru/";
//        Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
//          selenoidLogin,
//          selenoidPassword,
//          selenoidHost);
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.of(
//          "enableVNC", true,
//          "enableVideo", true,
//          "name", "Test: " + UUID.randomUUID()
//        ));
//        Configuration.browserCapabilities = capabilities;

        // Добавляем AllureSelenide listener только один раз
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        
        Configuration.timeout = 10000;
        Configuration.pollingInterval = 200;
        Configuration.fastSetValue = false;
    }
}