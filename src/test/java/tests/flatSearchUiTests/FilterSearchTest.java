package tests.flatSearchUiTests;

import helpers.Attach;
import helpers.CookieManager;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import pages.FilterSearchPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

public class FilterSearchTest extends TestBase {
    
    @AfterEach
    void attach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.getVideoUrl();
        Attach.addVideo();
    }

    private final FilterSearchPage filterSearchPage = new FilterSearchPage();
    private final CookieManager cookieManager = new CookieManager();

    @Test
    @Tag("Smoke")
    @Owner("belikinA")
    @Story("Фильтрация недвижимости")
    @DisplayName("Проверка установки значений фильтров и отображения тегов")
    @Description("Установка значений в фильтрах и соответствие отображаемых тегов выбранным значениям")
    void filterValuesAndTagsTest() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
            cookieManager.setCityForTest();
        });

        step("Установка значений фильтров", () -> {
            filterSearchPage.setDistrict("Адмиралтейский")
                    .setMetro("Адмиралтейская")
                    .setRooms("2-комнатная")
                    .setPriceRange("10000000", "20000000")
                    .setDeadline("до 2027 г.");
        });

        step("Проверка отображения тегов фильтров", () -> {
            var tags = filterSearchPage.getFilterTags();
            assertTrue(tags.contains("2-комнатная"), "Тег '2-комнатная' должен присутствовать");
            assertTrue(tags.contains("м. Адмиралтейская"), "Тег 'м. Адмиралтейская' должен присутствовать");
        });
    }

    @Test
    @Tag("Smoke")
    @Owner("belikinA")
    @Story("Расширенные фильтры")
    @DisplayName("Проверка открытия расширенных фильтров и их названий")
    @Description("Открытие расширенных фильтров и соответствие названий фильтров ожидаемым значениям")
    void extendedFiltersTest() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
            cookieManager.setCityForTest();
        });

        step("Открытие расширенных фильтров", () -> {
            filterSearchPage.clickAllFiltersButton();
        });

        step("Проверка отображения расширенных фильтров", () -> {
            var filters = filterSearchPage.getAllFilterNames();
            assertEquals(11, filters.size(), "Количество расширенных фильтров должно быть равно 11");
            assertTrue(filters.contains("Расстояние до метро"), "Фильтр 'Расстояние до метро' должен присутствовать");
            assertTrue(filters.contains("Прописка"), "Фильтр 'Прописка' должен присутствовать");
            assertTrue(filters.contains("Площадь от-до, м²"), "Фильтр 'Площадь от-до, м²' должен присутствовать");
            assertTrue(filters.contains("Площадь кухни от-до, м²"), "Фильтр 'Площадь кухни от-до, м²' должен присутствовать");
            assertTrue(filters.contains("Площадь балкона от-до, м²"), "Фильтр 'Площадь балкона от-до, м²' должен присутствовать");
            assertTrue(filters.contains("Отделка"), "Фильтр 'Отделка' должен присутствовать");
            assertTrue(filters.contains("Этаж"), "Фильтр 'Этаж' должен присутствовать");
            assertTrue(filters.contains("Санузел"), "Фильтр 'Санузел' должен присутствовать");
            assertTrue(filters.contains("Способ оплаты"), "Фильтр 'Способ оплаты' должен присутствовать");
            assertTrue(filters.contains("Апартаменты"), "Фильтр 'Апартаменты' должен присутствовать");
            assertTrue(filters.contains("Год постройки"), "Фильтр 'Год постройки' должен присутствовать");
        });
    }

    @Test
    @Tag("Smoke")
    @Owner("belikinA")
    @Story("Фильтрация недвижимости")
    @DisplayName("Проверка установки значений фильтров, отображения тегов и их сброса")
    @Description("Установка значений в фильтрах, соответствие отображаемых тегов выбранным значениям и их удаление")
    void filterValuesAndTagsWithResetTest() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
            cookieManager.setCityForTest();
        });

        step("Установка значений фильтров", () -> {
            filterSearchPage.setDistrict("Адмиралтейский")
                    .setMetro("Адмиралтейская")
                    .setRooms("2-комнатная")
                    .setPriceRange("10000000", "20000000")
                    .setDeadline("до 2027 г.");
        });

        step("Проверка отображения тегов фильтров", () -> {
            var tags = filterSearchPage.getFilterTags();
         //   assertEquals(5, tags.size(), "Количество тегов должно быть равно 5");
            assertTrue(tags.contains("2-комнатная"), "Тег '2-комнатная' должен присутствовать");
            assertTrue(tags.contains("м. Адмиралтейская"), "Тег 'м. Адмиралтейская' должен присутствовать");
        });

        step("Сброс всех фильтров", () -> {
            filterSearchPage.clickResetAllButton();
        });

        step("Проверка отсутствия тегов после сброса", () -> {
            assertEquals(0, filterSearchPage.getTagsCount(), "После сброса всех фильтров теги должны отсутствовать");
        });
    }

    @Test
    @Tag("Smoke")
    @Owner("belikinA")
    @Story("Поиск недвижимости")
    @DisplayName("Проверка ввода текста в поле поиска и его очистки")
    @Description("Ввод текста в поле поиска, его получение и очистка через кнопку крестика")
    void searchFieldClearTest() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
            cookieManager.setCityForTest();
        });

        step("Ввод текста в поле поиска", () -> {
            filterSearchPage.enterSearchText("Тестовый ЖК");
        });

        step("Проверка введенного текста", () -> {
            assertEquals("Тестовый ЖК", filterSearchPage.getSearchText(), "Введенный текст должен соответствовать полученному");
        });

        step("Очистка поля поиска", () -> {
            filterSearchPage.clearSearch();
        });

        step("Проверка пустого поля поиска", () -> {
            assertTrue(filterSearchPage.getSearchText().isEmpty(), "После очистки поле поиска должно быть пустым");
        });
    }

    @Test
    @Tag("Smoke")
    @Owner("belikinA")
    @Story("Фильтрация недвижимости")
    @DisplayName("Проверка отображения установленных значений фильтров")
    @Description("Установка значений в фильтрах и соответствие отображаемого текста установленным значениям")
    void FilterValuesDisplayTest() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
            cookieManager.setCityForTest();
        });

        step("Установка значений фильтров", () -> {
            filterSearchPage.setRooms("2-комнатная")
                    .setPriceRange("10000000", "20000000")
                    .setDeadline("до 2027 г.");
        });

        step("Проверка отображения значений в фильтрах", () -> {
            assertEquals("2-комнатная", filterSearchPage.getRoomsPlaceholder(), "Плейсхолдер комнат должен соответствовать установленному значению");
            assertEquals("от Цена от, ₽ 10 000 000 до Цена до, ₽ 20 000 000 ₽", filterSearchPage.getPricePlaceholder(), "Плейсхолдер цены должен соответствовать установленному значению");
            assertEquals("до 2027 г.", filterSearchPage.getDeadlinePlaceholder(), "Плейсхолдер срока сдачи должен соответствовать установленному значению");
        });
    }

    @Test
    @Tag("Smoke")
    @Owner("belikinA")
    @Story("Фильтрация недвижимости")
    @DisplayName("Проверка установки значений фильтров, отображения тегов и их удаления по одному")
    @Description("Установка значений в фильтрах, соответствие отображаемых тегов выбранным значениям и их удаление по одному")
    void FilterValuesAndTagsWithIndividualResetTest() {
        step("Открытие главной страницы", () -> {
            open(baseUrl);
            cookieManager.setCityForTest();
        });

        step("Установка значений фильтров", () -> {
            filterSearchPage.setDistrict("Адмиралтейский")
                    .setMetro("Адмиралтейская")
                    .setRooms("2-комнатная")
                    .setPriceRange("10000000", "20000000")
                    .setDeadline("до 2027 г.");
        });

        step("Проверка отображения тегов фильтров", () -> {
            var tags = filterSearchPage.getFilterTags();
            assertTrue(tags.contains("2-комнатная"), "Тег '2-комнатная' должен присутствовать");
            assertTrue(tags.contains("м. Адмиралтейская"), "Тег 'м. Адмиралтейская' должен присутствовать");
        });
        
        step("Удаление всех тегов по одному", () -> {
            filterSearchPage.removeAllTagsIndividually();
        });

        step("Проверка отсутствия тегов после удаления", () -> {
            assertEquals(0, filterSearchPage.getTagsCount(), "После удаления всех тегов по одному теги должны отсутствовать");
        });
    }
}