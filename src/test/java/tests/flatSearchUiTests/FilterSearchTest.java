package tests.flatSearchUiTests;

import helpers.CookieManager;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.FilterSearchPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterSearchTest extends TestBase {

    private final FilterSearchPage filterSearchPage = new FilterSearchPage();
    private final CookieManager cookieManager = new CookieManager();

    @Test
    @Story("Фильтрация недвижимости")
    @DisplayName("Проверка установки значений фильтров и отображения тегов")
    @Description("Тест проверяет установку значений в фильтрах и соответствие отображаемых тегов выбранным значениям")
    void testFilterValuesAndTags() {
        io.qameta.allure.Allure.step("Выполнение теста фильтрации", () -> {
            open(baseUrl);
            cookieManager.setCityForTest();

            filterSearchPage.setDistrict(FilterSearchPage.DISTRICT_ADMIRALTY)
                    .setMetro(FilterSearchPage.METRO_ADMIRALTY)
                    .setRooms(FilterSearchPage.ROOMS_2)
                    .setPriceRange(FilterSearchPage.PRICE_FROM, FilterSearchPage.PRICE_TO)
                    .setDeadline(FilterSearchPage.DEADLINE_2027);

            assertTrue(filterSearchPage.checkFilterTags(),
                    "Все выбранные значения фильтров должны отображаться в виде тегов");
        });
    }

    @Test
    @Story("Расширенные фильтры")
    @DisplayName("Проверка открытия расширенных фильтров и их названий")
    @Description("Тест проверяет открытие расширенных фильтров и соответствие названий фильтров ожидаемым значениям")
    void testExtendedFilters() {
        io.qameta.allure.Allure.step("Выполнение теста расширенных фильтров", () -> {
            open(baseUrl);
            cookieManager.setCityForTest();

            filterSearchPage.clickAllFiltersButton();

            assertTrue(filterSearchPage.checkExtendedFilters(),
                    "Все расширенные фильтры должны отображаться с правильными названиями");
        });
    }

    @Test
    @Story("Фильтрация недвижимости")
    @DisplayName("Проверка установки значений фильтров, отображения тегов и их сброса")
    @Description("Тест проверяет установку значений в фильтрах, соответствие отображаемых тегов выбранным значениям и их удаление")
    void testFilterValuesAndTagsWithReset() {
        io.qameta.allure.Allure.step("Выполнение теста фильтрации со сбросом", () -> {
            open(baseUrl);
            cookieManager.setCityForTest();

            filterSearchPage.setDistrict(FilterSearchPage.DISTRICT_ADMIRALTY)
                    .setMetro(FilterSearchPage.METRO_ADMIRALTY)
                    .setRooms(FilterSearchPage.ROOMS_2)
                    .setPriceRange(FilterSearchPage.PRICE_FROM, FilterSearchPage.PRICE_TO)
                    .setDeadline(FilterSearchPage.DEADLINE_2027);

            assertTrue(filterSearchPage.checkFilterTags(),
                    "Все выбранные значения фильтров должны отображаться в виде тегов");
            filterSearchPage.clickResetAllButton();

            assertTrue(filterSearchPage.areTagsEmpty(),
                    "После нажатия 'Сбросить все' теги должны отсутствовать");
        });
    }

    @Test
    @Story("Поиск недвижимости")
    @DisplayName("Проверка ввода текста в поле поиска и его очистки")
    @Description("Тест проверяет возможность ввода текста в поле поиска, его получения и очистки через кнопку крестика")
    void testSearchFieldClear() {
        io.qameta.allure.Allure.step("Выполнение теста очистки поля поиска", () -> {
            open(baseUrl);
            cookieManager.setCityForTest();

            filterSearchPage.enterSearchText(FilterSearchPage.TEST_SEARCH_TEXT);
            assertEquals(FilterSearchPage.TEST_SEARCH_TEXT, filterSearchPage.getSearchText(),
                    "Введенный текст должен соответствовать полученному из поля поиска");

            filterSearchPage.clearSearch();
            assertTrue(filterSearchPage.isSearchFieldEmpty(),
                    "После нажатия на кнопку очистки поле поиска должно быть пустым");
        });
    }
}