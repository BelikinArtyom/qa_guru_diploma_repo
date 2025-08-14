package helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;

public class CookieManager {

    public CookieManager setCityForTest() {
        open("https://trendrealty.ru/images/favicons/favicon.ico");
        Cookie ck = new Cookie("selected_city", "58c665588b6aa52311afa01b");
        WebDriverRunner.getWebDriver().manage().addCookie(ck);
        return this;
    }
}