# Умные ожидания Selenide - Best Practices

## Обзор

Данный проект настроен для использования умных ожиданий Selenide, что является best practice для UI тестирования. Умные ожидания автоматически ждут появления элементов и проверяют их состояние перед выполнением действий.

## Настройки в TestBase

В `TestBase.java` настроены следующие параметры:

```java
// Настройки умных ожиданий Selenide
Configuration.timeout = 10000; // 10 секунд для ожиданий
Configuration.pollingInterval = 200; // Проверка каждые 200мс
Configuration.reportsFolder = "build/selenide-reports";

// Автоматические ожидания для всех элементов
Configuration.fastSetValue = false; // Отключаем быстрый ввод для стабильности
```

## Основные принципы умных ожиданий

### 1. Автоматические ожидания

Selenide автоматически ждет появления элементов перед выполнением действий:

```java
// Элемент автоматически ждет появления перед кликом
element.shouldBe(visible, enabled).click();

// Элемент автоматически ждет появления перед вводом
element.shouldBe(visible, enabled).setValue("text");

// Элемент автоматически ждет появления перед получением текста
element.shouldBe(visible).text();
```

### 2. Условия ожидания

Основные условия, которые можно использовать:

- `visible` - элемент видим на странице
- `enabled` - элемент активен и доступен для взаимодействия
- `text("expected text")` - элемент содержит определенный текст
- `value("expected value")` - элемент имеет определенное значение
- `attribute("attr", "value")` - элемент имеет определенный атрибут
- `cssClass("class")` - элемент имеет определенный CSS класс
- `disappear` - элемент исчезает со страницы

### 3. Комбинирование условий

Можно комбинировать несколько условий:

```java
// Элемент должен быть видимым И активным
element.shouldBe(visible, enabled);

// Элемент должен быть видимым И содержать определенный текст
element.shouldBe(visible).shouldHave(text("expected text"));
```

## Использование в Page Object

### Пример метода с умными ожиданиями:

```java
public FilterSearchPage enterSearchText(String searchText) {
    searchField.shouldBe(visible, enabled).setValue(searchText);
    return this;
}

public FilterSearchPage clickDistrictButton() {
    districtButton.shouldBe(visible, enabled).click();
    return this;
}

public String getRoomsPlaceholder() {
    return roomsPlaceholder.shouldBe(visible).text();
}
```

### Преимущества такого подхода:

1. **Автоматические ожидания** - не нужно явно указывать `Thread.sleep()` или `WebDriverWait`
2. **Проверка состояния** - элемент проверяется на видимость и активность перед действием
3. **Стабильность тестов** - тесты становятся более устойчивыми к задержкам загрузки
4. **Читаемость кода** - код становится более понятным и выразительным

## Вспомогательный класс SelenideWaitHelper

Создан класс `SelenideWaitHelper` с часто используемыми методами:

```java
// Ждать появления элемента с проверкой видимости и кликабельности
SelenideWaitHelper.waitForElement(element);

// Ждать появления элемента с определенным текстом
SelenideWaitHelper.waitForElementWithText(element, "expected text");

// Ждать изменения значения элемента
SelenideWaitHelper.waitForValueChange(element, "old value");

// Ждать появления элемента после выполнения действия
SelenideWaitHelper.waitForElementAfterAction(element, () -> performAction());
```

## Рекомендации по использованию

### 1. Всегда используйте умные ожидания

```java
// ✅ Хорошо - с умным ожиданием
element.shouldBe(visible, enabled).click();

// ❌ Плохо - без умного ожидания
element.click();
```

### 2. Проверяйте состояние элементов

```java
// ✅ Хорошо - проверяем видимость и активность
element.shouldBe(visible, enabled).setValue("text");

// ✅ Хорошо - проверяем только видимость для чтения
element.shouldBe(visible).text();
```

### 3. Используйте цепочки методов

```java
// ✅ Хорошо - цепочка методов с умными ожиданиями
filterSearchPage
    .setDistrict("Адмиралтейский")
    .setMetro("Адмиралтейская")
    .setRooms("2");
```

### 4. Избегайте явных задержек

```java
// ❌ Плохо - явная задержка
Thread.sleep(2000);

// ✅ Хорошо - умное ожидание
element.shouldBe(visible);
```

## Обработка ошибок

При использовании умных ожиданий Selenide автоматически:

1. Ждет появления элемента в течение `timeout` (10 секунд)
2. Проверяет условие каждые `pollingInterval` (200мс)
3. Выбрасывает понятное исключение с деталями, если элемент не появился

## Примеры использования в тестах

```java
@Test
void testWithSmartWaits() {
    // Открываем страницу
    open(baseUrl);
    
    // Устанавливаем фильтры с автоматическими ожиданиями
    filterSearchPage
        .setDistrict("Адмиралтейский")
        .setMetro("Адмиралтейская")
        .setRooms("2");
    
    // Проверяем результат с автоматическим ожиданием
    List<String> tags = filterSearchPage.getFilterTags();
    assertTrue(tags.contains("Адмиралтейский"));
}
```

## Заключение

Использование умных ожиданий Selenide делает тесты:

- **Более стабильными** - автоматические ожидания предотвращают падения из-за задержек
- **Более читаемыми** - код становится выразительным и понятным
- **Более надежными** - проверка состояния элементов перед действиями
- **Соответствующими best practices** - современный подход к UI тестированию

Все тесты в проекте теперь используют умные ожидания Selenide, что значительно повышает их качество и стабильность.
