package tests.flatSearchUiTests;

import helpers.CookieManager;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.FilterSearchPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class FilterSearchTest extends TestBase {

    private final FilterSearchPage filterSearchPage = new FilterSearchPage();
    private final CookieManager cookieManager = new CookieManager();

    @Test
    @Story("Поиск недвижимости")
    @DisplayName("Проверка ввода текста в поле поиска")
    @Description("Тест проверяет возможность ввода текста в поле поиска и его получения")
    void testSearchFieldInput() {

        open(baseUrl);
        cookieManager.setCityForTest();
        String searchText = "ЖК Московский";
        filterSearchPage.enterSearchText(searchText);
        assertEquals(searchText, filterSearchPage.getSearchText(),
                "Введенный текст должен совпадать с полученным из поля поиска");
    }
}