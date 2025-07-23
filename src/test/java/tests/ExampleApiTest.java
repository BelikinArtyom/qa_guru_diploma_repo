package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import io.restassured.response.Response;
import tests.utils.AuthUtils;
import static io.restassured.RestAssured.*;

/**
 * Пример использования auth_token для выполнения аутентифицированных запросов
 */
public class ExampleApiTest {
    
    private String authToken;
    
    @BeforeEach
    public void setUp() {
        // Получаем токен из кэша или выполняем авторизацию если его нет
        authToken = AuthUtils.getToken();
        
        if (authToken == null || authToken.isEmpty()) {
            System.out.println("Токен не найден, выполняю авторизацию...");
            authToken = AuthUtils.quickAuth();
            Assertions.assertNotNull(authToken, "Не удалось получить токен авторизации");
        }
        
        System.out.println("Используется токен: " + authToken);
    }
    
    @Test
    @DisplayName("Пример аутентифицированного запроса")
    public void testAuthenticatedRequest() {
        // Пример запроса с использованием auth_token
        // Замените URL на актуальный API endpoint вашего приложения
        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                // или возможно .header("Authorization", authToken)
                // или .header("Auth-Token", authToken) - зависит от API
                .header("Content-Type", "application/json")
                .when()
                .get("https://sso-api.trendagent.ru/v1/user/profile") // замените на реальный endpoint
                .then()
                .extract()
                .response();
        
        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        
        // Проверки в зависимости от ожидаемого поведения вашего API
        // Например, если ожидается 200 OK:
        // Assertions.assertEquals(200, response.getStatusCode());
        
        // Если API возвращает 401/403 для неправильного токена:
        // Assertions.assertNotEquals(401, response.getStatusCode(), "Токен недействителен");
        // Assertions.assertNotEquals(403, response.getStatusCode(), "Доступ запрещен");
    }
    
    @Test
    @DisplayName("Пример POST запроса с токеном")
    public void testAuthenticatedPostRequest() {
        String requestBody = "{\"test\": \"data\"}";
        
        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("https://sso-api.trendagent.ru/v1/some-endpoint") // замените на реальный endpoint
                .then()
                .extract()
                .response();
        
        System.out.println("POST Response Status: " + response.getStatusCode());
        System.out.println("POST Response Body: " + response.getBody().asString());
    }
    
    @Test
    @DisplayName("Проверка валидности токена")
    public void testTokenValidation() {
        // Проверяем что токен не пустой
        Assertions.assertNotNull(authToken, "Токен не должен быть null");
        Assertions.assertFalse(authToken.trim().isEmpty(), "Токен не должен быть пустым");
        
        // Можно добавить проверку формата токена если известен
        // Например, JWT токены начинаются с определенных символов
        System.out.println("✅ Токен прошел базовые проверки валидности");
    }
}