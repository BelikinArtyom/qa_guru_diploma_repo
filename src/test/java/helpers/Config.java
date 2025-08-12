package helpers;

public class Config {
    // Параметры Selenoid для удаленного запуска
    public static final String SELENOID_HOST = System.getProperty("selenoid_host", "selenoid.autotests.cloud");
    public static final String SELENOID_LOGIN = System.getProperty("selenoid_login", "");
    public static final String SELENOID_PASSWORD = System.getProperty("selenoid_password", "");
    
    // Проверка обязательных параметров для Selenoid
    public static void validateSelenoidParams() {
        if (SELENOID_LOGIN.isEmpty() || SELENOID_PASSWORD.isEmpty()) {
            throw new IllegalStateException("SELENOID_LOGIN и SELENOID_PASSWORD должны быть установлены в Jenkins");
        }
    }
}
