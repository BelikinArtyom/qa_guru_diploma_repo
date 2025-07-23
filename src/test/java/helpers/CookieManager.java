package helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

public class CookieManager {

    public CookieManager setCityForTest() {
        Cookie ck = new Cookie("selected_city", "58c665588b6aa52311afa01b");
        WebDriverRunner.getWebDriver().manage().addCookie(ck);
        return this;
    }
}