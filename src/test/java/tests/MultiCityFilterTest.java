package tests;

import helpers.CookieManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.FilterSearchPage;

import static org.junit.jupiter.api.Assertions.*;

@Feature("Фильтр поиска недвижимости в разных городах")
@DisplayName("Тесты компонента поиска для различных городов")
public class MultiCityFilterTest extends BaseTest {

    private final FilterSearchPage filterSearchPage = new FilterSearchPage();

    @Test
    @Story("Тестирование в Москве")
    @DisplayName("Проверка фильтров в Москве")
    @Description("Тест проверяет работу фильтров при установленной cookie Москвы")
    void testFiltersInMoscow() {
        // Cookie Москвы уже установлена в BaseTest по умолчанию
        
        // Проверяем, что элементы фильтра отображаются
        assertTrue(filterSearchPage.areAllFilterElementsDisplayed(), 
                "Все элементы фильтра должны отображаться в Москве");
        
        // Тестируем поиск
        String searchText = "ЖК Москва-Сити";
        filterSearchPage.enterSearchText(searchText);
        assertEquals(searchText, filterSearchPage.getSearchText(), 
                "Поиск должен работать в Москве");
        
        // Проверяем, что cookie города установлена правильно
        assertEquals(CookieManager.CityIds.MOSCOW, CookieManager.getSelectedCityCookieValue(), 
                "Cookie должна содержать ID Москвы");
    }

    @Test
    @Story("Тестирование в Санкт-Петербурге")
    @DisplayName("Проверка фильтров в Санкт-Петербурге")
    @Description("Тест проверяет работу фильтров при установленной cookie Санкт-Петербурга")
    void testFiltersInSPb() {
        // Устанавливаем cookie для СПб
        setCityForTest(CookieManager.CityIds.SPB);
        
        // Проверяем, что элементы фильтра отображаются
        assertTrue(filterSearchPage.areAllFilterElementsDisplayed(), 
                "Все элементы фильтра должны отображаться в Санкт-Петербурге");
        
        // Тестируем поиск
        String searchText = "ЖК Петербург";
        filterSearchPage.enterSearchText(searchText);
        assertEquals(searchText, filterSearchPage.getSearchText(), 
                "Поиск должен работать в Санкт-Петербурге");
        
        // Проверяем, что cookie города изменилась
        assertEquals(CookieManager.CityIds.SPB, CookieManager.getSelectedCityCookieValue(), 
                "Cookie должна содержать ID Санкт-Петербурга");
    }

    @Test
    @Story("Тестирование в Казани")
    @DisplayName("Проверка фильтров в Казани")
    @Description("Тест проверяет работу фильтров при установленной cookie Казани")
    void testFiltersInKazan() {
        // Устанавливаем cookie для Казани
        setCityForTest(CookieManager.CityIds.KAZAN);
        
        // Проверяем, что элементы фильтра отображаются
        assertTrue(filterSearchPage.areAllFilterElementsDisplayed(), 
                "Все элементы фильтра должны отображаться в Казани");
        
        // Тестируем поиск
        String searchText = "ЖК Казань";
        filterSearchPage.enterSearchText(searchText);
        assertEquals(searchText, filterSearchPage.getSearchText(), 
                "Поиск должен работать в Казани");
        
        // Проверяем, что cookie города изменилась
        assertEquals(CookieManager.CityIds.KAZAN, CookieManager.getSelectedCityCookieValue(), 
                "Cookie должна содержать ID Казани");
    }

    @Test
    @Story("Переключение между городами")
    @DisplayName("Проверка переключения между городами в рамках одного теста")
    @Description("Тест проверяет корректность переключения cookies и функциональности при смене города")
    void testCitySwitching() {
        // Начинаем с Москвы (по умолчанию)
        assertEquals(CookieManager.CityIds.MOSCOW, CookieManager.getSelectedCityCookieValue(), 
                "Изначально должна быть установлена Москва");
        
        // Тестируем функциональность в Москве
        filterSearchPage.enterSearchText("Москва");
        assertTrue(filterSearchPage.isSearchFieldDisplayed(), 
                "Поле поиска должно работать в Москве");
        
        // Переключаемся на СПб
        setCityForTest(CookieManager.CityIds.SPB);
        assertEquals(CookieManager.CityIds.SPB, CookieManager.getSelectedCityCookieValue(), 
                "Cookie должна измениться на СПб");
        
        // Тестируем функциональность в СПб
        filterSearchPage.clearSearch();
        filterSearchPage.enterSearchText("СПб");
        assertEquals("СПб", filterSearchPage.getSearchText(), 
                "Поиск должен работать после смены города");
        
        // Переключаемся обратно на Москву
        setCityForTest(CookieManager.CityIds.MOSCOW);
        assertEquals(CookieManager.CityIds.MOSCOW, CookieManager.getSelectedCityCookieValue(), 
                "Cookie должна вернуться на Москву");
        
        // Проверяем, что функциональность по-прежнему работает
        assertTrue(filterSearchPage.areAllFilterElementsDisplayed(), 
                "Все элементы должны отображаться после возврата к Москве");
    }

    @Test
    @Story("Управление cookies")
    @DisplayName("Проверка методов CookieManager для разных городов")
    @Description("Тест проверяет различные методы управления cookies городов")
    void testCookieManagementMethods() {
        // Тестируем методы установки конкретных городов
        CookieManager.CityIds.setMoscowCookie();
        assertEquals(CookieManager.CityIds.MOSCOW, CookieManager.getSelectedCityCookieValue(), 
                "Метод setMoscowCookie должен установить ID Москвы");
        
        CookieManager.CityIds.setSPbCookie();
        assertEquals(CookieManager.CityIds.SPB, CookieManager.getSelectedCityCookieValue(), 
                "Метод setSPbCookie должен установить ID СПб");
        
        CookieManager.CityIds.setKazanCookie();
        assertEquals(CookieManager.CityIds.KAZAN, CookieManager.getSelectedCityCookieValue(), 
                "Метод setKazanCookie должен установить ID Казани");
        
        // Тестируем удаление cookie
        CookieManager.removeSelectedCityCookie();
        assertFalse(CookieManager.isSelectedCityCookieSet(), 
                "Cookie должна быть удалена");
        
        // Восстанавливаем cookie по умолчанию
        CookieManager.setDefaultCityCookie();
        assertTrue(CookieManager.isSelectedCityCookieSet(), 
                "Cookie должна быть восстановлена");
        assertEquals(CookieManager.CityIds.MOSCOW, CookieManager.getSelectedCityCookieValue(), 
                "Должна быть установлена cookie по умолчанию (Москва)");
    }
}