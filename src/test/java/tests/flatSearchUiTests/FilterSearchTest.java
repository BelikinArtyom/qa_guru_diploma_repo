package tests.flatSearchUiTests;

import helpers.CookieManager;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.FilterSearchPage;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterSearchTest extends TestBase {

    private final FilterSearchPage filterSearchPage = new FilterSearchPage();
    private final CookieManager cookieManager = new CookieManager();

    @Test
    @Story("Фильтрация недвижимости")
    @DisplayName("Проверка установки значений фильтров и отображения тегов")
    @Description("Тест проверяет установку значений в фильтрах и соответствие отображаемых тегов выбранным значениям")
    void FilterValuesAndTagsTest() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
        });

        step("Установка города для тестирования", () -> {
            cookieManager.setCityForTest();
        });

        step("Установка значений фильтров", () -> {
            filterSearchPage.setDistrict(TestData.DISTRICT_ADMIRALTY)
                    .setMetro(TestData.METRO_ADMIRALTY)
                    .setRooms(TestData.ROOMS_2)
                    .setPriceRange(TestData.PRICE_FROM, TestData.PRICE_TO)
                    .setDeadline(TestData.DEADLINE);
        });

        step("Проверка отображения тегов фильтров", () -> {
            List<String> actualTags = filterSearchPage.getFilterTags();
            List<String> expectedTags = TestData.getExpectedAdmiraltyTags();
            assertTrue(actualTags.containsAll(expectedTags),
                    "Все выбранные значения фильтров должны отображаться в виде тегов");
        });
    }

    @Test
    @Story("Расширенные фильтры")
    @DisplayName("Проверка открытия расширенных фильтров и их названий")
    @Description("Тест проверяет открытие расширенных фильтров и соответствие названий фильтров ожидаемым значениям")
    void ExtendedFiltersTest() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
        });

        step("Установка города для тестирования", () -> {
            cookieManager.setCityForTest();
        });

        step("Открытие расширенных фильтров", () -> {
            filterSearchPage.clickAllFiltersButton();
        });

        step("Проверка отображения расширенных фильтров", () -> {
            List<String> actualFilters = filterSearchPage.getAllFilterNames();
            List<String> expectedFilters = TestData.getExpectedExtendedFilters();
            assertTrue(actualFilters.containsAll(expectedFilters),
                    "Все расширенные фильтры должны отображаться с правильными названиями");
        });
    }

    @Test
    @Story("Фильтрация недвижимости")
    @DisplayName("Проверка установки значений фильтров, отображения тегов и их сброса")
    @Description("Тест проверяет установку значений в фильтрах, соответствие отображаемых тегов выбранным значениям и их удаление")
    void FilterValuesAndTagsWithResetTest() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
        });

        step("Установка города для тестирования", () -> {
            cookieManager.setCityForTest();
        });

        step("Установка значений фильтров", () -> {
            filterSearchPage.setDistrict(TestData.DISTRICT_ADMIRALTY)
                    .setMetro(TestData.METRO_ADMIRALTY)
                    .setRooms(TestData.ROOMS_2)
                    .setPriceRange(TestData.PRICE_FROM, TestData.PRICE_TO)
                    .setDeadline(TestData.DEADLINE);
        });

        step("Проверка отображения тегов фильтров", () -> {
            List<String> actualTags = filterSearchPage.getFilterTags();
            List<String> expectedTags = TestData.getExpectedAdmiraltyTags();
            assertTrue(actualTags.containsAll(expectedTags),
                    "Все выбранные значения фильтров должны отображаться в виде тегов");
        });

        step("Сброс всех фильтров", () -> {
            filterSearchPage.clickResetAllButton();
        });

        step("Проверка отсутствия тегов после сброса", () -> {
            int tagsCount = filterSearchPage.getTagsCount();
            assertTrue(tagsCount == 0,
                    "После нажатия 'Сбросить все' теги должны отсутствовать");
        });
    }

    @Test
    @Story("Поиск недвижимости")
    @DisplayName("Проверка ввода текста в поле поиска и его очистки")
    @Description("Тест проверяет возможность ввода текста в поле поиска, его получения и очистки через кнопку крестика")
    void SearchFieldClearTest() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
        });

        step("Установка города для тестирования", () -> {
            cookieManager.setCityForTest();
        });

        step("Ввод текста в поле поиска", () -> {
            filterSearchPage.enterSearchText(TestData.TEST_SEARCH_TEXT);
        });

        step("Проверка введенного текста", () -> {
            assertEquals(TestData.TEST_SEARCH_TEXT, filterSearchPage.getSearchText(),
                    "Введенный текст должен соответствовать полученному из поля поиска");
        });

        step("Очистка поля поиска", () -> {
            filterSearchPage.clearSearch();
        });

        step("Проверка пустого поля поиска", () -> {
            String searchFieldValue = filterSearchPage.getSearchFieldValue();
            assertTrue(searchFieldValue.isEmpty(),
                    "После нажатия на кнопку очистки поле поиска должно быть пустым");
        });
    }

    @Test
    @Story("Фильтрация недвижимости")
    @DisplayName("Проверка отображения установленных значений фильтров")
    @Description("Тест проверяет установку значений в фильтрах и соответствие отображаемого текста установленным значениям")
    void testFilterValuesDisplay() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
        });

        step("Установка города для тестирования", () -> {
            cookieManager.setCityForTest();
        });

        step("Установка значений фильтров", () -> {
            filterSearchPage.setRooms(TestData.ROOMS_2)
                    .setPriceRange(TestData.PRICE_FROM, TestData.PRICE_TO)
                    .setDeadline(TestData.DEADLINE);
        });

        step("Проверка отображения значений в фильтрах", () -> {
            assertTrue(filterSearchPage.getRoomsPlaceholder().equals("2-комнатная") &&
                    filterSearchPage.getPricePlaceholder().equals("от Цена от, ₽ 10 000 000 до Цена до, ₽ 20 000 000 ₽") &&
                    filterSearchPage.getDeadlinePlaceholder().equals("до 2027 г."),
                    "Отображаемые тексты в фильтрах должны точно соответствовать установленным значениям");
        });
    }

    @Test
    @Story("Фильтрация недвижимости")
    @DisplayName("Проверка установки значений фильтров, отображения тегов и их удаления по одному")
    @Description("Тест проверяет установку значений в фильтрах, соответствие отображаемых тегов выбранным значениям и их удаление по одному")
    void testFilterValuesAndTagsWithIndividualReset() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
        });

        step("Установка города для тестирования", () -> {
            cookieManager.setCityForTest();
        });

        step("Установка значений фильтров", () -> {
            filterSearchPage.setDistrict(TestData.DISTRICT_ADMIRALTY)
                    .setMetro(TestData.METRO_ADMIRALTY)
                    .setRooms(TestData.ROOMS_2)
                    .setPriceRange(TestData.PRICE_FROM, TestData.PRICE_TO)
                    .setDeadline(TestData.DEADLINE);
        });

        step("Проверка отображения тегов фильтров", () -> {
            List<String> actualTags = filterSearchPage.getFilterTags();
            List<String> expectedTags = TestData.getExpectedAdmiraltyTags();
            assertTrue(actualTags.containsAll(expectedTags),
                    "Все выбранные значения фильтров должны отображаться в виде тегов");
        });
        
        step("Удаление всех тегов по одному", () -> {
            filterSearchPage.removeAllTagsIndividually();
        });

        step("Проверка отсутствия тегов после удаления", () -> {
            int tagsCount = filterSearchPage.getTagsCount();
            assertTrue(tagsCount == 0,
                    "После удаления всех тегов по одному теги должны отсутствовать");
        });
    }
}