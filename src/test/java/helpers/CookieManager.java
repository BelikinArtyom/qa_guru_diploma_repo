package helpers;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

/**
 * Класс для управления cookies в тестах
 * Содержит методы для установки различных cookies, необходимых для корректной работы тестов
 */
public class CookieManager {

    // Константы для cookies
    private static final String SELECTED_CITY_COOKIE_NAME = "selected_city";
    private static final String DEFAULT_CITY_ID = "58c665588b6aa52311afa01b";

    /**
     * Устанавливает cookie для выбранного города (по умолчанию - Москва)
     * Эта cookie должна устанавливаться перед каждым тестом
     */
    @Step("Установить cookie для выбранного города: {cityId}")
    public static void setSelectedCityCookie(String cityId) {
        Cookie cityCookie = new Cookie(SELECTED_CITY_COOKIE_NAME, cityId);
        WebDriverRunner.getWebDriver().manage().addCookie(cityCookie);
    }

    /**
     * Устанавливает cookie для выбранного города с ID по умолчанию
     */
    @Step("Установить cookie для города по умолчанию")
    public static void setDefaultCityCookie() {
        setSelectedCityCookie(DEFAULT_CITY_ID);
    }

    /**
     * Удаляет cookie выбранного города
     */
    @Step("Удалить cookie выбранного города")
    public static void removeSelectedCityCookie() {
        WebDriverRunner.getWebDriver().manage().deleteCookieNamed(SELECTED_CITY_COOKIE_NAME);
    }

    /**
     * Проверяет, установлена ли cookie выбранного города
     */
    @Step("Проверить наличие cookie выбранного города")
    public static boolean isSelectedCityCookieSet() {
        Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed(SELECTED_CITY_COOKIE_NAME);
        return cookie != null;
    }

    /**
     * Получает значение cookie выбранного города
     */
    @Step("Получить значение cookie выбранного города")
    public static String getSelectedCityCookieValue() {
        Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed(SELECTED_CITY_COOKIE_NAME);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * Устанавливает все необходимые cookies для тестов
     * Этот метод должен вызываться в @BeforeEach каждого теста
     */
    @Step("Установить все необходимые cookies для тестов")
    public static void setRequiredCookiesForTests() {
        setDefaultCityCookie();
        // Здесь можно добавить другие необходимые cookies
    }

    /**
     * Очищает все cookies
     */
    @Step("Очистить все cookies")
    public static void clearAllCookies() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
    }

    /**
     * Устанавливает cookie с произвольным именем и значением
     */
    @Step("Установить cookie: {name} = {value}")
    public static void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }

    /**
     * Устанавливает cookie с дополнительными параметрами
     */
    @Step("Установить cookie с параметрами: {name} = {value}")
    public static void setCookie(String name, String value, String domain, String path) {
        Cookie cookie = new Cookie(name, value, domain, path);
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }

    // Константы для различных городов (можно расширять по необходимости)
    public static class CityIds {
        public static final String MOSCOW = "58c665588b6aa52311afa01b";
        public static final String SPB = "58c665588b6aa52311afa01c"; // Примерный ID для СПб
        public static final String KAZAN = "58c665588b6aa52311afa01d"; // Примерный ID для Казани
        
        // Методы для установки конкретных городов
        @Step("Установить cookie для Москвы")
        public static void setMoscowCookie() {
            setSelectedCityCookie(MOSCOW);
        }
        
        @Step("Установить cookie для Санкт-Петербурга")
        public static void setSPbCookie() {
            setSelectedCityCookie(SPB);
        }
        
        @Step("Установить cookie для Казани")
        public static void setKazanCookie() {
            setSelectedCityCookie(KAZAN);
        }
    }
}