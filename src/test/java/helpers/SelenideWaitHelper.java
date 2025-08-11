package helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;

/**
 * Вспомогательный класс для умных ожиданий Selenide
 * Содержит часто используемые методы проверки состояния элементов
 */
public class SelenideWaitHelper {

    /**
     * Ждет появления элемента с проверкой видимости и кликабельности
     */
    public static SelenideElement waitForElement(SelenideElement element) {
        return element.shouldBe(visible, enabled);
    }

    /**
     * Ждет появления элемента только с проверкой видимости
     */
    public static SelenideElement waitForElementVisible(SelenideElement element) {
        return element.shouldBe(visible);
    }

    /**
     * Ждет появления элемента с проверкой кликабельности
     */
    public static SelenideElement waitForElementEnabled(SelenideElement element) {
        return element.shouldBe(enabled);
    }

    /**
     * Ждет появления элемента с текстом
     */
    public static SelenideElement waitForElementWithText(SelenideElement element, String text) {
        return element.shouldBe(visible).shouldHave(text(text));
    }

    /**
     * Ждет появления элемента с атрибутом
     */
    public static SelenideElement waitForElementWithAttribute(SelenideElement element, String attribute, String value) {
        return element.shouldBe(visible).shouldHave(attribute(attribute, value));
    }

    /**
     * Ждет появления элемента с CSS классом
     */
    public static SelenideElement waitForElementWithClass(SelenideElement element, String className) {
        return element.shouldBe(visible).shouldHave(cssClass(className));
    }

    /**
     * Ждет появления элемента с значением
     */
    public static SelenideElement waitForElementWithValue(SelenideElement element, String value) {
        return element.shouldBe(visible).shouldHave(value(value));
    }

    /**
     * Ждет появления элемента с пустым значением
     */
    public static SelenideElement waitForElementEmpty(SelenideElement element) {
        return element.shouldBe(visible).shouldHave(value(""));
    }

    /**
     * Ждет исчезновения элемента
     */
    public static void waitForElementDisappear(SelenideElement element) {
        element.should(disappear);
    }

    /**
     * Ждет исчезновения элемента с текстом
     */
    public static void waitForElementWithTextDisappear(SelenideElement element, String text) {
        element.shouldNotHave(text(text));
    }

    /**
     * Ждет изменения значения элемента
     */
    public static SelenideElement waitForValueChange(SelenideElement element, String oldValue) {
        return element.shouldNotHave(value(oldValue));
    }

    /**
     * Ждет появления элемента после действия
     */
    public static SelenideElement waitForElementAfterAction(SelenideElement element, Runnable action) {
        action.run();
        return element.shouldBe(visible);
    }

    /**
     * Ждет появления элемента с задержкой
     */
    public static SelenideElement waitForElementWithDelay(SelenideElement element, int delayMs) {
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return element.shouldBe(visible);
    }
}
