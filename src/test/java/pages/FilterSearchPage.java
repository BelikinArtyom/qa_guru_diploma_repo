package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

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

    // Методы для установки значений фильтров


    @Step("Установить район: {district}")
    public FilterSearchPage setDistrict(String district) {
        clickDistrictButton();
        
        $("input[placeholder='Поиск по названию']").setValue(district);
        
        $$(".dropdown__list").get(0).click();
        
        return this;
    }


    @Step("Установить метро: {metro}")
    public FilterSearchPage setMetro(String metro) {
        $$("button.btn_ghost").findBy(text("Метро")).click();

        $(".filter-search__field input.text-field__element[placeholder='Поиск по названию']").setValue(metro);

        $(".filter-search-suggestion-label__name").click();

        $(".modal-header__close").click();

        return this;
    }


    @Step("Установить количество комнат: {rooms}")
    public FilterSearchPage setRooms(String rooms) {
        openRoomsSelect();
        $$(".dropdown__list .dropdown-item").get(4).click();
        $("body").click();
        return this;
    }

    @Step("Установить цену от {priceFrom} до {priceTo}")
    public FilterSearchPage setPriceRange(String priceFrom, String priceTo) {
        openPriceSelect();
        $("input[placeholder*='от']").setValue(priceFrom);
        $("input[placeholder*='до']").setValue(priceTo);
     
        $("body").click();
        return this;
    }

    @Step("Установить срок сдачи: {deadline}")
    public FilterSearchPage setDeadline(String deadline) {
        openDeadlineSelect();
        $$(".dropdown__list .dropdown-item").get(0).click();
        $("body").click();
        return this;
    }

    // Методы для проверки тегов фильтров

    @Step("Получить все теги фильтров")
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

    @Step("Проверить наличие тега: {expectedTag}")
    public boolean hasFilterTag(String expectedTag) {
        return getFilterTags().contains(expectedTag);
    }

    @Step("Проверить наличие всех ожидаемых тегов")
    public boolean hasAllExpectedTags(java.util.List<String> expectedTags) {
        java.util.List<String> actualTags = getFilterTags();
        return actualTags.containsAll(expectedTags);
    }

    @Step("Получить количество тегов")
    public int getFilterTagsCount() {
        return getFilterTags().size();
    }

    @Step("Вывести все теги для отладки")
    public void printAllTags() {
        java.util.List<String> tags = getFilterTags();
        System.out.println("Найденные теги:");
        for (String tag : tags) {
            System.out.println("  - " + tag);
        }
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

    // Методы для работы с расширенными фильтрами

    @Step("Нажать на кнопку 'Все фильтры'")
    public FilterSearchPage clickAllFiltersButton() {
        $$("button.btn_white").findBy(text("Все фильтры")).click();
        return this;
    }

    @Step("Получить все названия фильтров")
    public java.util.List<String> getAllFilterNames() {
        return $$(".filter__extend-field .select__placeholder, .filter__extend-field .range-select__placeholder").texts();
    }

    @Step("Проверить наличие всех ожидаемых фильтров")
    public boolean hasAllExpectedFilters(java.util.List<String> expectedFilters) {
        java.util.List<String> actualFilters = getAllFilterNames();
        return actualFilters.containsAll(expectedFilters);
    }

    @Step("Проверить расширенные фильтры")
    public boolean checkExtendedFilters() {
        java.util.List<String> expectedFilters = getExpectedExtendedFilters();
        return hasAllExpectedFilters(expectedFilters);
    }

    @Step("Проверить теги фильтров")
    public boolean checkFilterTags() {
        java.util.List<String> expectedTags = getExpectedAdmiraltyTags();
        return hasAllExpectedTags(expectedTags);
    }

    @Step("Нажать кнопку 'Сбросить все'")
    public FilterSearchPage clickResetAllButton() {
        $(".chips__delete-all button").click();
        return this;
    }

    @Step("Проверить, что теги отсутствуют")
    public boolean areTagsEmpty() {
        return $$(".filter__tags .tag").size() == 0;
    }

    @Step("Проверить отсутствие тегов через Selenide shouldHave")
    public FilterSearchPage shouldHaveNoTags() {
        $(".filter__tags").shouldHave(empty);
        return this;
    }

    @Step("Проверить отсутствие тегов через Selenide shouldNotHave")
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
    public static final String DEADLINE_2027 = "до 2027г.";
    public static final String TEST_SEARCH_TEXT = "Тестовый ЖК";

    @Step("Получить ожидаемые теги для фильтров Адмиралтейского района")
    public java.util.List<String> getExpectedAdmiraltyTags() {
        return java.util.Arrays.asList(
                "2-комнатная",
                "м. Адмиралтейская"
        );
    }

    @Step("Получить ожидаемые названия расширенных фильтров")
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