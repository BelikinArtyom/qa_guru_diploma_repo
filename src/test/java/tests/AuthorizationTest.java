package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import tests.utils.AuthUtils;

public class AuthorizationTest {
    
    @Test
    @DisplayName("Авторизация пользователя и получение auth_token")
    public void testUserAuthorization() {
        // Очистка предыдущего токена для чистого теста
        AuthUtils.clearToken();
        
        // Выполнение авторизации с данными из curl запроса
        String authToken = AuthUtils.quickAuth();
        
        // Проверки
        Assertions.assertNotNull(authToken, "auth_token не должен быть null");
        Assertions.assertFalse(authToken.isEmpty(), "auth_token не должен быть пустым");
        
        System.out.println("✅ Тест авторизации прошел успешно!");
        System.out.println("Полученный токен: " + authToken);
        
        // Дополнительная проверка - убеждаемся что токен сохранен и доступен
        String retrievedToken = AuthUtils.getToken();
        Assertions.assertEquals(authToken, retrievedToken, "Сохраненный токен должен совпадать с полученным");
        
        // Проверяем что hasValidToken возвращает true
        Assertions.assertTrue(AuthUtils.hasValidToken(), "hasValidToken должен возвращать true после успешной авторизации");
    }
    
    @Test
    @DisplayName("Тест с кастомными данными авторизации")
    public void testCustomAuthorization() {
        // Очистка предыдущего токена
        AuthUtils.clearToken();
        
        // Можно тестировать с другими данными, если они есть
        String phone = "+79215711724";
        String password = "1724";
        
        String authToken = AuthUtils.authorize(phone, password);
        
        Assertions.assertNotNull(authToken, "Токен должен быть получен при корректных данных");
        System.out.println("✅ Тест с кастомными данными прошел успешно!");
    }

}