package tests;

import helpers.CookieManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.FilterSearchPage;

import static org.junit.jupiter.api.Assertions.*;

@Feature("Фильтр поиска недвижимости")
@DisplayName("Тесты компонента поиска и фильтрации на TrendRealty")
public class FilterSearchTest extends BaseTest {

    private final FilterSearchPage filterSearchPage = new FilterSearchPage();

    @Test
    @Story("Поиск недвижимости")
    @DisplayName("Проверка ввода текста в поле поиска")
    @Description("Тест проверяет возможность ввода текста в поле поиска и его получения")
    void testSearchFieldInput() {
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
        assertTrue(filterSearchPage.areAllFilterElementsDisplayed(), 
                "Все основные элементы фильтра должны отображаться");
    }

    @Test
    @Story("Поиск недвижимости")
    @DisplayName("Проверка поиска с различными типами данных")
    @Description("Тест проверяет ввод различных типов поисковых запросов")
    void testSearchWithDifferentInputTypes() {
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

    @Test
    @Story("Управление cookies")
    @DisplayName("Проверка установки cookie города")
    @Description("Тест проверяет, что cookie города устанавливается корректно")
    void testCityCookieManagement() {
        // Проверяем, что cookie установлена
        assertTrue(CookieManager.isSelectedCityCookieSet(), 
                "Cookie выбранного города должна быть установлена");
        
        // Проверяем значение cookie по умолчанию
        assertEquals(CookieManager.CityIds.MOSCOW, CookieManager.getSelectedCityCookieValue(), 
                "Cookie должна содержать ID Москвы по умолчанию");
        
        // Тестируем установку другого города
        CookieManager.CityIds.setSPbCookie();
        assertEquals(CookieManager.CityIds.SPB, CookieManager.getSelectedCityCookieValue(), 
                "Cookie должна содержать ID Санкт-Петербурга");
        
        // Восстанавливаем cookie по умолчанию
        CookieManager.setDefaultCityCookie();
        assertEquals(CookieManager.CityIds.MOSCOW, CookieManager.getSelectedCityCookieValue(), 
                "Cookie должна быть восстановлена на Москву");
    }
}