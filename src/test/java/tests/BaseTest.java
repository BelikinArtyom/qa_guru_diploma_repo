package tests;

import com.codeborne.selenide.Configuration;
import helpers.CookieManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

/**
 * Базовый класс для всех тестов
 * Содержит общую настройку браузера и установку необходимых cookies
 */
public abstract class BaseTest {

    protected static final String BASE_URL = "https://trendrealty.ru/";

    @BeforeAll
    static void globalSetUp() {
        configureBrowser();
    }

    @BeforeEach
    void setUp() {
        openPageAndSetCookies();
    }

    /**
     * Настройка конфигурации браузера
     */
    @Step("Настройка конфигурации браузера")
    private static void configureBrowser() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.baseUrl = BASE_URL;
        
        // Дополнительные настройки для стабильности тестов
        Configuration.holdBrowserOpen = false;
        Configuration.reopenBrowserOnFail = false;
        Configuration.fastSetValue = true;
    }

    /**
     * Открытие страницы и установка необходимых cookies
     */
    @Step("Открытие страницы и установка cookies")
    private void openPageAndSetCookies() {
        // Открываем страницу перед установкой cookies
        open(BASE_URL);
        
        // Устанавливаем необходимые cookies
        CookieManager.setRequiredCookiesForTests();
        
        // Обновляем страницу после установки cookies для их применения
        open(BASE_URL);
    }

    /**
     * Метод для установки конкретного города в cookie
     * Можно переопределить в наследуемых классах для тестирования конкретных городов
     */
    @Step("Установка cookie для конкретного города: {cityId}")
    protected void setCityForTest(String cityId) {
        CookieManager.setSelectedCityCookie(cityId);
        // Обновляем страницу для применения новой cookie
        open(BASE_URL);
    }

    /**
     * Метод для очистки всех cookies (может понадобиться в некоторых тестах)
     */
    @Step("Очистка всех cookies")
    protected void clearAllCookies() {
        CookieManager.clearAllCookies();
    }
}