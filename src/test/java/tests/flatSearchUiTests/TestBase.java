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
        Configuration.baseUrl = "https://trendrealty.ru/";
        
        Configuration.timeout = 10000;
        Configuration.pollingInterval = 200; 
        Configuration.reportsFolder = "build/selenide-reports";
        
        Configuration.fastSetValue = false; 
    }
}