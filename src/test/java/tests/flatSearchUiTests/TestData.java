package tests.flatSearchUiTests;

import java.util.Arrays;
import java.util.List;

public class TestData {
    
    // Константы для тестовых данных
    public static final String DISTRICT_ADMIRALTY = "Адмиралтейский";
    public static final String METRO_ADMIRALTY = "Адмиралтейская";
    public static final String ROOMS_2 = "2-комнатная";
    public static final String PRICE_FROM = "10000000";
    public static final String PRICE_TO = "20000000";
    public static final String DEADLINE = "до 2027 г.";
    public static final String TEST_SEARCH_TEXT = "Тестовый ЖК";

    public static List<String> getExpectedAdmiraltyTags() {
        return Arrays.asList(
                "2-комнатная",
                "м. Адмиралтейская"
        );
    }

    public static List<String> getExpectedExtendedFilters() {
        return Arrays.asList(
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