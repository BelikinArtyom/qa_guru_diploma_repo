package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.*;

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
    private final ElementsCollection filterBottomTags = $$(".chips__item.chips__item_black");
    private final ElementsCollection filterTags = $$(".filter-tags .tag");
    private final ElementsCollection allTags = $$(".tag");
    private final ElementsCollection anyTagElements = $$("[class*='tag']");
    private final ElementsCollection filterTagsForCount = $$(".filter__tags .tag");

    // Элементы для работы с расширенными фильтрами
    private final SelenideElement allFiltersButton = $$("button.btn_white").findBy(text("Все фильтры"));
    private final ElementsCollection extendedFilterNames = $$(".filter__extend-field [class*='placeholder']");

    // Элементы для работы с кнопками сброса
    private final SelenideElement resetAllButton = $(".chips__delete-all button");
    private final SelenideElement resetAllChip = $(".chips__delete-all");
    private final ElementsCollection individualDeleteButtons = $$(".chips__delete .btn_clear, .chips__item .btn_clear");

    // Методы для работы с поиском

    @Step("Ввод текста в поле поиска: {searchText}")
    public FilterSearchPage enterSearchText(String searchText) {
        searchField.shouldBe(visible, enabled).setValue(searchText);
        return this;
    }

    @Step("Очистка поля поиска")
    public FilterSearchPage clearSearch() {
        clearSearchButton.shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Получение текста из поля поиска")
    public String getSearchText() {
        return searchField.shouldBe(visible).getValue();
    }

    // Методы для работы с кнопками локации

    @Step("Клик по кнопке выбора района")
    public FilterSearchPage clickDistrictButton() {
        districtButton.shouldBe(visible, enabled).click();
        return this;
    }

    // Методы для работы с селектом количества комнат

    @Step("Открытие селекта количества комнат")
    public FilterSearchPage openRoomsSelect() {
        roomsSelect.shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Получение плейсхолдера количества комнат")
    public String getRoomsPlaceholder() {
        return roomsPlaceholder.shouldBe(visible).text();
    }

    // Методы для работы с селектом цены

    @Step("Открытие селекта цены")
    public FilterSearchPage openPriceSelect() {
        priceSelect.shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Получение плейсхолдера цены")
    public String getPricePlaceholder() {
        return pricePlaceholder.shouldBe(visible).text();
    }

    // Методы для работы с селектом срока сдачи

    @Step("Открытие селекта срока сдачи")
    public FilterSearchPage openDeadlineSelect() {
        deadlineSelect.shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Получение плейсхолдера срока сдачи")
    public String getDeadlinePlaceholder() {
        return deadlinePlaceholder.shouldBe(visible).text();
    }

    // Методы для установки значений фильтров

    @Step("Установка района: {district}")
    public FilterSearchPage setDistrict(String district) {
        clickDistrictButton();
        firstDropdownItem.shouldBe(visible, enabled).click();
        districtSearchInput.shouldBe(visible, enabled).setValue(district);
        return this;
    }

    @Step("Установка метро: {metro}")
    public FilterSearchPage setMetro(String metro) {
        metroButton.shouldBe(visible, enabled).click();

        metroSearchInput.shouldBe(visible, enabled).setValue(metro);

        metroSuggestion.shouldBe(visible, enabled).click();

        modalCloseButton.shouldBe(visible, enabled).click();

        return this;
    }

    @Step("Установка количества комнат: {rooms}")
    public FilterSearchPage setRooms(String rooms) {
        openRoomsSelect();
        dropdownItems.findBy(text(rooms)).shouldBe(visible, enabled).click();
        bodyElement.click();
        return this;
    }

    @Step("Установка диапазона цен: от {priceFrom} до {priceTo}")
    public FilterSearchPage setPriceRange(String priceFrom, String priceTo) {
        openPriceSelect();
        priceFromInput.shouldBe(visible, enabled).setValue(priceFrom);
        priceToInput.shouldBe(visible, enabled).setValue(priceTo);
        bodyElement.click();
        return this;
    }

    @Step("Установка срока сдачи: {deadline}")
    public FilterSearchPage setDeadline(String deadline) {
        openDeadlineSelect();
        dropdownItems.get(3).shouldBe(visible, enabled).click();
        bodyElement.click();
        return this;
    }

    // Методы для получения данных о тегах фильтров

    @Step("Получение списка тегов фильтров")
    public java.util.List<String> getFilterTags() {
        // Получаем все теги, исключая кнопку "Сбросить все"
        java.util.List<String> tags = new java.util.ArrayList<>();
        
        // Пробуем разные селекторы по приоритету
        if (!filterBottomTags.isEmpty()) {
            tags = filterBottomTags.texts();
        } else if (!filterTags.isEmpty()) {
            tags = filterTags.texts();
        } else if (!allTags.isEmpty()) {
            tags = allTags.texts();
        } else if (!anyTagElements.isEmpty()) {
            tags = anyTagElements.texts();
        }
        
        // Фильтруем теги, исключая служебные элементы
        java.util.List<String> filteredTags = new java.util.ArrayList<>();
        for (String tag : tags) {
            // Исключаем кнопку "Сбросить все" и пустые строки
            if (!tag.equals("Сбросить все") && !tag.trim().isEmpty()) {
                filteredTags.add(tag);
            }
        }
        
        return filteredTags;
    }

    @Step("Получение списка тегов фильтров (исключая кнопку 'Сбросить все')")
    public java.util.List<String> getFilterTagsOnly() {
        // Получаем только теги фильтров, исключая кнопку "Сбросить все"
        java.util.List<String> allTags = getFilterTags();
        allTags.removeIf(tag -> tag.equals("Сбросить все"));
        return allTags;
    }

    @Step("Получение списка чистых тегов фильтров")
    public java.util.List<String> getPureFilterTags() {
        // Получаем только теги фильтров, исключая все служебные элементы
        java.util.List<String> tags = getFilterTags();
        
        // Исключаем служебные элементы
        java.util.List<String> pureTags = new java.util.ArrayList<>();
        for (String tag : tags) {
            // Исключаем кнопки и служебные элементы
            if (!tag.equals("Сбросить все") && 
                !tag.contains("Сбросить") && 
                !tag.trim().isEmpty() &&
                !tag.matches(".*[Кк]нопка.*")) {
                pureTags.add(tag);
            }
        }
        
        return pureTags;
    }

    @Step("Получение количества тегов (исключая кнопку 'Сбросить все')")
    public int getTagsCount() {
        // Получаем количество тегов, исключая кнопку "Сбросить все"
        int totalTags = filterTagsForCount.size();
        if (resetAllChip.isDisplayed()) {
            totalTags--;
        }
        return totalTags;
    }

    @Step("Получение количества тегов фильтров")
    public int getFilterTagsCount() {
        // Получаем количество только тегов фильтров
        return getFilterTags().size();
    }

    @Step("Получение количества чистых тегов фильтров")
    public int getPureFilterTagsCount() {
        // Получает количество только чистых тегов фильтров
        return getPureFilterTags().size();
    }

    // Методы для работы с расширенными фильтрами

    @Step("Клик по кнопке 'Все фильтры'")
    public FilterSearchPage clickAllFiltersButton() {
        allFiltersButton.shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Получение названий расширенных фильтров")
    public java.util.List<String> getAllFilterNames() {
        return extendedFilterNames.texts();
    }

    // Методы для работы с кнопками сброса

    @Step("Клик по кнопке 'Сбросить все'")
    public FilterSearchPage clickResetAllButton() {
        resetAllButton.shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Удаление всех тегов по одному")
    public FilterSearchPage removeAllTagsIndividually() {
        while (individualDeleteButtons.size() > 0) {
            individualDeleteButtons.first().shouldBe(visible, enabled).click();
        }
        return this;
    }

    // Вспомогательные методы

    private FilterSearchPage closeDropdown() {
        modalCloseButton.shouldBe(visible, enabled).click();
        return this;
    }
}
