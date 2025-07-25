package pages;

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
    private final SelenideElement roomsSelect = $(".field-wrapper_press-effect-animation.select.rooms .field__element");

    // Селект цены
    private final SelenideElement priceSelect = $(".field-wrapper_press-effect-animation.range-select.price .field__element");

    // Селект срока сдачи
    private final SelenideElement deadlineSelect = $(".field-wrapper_press-effect-animation.select.deadline .field__element");

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
        return $(".field-wrapper.select.rooms .field__element span").text();
    }

    // Методы для работы с селектом цены

    public FilterSearchPage openPriceSelect() {
        priceSelect.click();
        return this;
    }

    public String getPricePlaceholder() {
        return $(".field-wrapper.range-select.price .field__element span").text();
    }

    // Методы для работы с селектом срока сдачи

    public FilterSearchPage openDeadlineSelect() {
        deadlineSelect.click();
        return this;
    }

    public String getDeadlinePlaceholder() {
        return $(".field-wrapper.select.deadline .field__element span").text();
    }

    // Методы для установки значений фильтров

    public FilterSearchPage setDistrict(String district) {
        clickDistrictButton();
        
        $("input[placeholder='Поиск по названию']").setValue(district);
        
        $$(".dropdown__list").first().click();
        
        return this;
    }

    public FilterSearchPage setMetro(String metro) {
        $$("button.btn_ghost").findBy(text("Метро")).click();

        $(".filter-search__field input.text-field__element[placeholder='Поиск по названию']").setValue(metro);

        $(".filter-search-suggestion-label__name").click();

        $(".modal-header__close").click();

        return this;
    }

    public FilterSearchPage setRooms(String rooms) {
        openRoomsSelect();
        $$(".dropdown-item").findBy(text(rooms)).click();
        closeDropdown();
        return this;
    }

    public FilterSearchPage setPriceRange(String priceFrom, String priceTo) {
        openPriceSelect();
        $("input[placeholder*='от']").setValue(priceFrom);
        $("input[placeholder*='до']").setValue(priceTo);
     
        closeDropdown();
        return this;
    }

    public FilterSearchPage setDeadline(String deadline) {
        openDeadlineSelect();
        $$(".dropdown-item").get(3).click();
        closeDropdown();
        return this;
    }

    // Методы для получения данных о тегах фильтров

    public java.util.List<String> getFilterTags() {
        return $$(".filter__tags .tag").texts();
    }

    public int getTagsCount() {
        return $$(".filter__tags .tag").size();
    }

    // Методы для работы с расширенными фильтрами

    public FilterSearchPage clickAllFiltersButton() {
        $$("button.btn_white").findBy(text("Все фильтры")).click();
        return this;
    }

    public java.util.List<String> getAllFilterNames() {
        return $$(".filter__extend-field .select__placeholder, .filter__extend-field .range-select__placeholder").texts();
    }

    // Методы для работы с кнопками сброса

    public FilterSearchPage clickResetAllButton() {
        $(".chips__delete-all button").click();
        return this;
    }

    public FilterSearchPage removeAllTagsIndividually() {
        while ($$(".chips__item .chips__delete .btn_clear").size() > 0) {
            $(".chips__item .chips__delete .btn_clear").click();
        }
        return this;
    }

    // Вспомогательные методы

    private FilterSearchPage closeDropdown() {
        $(".modal-header__close").click();
        return this;
    }
}
