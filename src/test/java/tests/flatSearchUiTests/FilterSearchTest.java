package tests.flatSearchUiTests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.FilterSearchPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

@Feature("Фильтр поиска недвижимости")
@DisplayName("Тесты компонента поиска и фильтрации на TrendRealty")
public class FilterSearchTest {

    private FilterSearchPage filterSearchPage = new FilterSearchPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
    }

    @Test
    @Story("Поиск недвижимости")
    @DisplayName("Проверка ввода текста в поле поиска")
    @Description("Тест проверяет возможность ввода текста в поле поиска и его получения")
    void testSearchFieldInput() {
        open("https://trendrealty.ru/");

        String searchText = "ЖК Московский";

        filterSearchPage.enterSearchText(searchText);

        assertEquals(searchText, filterSearchPage.getSearchText(),
                "Введенный текст должен совпадать с полученным из поля поиска");
    }

    @Test
    @Story("Поиск недвижимости")
    @DisplayName("Проверка очистки поля поиска")
    @Description("Тест проверяет функциональность кнопки очистки поля поиска")
    void testClearSearchField() {
        open("https://trendrealty.ru/");

        String searchText = "Тестовый текст";

        filterSearchPage.enterSearchText(searchText)
                .clearSearch();

        assertTrue(filterSearchPage.isSearchFieldEmpty(),
                "Поле поиска должно быть пустым после очистки");
    }

    @Test
    @Story("Фильтрация по локации")
    @DisplayName("Проверка кнопок локации")
    @Description("Тест проверяет отображение и кликабельность кнопок 'Район' и 'Метро'")
    void testLocationButtons() {
        open("https://trendrealty.ru/");

        assertTrue(filterSearchPage.isDistrictButtonDisplayed(),
                "Кнопка 'Район' должна отображаться");
        assertTrue(filterSearchPage.isMetroButtonDisplayed(),
                "Кнопка 'Метро' должна отображаться");

        // Проверяем кликабельность
        assertDoesNotThrow(() -> filterSearchPage.clickDistrictButton(),
                "Кнопка 'Район' должна быть кликабельной");
        assertDoesNotThrow(() -> filterSearchPage.clickMetroButton(),
                "Кнопка 'Метро' должна быть кликабельной");
    }

    @Test
    @Story("Фильтрация по параметрам")
    @DisplayName("Проверка селектов фильтров")
    @Description("Тест проверяет отображение и функциональность селектов количества комнат, цены и срока сдачи")
    void testFilterSelects() {
        open("https://trendrealty.ru/");

        // Проверяем отображение всех селектов
        assertTrue(filterSearchPage.isRoomsSelectDisplayed(),
                "Селект количества комнат должен отображаться");
        assertTrue(filterSearchPage.isPriceSelectDisplayed(),
                "Селект цены должен отображаться");
        assertTrue(filterSearchPage.isDeadlineSelectDisplayed(),
                "Селект срока сдачи должен отображаться");

        // Проверяем плейсхолдеры
        assertEquals("Кол-во комнат", filterSearchPage.getRoomsPlaceholder(),
                "Плейсхолдер селекта комнат должен быть корректным");
        assertEquals("Цена от-до, ₽", filterSearchPage.getPricePlaceholder(),
                "Плейсхолдер селекта цены должен быть корректным");
        assertEquals("Срок сдачи", filterSearchPage.getDeadlinePlaceholder(),
                "Плейсхолдер селекта срока сдачи должен быть корректным");

        // Проверяем кликабельность селектов
        assertDoesNotThrow(() -> filterSearchPage.openRoomsSelect(),
                "Селект количества комнат должен быть кликабельным");
        assertDoesNotThrow(() -> filterSearchPage.openPriceSelect(),
                "Селект цены должен быть кликабельным");
        assertDoesNotThrow(() -> filterSearchPage.openDeadlineSelect(),
                "Селект срока сдачи должен быть кликабельным");
    }

    @Test
    @Story("Общая функциональность")
    @DisplayName("Проверка отображения всех элементов фильтра")
    @Description("Тест проверяет, что все основные элементы компонента фильтра отображаются корректно")
    void testAllFilterElementsDisplayed() {
        open("https://trendrealty.ru/");

        assertTrue(filterSearchPage.areAllFilterElementsDisplayed(),
                "Все основные элементы фильтра должны отображаться");
    }

    @Test
    @Story("Поиск недвижимости")
    @DisplayName("Проверка поиска с различными типами данных")
    @Description("Тест проверяет ввод различных типов поисковых запросов")
    void testSearchWithDifferentInputTypes() {
        open("https://trendrealty.ru/");

        // Тест поиска по названию ЖК
        String complexName = "ЖК Солнечный";
        filterSearchPage.enterSearchText(complexName);
        assertEquals(complexName, filterSearchPage.getSearchText());

        filterSearchPage.clearSearch();

        // Тест поиска по улице
        String streetName = "улица Ленина";
        filterSearchPage.enterSearchText(streetName);
        assertEquals(streetName, filterSearchPage.getSearchText());

        filterSearchPage.clearSearch();

        // Тест поиска по застройщику
        String developerName = "ПИК";
        filterSearchPage.enterSearchText(developerName);
        assertEquals(developerName, filterSearchPage.getSearchText());
    }
}