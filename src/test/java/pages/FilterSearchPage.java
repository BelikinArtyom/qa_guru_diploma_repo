package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.empty;

public class FilterSearchPage {

    // Основной контейнер поиска
    private final SelenideElement filterSearchBox = $(".filter-search-box");

    // Поле поиска
    private final SelenideElement searchField = $("input[name='filter-search']");
    private final SelenideElement searchIcon = $(".field__icon_before svg");
    private final SelenideElement clearSearchButton = $(".field__clear button");

    // Кнопки локации
    private final SelenideElement districtButton = $(".location-controls button:nth-child(1)");
    private final SelenideElement metroButton = $(".location-controls button:nth-child(2)");

    // Селект количества комнат
    private final SelenideElement roomsSelect = $(".field-wrapper_press-effect-animation.select.rooms .field__element");
    private final SelenideElement roomsHiddenInput = $("input[name='room']");
    private final SelenideElement roomsPlaceholder = $(".rooms .select__placeholder");
    private final SelenideElement roomsDropdownIcon = $(".rooms .select__icon-down");

    // Селект цены
    private final SelenideElement priceSelect = $(".field-wrapper_press-effect-animation.range-select.price .field__element");
    private final SelenideElement priceHiddenInput = $("input[name='price']");
    private final SelenideElement pricePlaceholder = $(".price .range-select__placeholder");
    private final SelenideElement priceDropdownIcon = $(".price .range-select__icon-down");

    // Селект срока сдачи
    private final SelenideElement deadlineSelect = $(".field-wrapper_press-effect-animation.select.deadline .field__element");
    private final SelenideElement deadlineHiddenInput = $("input[name='deadline']");
    private final SelenideElement deadlinePlaceholder = $(".deadline .select__placeholder");
    private final SelenideElement deadlineDropdownIcon = $(".deadline .select__icon-down");

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

    public boolean isSearchFieldEmpty() {
        return searchField.getValue().isEmpty();
    }

    // Методы для работы с кнопками локации

    public FilterSearchPage clickDistrictButton() {
        districtButton.click();
        return this;
    }

    public FilterSearchPage clickMetroButton() {
        metroButton.click();
        return this;
    }

    // Методы для работы с селектом количества комнат

    public FilterSearchPage openRoomsSelect() {
        roomsSelect.click();
        return this;
    }

    public String getRoomsValue() {
        return roomsHiddenInput.getValue();
    }

    public String getRoomsPlaceholder() {
        return $(".field-wrapper.select.rooms .field__element span").text();
    }

    // Методы для работы с селектом цены

    public FilterSearchPage openPriceSelect() {
        priceSelect.click();
        return this;
    }

    public String getPriceValue() {
        return priceHiddenInput.getValue();
    }

    public String getPricePlaceholder() {
        return $(".field-wrapper.range-select.price .field__element span").text();
    }

    // Методы для работы с селектом срока сдачи

    public FilterSearchPage openDeadlineSelect() {
        deadlineSelect.click();
        return this;
    }

    public String getDeadlineValue() {
        return deadlineHiddenInput.getValue();
    }

    public String getDeadlinePlaceholder() {
        return $(".field-wrapper.select.deadline .field__element span").text();
    }

    // Методы для установки значений фильтров


    public FilterSearchPage setDistrict(String district) {
        clickDistrictButton();
        
        $("input[placeholder='Поиск по названию']").setValue(district);
        
        $$(".dropdown__list").get(0).click();
        
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
        $("body").click();
        return this;
    }

    public FilterSearchPage setPriceRange(String priceFrom, String priceTo) {
        openPriceSelect();
        $("input[placeholder*='от']").setValue(priceFrom);
        $("input[placeholder*='до']").setValue(priceTo);
     
        $("body").click();
        return this;
    }

    public FilterSearchPage setDeadline(String deadline) {
        openDeadlineSelect();
        $$(".dropdown-item").get(3).click();
        $("body").click();
        return this;
    }

    public FilterSearchPage setDeadlineForFilter(String deadline) {
        openDeadlineSelect();
        $$(".dropdown-item").get(3).click();
        return this;
    }

    // Методы для проверки тегов фильтров

    public java.util.List<String> getFilterTags() {
        java.util.List<String> tags = $$(".filter__bottom .tag").texts();
        if (tags.isEmpty()) {
            tags = $$(".filter-tags .tag").texts();
        }
        if (tags.isEmpty()) {
            tags = $$(".tag").texts();
        }
        if (tags.isEmpty()) {
            tags = $$("[class*='tag']").texts();
        }
        return tags;
    }

