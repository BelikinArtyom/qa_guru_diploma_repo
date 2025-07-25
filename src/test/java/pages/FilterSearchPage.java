package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.text;

public class FilterSearchPage {

    // Поле поиска
    private final SelenideElement searchField = $("input[name='filter-search']");
    private final SelenideElement clearSearchButton = $(".field__clear button");

    // Кнопки локации
    private final SelenideElement districtButton = $(".location-controls button:nth-child(1)");

    // Селект количества комнат
    private final SelenideElement roomsSelect = $(".select.rooms .field__element");
    private final SelenideElement roomsPlaceholder = $(".select.rooms .field__element span");

    // Селект цены
    private final SelenideElement priceSelect = $(".range-select.price .field__element");
    private final SelenideElement pricePlaceholder = $(".range-select.price .field__element span");
    private final SelenideElement priceFromInput = $("input[placeholder*='от']");
    private final SelenideElement priceToInput = $("input[placeholder*='до']");

    // Селект срока сдачи
    private final SelenideElement deadlineSelect = $(".select.deadline .field__element");
    private final SelenideElement deadlinePlaceholder = $(".select.deadline .field__element span");

    // Элементы для работы с районом
    private final SelenideElement districtSearchInput = $("input[placeholder='Поиск по названию']");
    private final SelenideElement firstDropdownItem = $$(".dropdown__list").first();

    // Элементы для работы с метро
    private final SelenideElement metroButton = $$("button.btn_ghost").findBy(text("Метро"));
    private final SelenideElement metroSearchInput = $(".filter-search__field input.text-field__element[placeholder='Поиск по названию']");
    private final SelenideElement metroSuggestion = $(".filter-search-suggestion-label__name");
    private final SelenideElement modalCloseButton = $(".modal-header__close");

    // Элементы для работы с комнатами и сроками
    private final ElementsCollection dropdownItems = $$(".dropdown-item");
    private final SelenideElement bodyElement = $("body");

    // Элементы для работы с тегами
    private final ElementsCollection filterBottomTags = $$(".filter__bottom .tag");
    private final ElementsCollection filterTags = $$(".filter-tags .tag");
    private final ElementsCollection allTags = $$(".tag");
    private final ElementsCollection anyTagElements = $$("[class*='tag']");
    private final ElementsCollection filterTagsForCount = $$(".filter__tags .tag");

    // Элементы для работы с расширенными фильтрами
    private final SelenideElement allFiltersButton = $$("button.btn_white").findBy(text("Все фильтры"));
    private final ElementsCollection extendedFilterNames = $$(".filter__extend-field [class*='placeholder']");

    // Элементы для работы с кнопками сброса
    private final SelenideElement resetAllButton = $(".chips__delete-all button");
    private final ElementsCollection individualDeleteButtons = $$(".chips__delete .btn_clear, .chips__item .btn_clear");

    // Методы для работы с поиском

    public FilterSearchPage enterSearchText(String searchText) {
        searchField.setValue(searchText);
        return this;
    }

    public FilterSearchPage clearSearch() {
        clearSearchButton.click();
        return this;
    }

    public String getSearchText() {
        return searchField.getValue();
    }

    // Методы для работы с кнопками локации

    public FilterSearchPage clickDistrictButton() {
        districtButton.click();
        return this;
    }

    // Методы для работы с селектом количества комнат

    public FilterSearchPage openRoomsSelect() {
        roomsSelect.click();
        return this;
    }

    public String getRoomsPlaceholder() {
        return roomsPlaceholder.text();
    }

    // Методы для работы с селектом цены

    public FilterSearchPage openPriceSelect() {
        priceSelect.click();
        return this;
    }

    public String getPricePlaceholder() {
        return pricePlaceholder.text();
    }

    // Методы для работы с селектом срока сдачи

    public FilterSearchPage openDeadlineSelect() {
        deadlineSelect.click();
        return this;
    }

    public String getDeadlinePlaceholder() {
        return deadlinePlaceholder.text();
    }

    // Методы для установки значений фильтров

    public FilterSearchPage setDistrict(String district) {
        clickDistrictButton();
        
        districtSearchInput.setValue(district);
        
        firstDropdownItem.click();
        
        return this;
    }

    public FilterSearchPage setMetro(String metro) {
        metroButton.click();

        metroSearchInput.setValue(metro);

        metroSuggestion.click();

        modalCloseButton.click();

        return this;
    }

    public FilterSearchPage setRooms(String rooms) {
        openRoomsSelect();
        dropdownItems.findBy(text(rooms)).click();
        bodyElement.click();
        return this;
    }

    public FilterSearchPage setPriceRange(String priceFrom, String priceTo) {
        openPriceSelect();
        priceFromInput.setValue(priceFrom);
        priceToInput.setValue(priceTo);
        bodyElement.click();
        return this;
    }

    public FilterSearchPage setDeadline(String deadline) {
        openDeadlineSelect();
        dropdownItems.get(3).click();
        bodyElement.click();
        return this;
    }

    // Методы для получения данных о тегах фильтров

    public java.util.List<String> getFilterTags() {
        java.util.List<String> tags = filterBottomTags.texts();
        if (tags.isEmpty()) {
            tags = filterTags.texts();
        }
        if (tags.isEmpty()) {
            tags = allTags.texts();
        }
        if (tags.isEmpty()) {
            tags = anyTagElements.texts();
        }
        return tags;
    }

    public int getTagsCount() {
        return filterTagsForCount.size();
    }

    // Методы для работы с расширенными фильтрами

    public FilterSearchPage clickAllFiltersButton() {
        allFiltersButton.click();
        return this;
    }

    public java.util.List<String> getAllFilterNames() {
        return extendedFilterNames.texts();
    }

    // Методы для работы с кнопками сброса

    public FilterSearchPage clickResetAllButton() {
        resetAllButton.click();
        return this;
    }

    public FilterSearchPage removeAllTagsIndividually() {
        while (individualDeleteButtons.size() > 0) {
            individualDeleteButtons.first().click();
        }
        return this;
    }

    // Вспомогательные методы

    private FilterSearchPage closeDropdown() {
        modalCloseButton.click();
        return this;
    }
}
