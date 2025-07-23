package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FilterSearchPage {

    // Константы для локаторов
    private static final String SEARCH_INPUT_LOCATOR = "input[name='filter-search']";
    private static final String CLEAR_BUTTON_LOCATOR = ".field__clear button";
    private static final String DISTRICT_BUTTON_LOCATOR = ".location-controls .btn-wrapper:first-child button";
    private static final String METRO_BUTTON_LOCATOR = ".location-controls .btn-wrapper:last-child button";
    private static final String ROOMS_SELECT_LOCATOR = "div.rooms[role='select']";
    private static final String PRICE_SELECT_LOCATOR = "div.price[role='select']";
    private static final String DEADLINE_SELECT_LOCATOR = "div.deadline[role='select']";

    // Основной контейнер фильтра поиска
    private final SelenideElement filterSearchBox = $(".filter-search-box");

    // Поле поиска и связанные элементы
    private final SelenideElement searchField = $(SEARCH_INPUT_LOCATOR);
    private final SelenideElement searchIcon = $(".field__icon_before svg");
    private final SelenideElement clearSearchButton = $(CLEAR_BUTTON_LOCATOR);

    // Кнопки локации
    private final SelenideElement districtButton = $(DISTRICT_BUTTON_LOCATOR);
    private final SelenideElement metroButton = $(METRO_BUTTON_LOCATOR);

    // Селект количества комнат
    private final SelenideElement roomsSelect = $(ROOMS_SELECT_LOCATOR);
    private final SelenideElement roomsHiddenInput = $("input[name='room']");
    private final SelenideElement roomsPlaceholder = $(".rooms .select__placeholder");
    private final SelenideElement roomsDropdownIcon = $(".rooms .select__icon-down");

    // Селект цены
    private final SelenideElement priceSelect = $(PRICE_SELECT_LOCATOR);
    private final SelenideElement priceHiddenInput = $("input[name='price']");
    private final SelenideElement pricePlaceholder = $(".price .range-select__placeholder");
    private final SelenideElement priceDropdownIcon = $(".price .range-select__icon-down");

    // Селект срока сдачи
    private final SelenideElement deadlineSelect = $(DEADLINE_SELECT_LOCATOR);
    private final SelenideElement deadlineHiddenInput = $("input[name='deadline']");
    private final SelenideElement deadlinePlaceholder = $(".deadline .select__placeholder");
    private final SelenideElement deadlineDropdownIcon = $(".deadline .select__icon-down");

    // Нижняя часть фильтра (filter__bottom)
    private final SelenideElement filterBottom = $(".filter__bottom");
    private final SelenideElement filterTags = $(".filter__tags");
    private final SelenideElement filterControls = $(".filter__controls");

    // Кнопка "Все фильтры"
    private final SelenideElement allFiltersButton = $(".filter__open-extend button");
    private final SelenideElement allFiltersIcon = $(".filter__open-extend svg");
    private final SelenideElement allFiltersText = $(".filter__open-extend span");

    // Кнопка "На карте"
    private final SelenideElement onMapButton = $(".filter__on-map a");
    private final SelenideElement onMapIcon = $(".filter__on-map svg");
    private final SelenideElement onMapText = $(".filter__on-map span");

    // Кнопка "Показать квартиры"
    private final SelenideElement showApartmentsButton = $(".filter__apply a");
    private final SelenideElement showApartmentsText = $(".filter__apply span");

    // Геттеры для доступа к элементам верхней части фильтра

    public SelenideElement getFilterSearchBox() {
        return filterSearchBox;
    }

    public SelenideElement getSearchField() {
        return searchField;
    }

    public SelenideElement getSearchIcon() {
        return searchIcon;
    }

    public SelenideElement getClearSearchButton() {
        return clearSearchButton;
    }

    public SelenideElement getDistrictButton() {
        return districtButton;
    }

    public SelenideElement getMetroButton() {
        return metroButton;
    }

    public SelenideElement getRoomsSelect() {
        return roomsSelect;
    }

    public SelenideElement getRoomsHiddenInput() {
        return roomsHiddenInput;
    }

    public SelenideElement getRoomsPlaceholder() {
        return roomsPlaceholder;
    }

    public SelenideElement getRoomsDropdownIcon() {
        return roomsDropdownIcon;
    }

    public SelenideElement getPriceSelect() {
        return priceSelect;
    }

    public SelenideElement getPriceHiddenInput() {
        return priceHiddenInput;
    }

    public SelenideElement getPricePlaceholder() {
        return pricePlaceholder;
    }

    public SelenideElement getPriceDropdownIcon() {
        return priceDropdownIcon;
    }

    public SelenideElement getDeadlineSelect() {
        return deadlineSelect;
    }

    public SelenideElement getDeadlineHiddenInput() {
        return deadlineHiddenInput;
    }

    public SelenideElement getDeadlinePlaceholder() {
        return deadlinePlaceholder;
    }

    public SelenideElement getDeadlineDropdownIcon() {
        return deadlineDropdownIcon;
    }

    // Геттеры для доступа к элементам нижней части фильтра

    public SelenideElement getFilterBottom() {
        return filterBottom;
    }

    public SelenideElement getFilterTags() {
        return filterTags;
    }

    public SelenideElement getFilterControls() {
        return filterControls;
    }

    public SelenideElement getAllFiltersButton() {
        return allFiltersButton;
    }

    public SelenideElement getAllFiltersIcon() {
        return allFiltersIcon;
    }

    public SelenideElement getAllFiltersText() {
        return allFiltersText;
    }

    public SelenideElement getOnMapButton() {
        return onMapButton;
    }

    public SelenideElement getOnMapIcon() {
        return onMapIcon;
    }

    public SelenideElement getOnMapText() {
        return onMapText;
    }

    public SelenideElement getShowApartmentsButton() {
        return showApartmentsButton;
    }

    public SelenideElement getShowApartmentsText() {
        return showApartmentsText;
    }
}