    public boolean hasFilterTag(String expectedTag) {
        return getFilterTags().contains(expectedTag);
    }

    public boolean hasAllExpectedTags(java.util.List<String> expectedTags) {
        java.util.List<String> actualTags = getFilterTags();
        return actualTags.containsAll(expectedTags);
    }

    public int getFilterTagsCount() {
        return getFilterTags().size();
    }

    public void printAllTags() {
        java.util.List<String> tags = getFilterTags();
        System.out.println("Найденные теги:");
        for (String tag : tags) {
            System.out.println("  - " + tag);
        }
    }

    // Методы проверки видимости элементов

    public boolean isSearchFieldDisplayed() {
        return searchField.isDisplayed();
    }

    public boolean isClearButtonDisplayed() {
        return clearSearchButton.isDisplayed();
    }

    public boolean isDistrictButtonDisplayed() {
        return districtButton.isDisplayed();
    }

    public boolean isMetroButtonDisplayed() {
        return metroButton.isDisplayed();
    }

    public boolean isRoomsSelectDisplayed() {
        return roomsSelect.isDisplayed();
    }

    public boolean isPriceSelectDisplayed() {
        return priceSelect.isDisplayed();
    }

    public boolean isDeadlineSelectDisplayed() {
        return deadlineSelect.isDisplayed();
    }

    // Дополнительные методы для взаимодействия

    public boolean areAllFilterElementsDisplayed() {
        return filterSearchBox.isDisplayed() &&
                isSearchFieldDisplayed() &&
                isDistrictButtonDisplayed() &&
                isMetroButtonDisplayed() &&
                isRoomsSelectDisplayed() &&
                isPriceSelectDisplayed() &&
                isDeadlineSelectDisplayed();
    }

    public SelenideElement getFilterSearchBox() {
        return filterSearchBox;
    }

    // Методы для работы с расширенными фильтрами

    public FilterSearchPage clickAllFiltersButton() {
        $$("button.btn_white").findBy(text("Все фильтры")).click();
        return this;
    }

    public java.util.List<String> getAllFilterNames() {
        return $$(".filter__extend-field .select__placeholder, .filter__extend-field .range-select__placeholder").texts();
    }

    public boolean hasAllExpectedFilters(java.util.List<String> expectedFilters) {
        java.util.List<String> actualFilters = getAllFilterNames();
        return actualFilters.containsAll(expectedFilters);
    }

    public boolean checkExtendedFilters() {
        java.util.List<String> expectedFilters = getExpectedExtendedFilters();
        return hasAllExpectedFilters(expectedFilters);
    }

    public boolean checkFilterTags() {
        java.util.List<String> expectedTags = getExpectedAdmiraltyTags();
        return hasAllExpectedTags(expectedTags);
    }

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

    public boolean areTagsEmpty() {
        return $$(".filter__tags .tag").size() == 0;
    }

    public FilterSearchPage shouldHaveNoTags() {
        $(".filter__tags").shouldHave(empty);
        return this;
    }

    public FilterSearchPage shouldNotHaveAnyTags() {
        $(".filter__tags").shouldNotHave(text("2-комнатная"));
        $(".filter__tags").shouldNotHave(text("м. Адмиралтейская"));
        return this;
    }


    // Константы для тестовых данных
    public static final String DISTRICT_ADMIRALTY = "Адмиралтейский";
    public static final String METRO_ADMIRALTY = "Адмиралтейская";
    public static final String ROOMS_2 = "2-комнатная";
    public static final String PRICE_FROM = "10000000";
    public static final String PRICE_TO = "20000000";
    public static final String DEADLINE = "до 2027 г.";
    public static final String TEST_SEARCH_TEXT = "Тестовый ЖК";

    public java.util.List<String> getExpectedAdmiraltyTags() {
        return java.util.Arrays.asList(
                "2-комнатная",
                "м. Адмиралтейская"
        );
    }

    public java.util.List<String> getExpectedExtendedFilters() {
        return java.util.Arrays.asList(
                "Расстояние до метро",
                "Прописка",
                "Площадь от-до, м²",
                "Площадь кухни от-до, м²",
                "Площадь балкона от-до, м²",
                "Отделка",
                "Этаж",
                "Санузел",
                "Способ оплаты",
                "Апартаменты",
                "Год постройки"
        );
    }
}
