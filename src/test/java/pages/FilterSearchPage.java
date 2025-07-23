package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Page Object для компонента поиска и фильтрации на странице TrendRealty
 * Содержит все интерактивные элементы из блока filter-search-box
 */
public class FilterSearchPage {

    // Основной контейнер поиска
    private final SelenideElement filterSearchBox = $(".filter-search-box");

    // Поле поиска
    private final SelenideElement searchField = $("input[name='filter-search']");
    private final SelenideElement searchIcon = $(".field__icon_before svg");
    private final SelenideElement clearSearchButton = $(".field__clear button");

    // Кнопки локации
    private final SelenideElement districtButton = $(".location-controls button:has(span:contains('Район'))");
    private final SelenideElement metroButton = $(".location-controls button:has(span:contains('Метро'))");

    // Селект количества комнат
    private final SelenideElement roomsSelect = $("div.rooms[role='select']");
    private final SelenideElement roomsHiddenInput = $("input[name='room']");
    private final SelenideElement roomsPlaceholder = $(".rooms .select__placeholder");
    private final SelenideElement roomsDropdownIcon = $(".rooms .select__icon-down");

    // Селект цены
    private final SelenideElement priceSelect = $("div.price[role='select']");
    private final SelenideElement priceHiddenInput = $("input[name='price']");
    private final SelenideElement pricePlaceholder = $(".price .range-select__placeholder");
    private final SelenideElement priceDropdownIcon = $(".price .range-select__icon-down");

    // Селект срока сдачи
    private final SelenideElement deadlineSelect = $("div.deadline[role='select']");
    private final SelenideElement deadlineHiddenInput = $("input[name='deadline']");
    private final SelenideElement deadlinePlaceholder = $(".deadline .select__placeholder");
    private final SelenideElement deadlineDropdownIcon = $(".deadline .select__icon-down");

    // Методы для работы с поиском

    @Step("Ввести текст в поле поиска: {searchText}")
    public FilterSearchPage enterSearchText(String searchText) {
        searchField.setValue(searchText);
        return this;
    }

    @Step("Очистить поле поиска")
    public FilterSearchPage clearSearch() {
        clearSearchButton.click();
        return this;
    }

    @Step("Получить текст из поля поиска")
    public String getSearchText() {
        return searchField.getValue();
    }

    @Step("Проверить, что поле поиска пустое")
    public boolean isSearchFieldEmpty() {
        return searchField.getValue().isEmpty();
    }

    // Методы для работы с кнопками локации

    @Step("Нажать на кнопку 'Район'")
    public FilterSearchPage clickDistrictButton() {
        districtButton.click();
        return this;
    }

    @Step("Нажать на кнопку 'Метро'")
    public FilterSearchPage clickMetroButton() {
        metroButton.click();
        return this;
    }

    // Методы для работы с селектом количества комнат

    @Step("Открыть селект количества комнат")
    public FilterSearchPage openRoomsSelect() {
        roomsSelect.click();
        return this;
    }

    @Step("Получить значение селекта количества комнат")
    public String getRoomsValue() {
        return roomsHiddenInput.getValue();
    }

    @Step("Проверить текст плейсхолдера количества комнат")
    public String getRoomsPlaceholder() {
        return roomsPlaceholder.text();
    }

    // Методы для работы с селектом цены

    @Step("Открыть селект цены")
    public FilterSearchPage openPriceSelect() {
        priceSelect.click();
        return this;
    }

    @Step("Получить значение селекта цены")
    public String getPriceValue() {
        return priceHiddenInput.getValue();
    }

    @Step("Проверить текст плейсхолдера цены")
    public String getPricePlaceholder() {
        return pricePlaceholder.text();
    }

    // Методы для работы с селектом срока сдачи

    @Step("Открыть селект срока сдачи")
    public FilterSearchPage openDeadlineSelect() {
        deadlineSelect.click();
        return this;
    }

    @Step("Получить значение селекта срока сдачи")
    public String getDeadlineValue() {
        return deadlineHiddenInput.getValue();
    }

    @Step("Проверить текст плейсхолдера срока сдачи")
    public String getDeadlinePlaceholder() {
        return deadlinePlaceholder.text();
    }

    // Методы проверки видимости элементов

    @Step("Проверить, что поле поиска отображается")
    public boolean isSearchFieldDisplayed() {
        return searchField.isDisplayed();
    }

    @Step("Проверить, что кнопка очистки поиска отображается")
    public boolean isClearButtonDisplayed() {
        return clearSearchButton.isDisplayed();
    }

    @Step("Проверить, что кнопка 'Район' отображается")
    public boolean isDistrictButtonDisplayed() {
        return districtButton.isDisplayed();
    }

    @Step("Проверить, что кнопка 'Метро' отображается")
    public boolean isMetroButtonDisplayed() {
        return metroButton.isDisplayed();
    }

    @Step("Проверить, что селект количества комнат отображается")
    public boolean isRoomsSelectDisplayed() {
        return roomsSelect.isDisplayed();
    }

    @Step("Проверить, что селект цены отображается")
    public boolean isPriceSelectDisplayed() {
        return priceSelect.isDisplayed();
    }

    @Step("Проверить, что селект срока сдачи отображается")
    public boolean isDeadlineSelectDisplayed() {
        return deadlineSelect.isDisplayed();
    }

    // Дополнительные методы для взаимодействия

    @Step("Проверить, что все основные элементы фильтра отображаются")
    public boolean areAllFilterElementsDisplayed() {
        return filterSearchBox.isDisplayed() &&
                isSearchFieldDisplayed() &&
                isDistrictButtonDisplayed() &&
                isMetroButtonDisplayed() &&
                isRoomsSelectDisplayed() &&
                isPriceSelectDisplayed() &&
                isDeadlineSelectDisplayed();
    }

    @Step("Получить контейнер фильтра поиска")
    public SelenideElement getFilterSearchBox() {
        return filterSearchBox;
    }
}
