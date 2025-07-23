package tests.utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Утилитарный класс для работы с авторизацией
 */
public class AuthUtils {
    
    private static final String LOGIN_URL = "https://sso-api.trendagent.ru/v1/login";
    private static final String APP_ID = "66d84ffc4c0168b8ccd281c7";
    private static final String LANG = "ru";
    private static final String TOKEN_FILE = "auth_token.txt";
    
    private static String cachedToken;
    
    /**
     * Выполняет авторизацию и возвращает auth_token
     * 
     * @param phone номер телефона
     * @param password пароль
     * @return auth_token или null если авторизация не удалась
     */
    public static String authorize(String phone, String password) {
        return authorize(phone, password, "web");
    }
    
    /**
     * Выполняет авторизацию и возвращает auth_token
     * 
     * @param phone номер телефона
     * @param password пароль
     * @param client тип клиента (web, mobile, etc.)
     * @return auth_token или null если авторизация не удалась
     */
    public static String authorize(String phone, String password, String client) {
        try {
            // Формирование тела запроса
            String requestBody = String.format("password=%s&phone=%s&client=%s", 
                                             password, 
                                             java.net.URLEncoder.encode(phone, java.nio.charset.StandardCharsets.UTF_8),
                                             client);
            
            // Выполнение запроса
            Response response = given()
                    .baseUri(LOGIN_URL)
                    .queryParam("app_id", APP_ID)
                    .queryParam("lang", LANG)
                    .header("accept", "application/json, text/plain, */*")
                    .header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("cache-control", "no-cache")
                    .header("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("origin", "https://sso.trendagent.ru")
                    .header("pragma", "no-cache")
                    .header("priority", "u=1, i")
                    .header("referer", "https://sso.trendagent.ru/")
                    .header("sec-ch-ua", "\"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"138\", \"Google Chrome\";v=\"138\"")
                    .header("sec-ch-ua-mobile", "?0")
                    .header("sec-ch-ua-platform", "\"Windows\"")
                    .header("sec-fetch-dest", "empty")
                    .header("sec-fetch-mode", "cors")
                    .header("sec-fetch-site", "same-site")
                    .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36")
                    .body(requestBody)
                    .when()
                    .post()
                    .then()
                    .statusCode(201)
                    .extract()
                    .response();
            
            // Извлечение токена из ответа
            String token = extractTokenFromResponse(response.getBody().asString());
            
            if (token != null && !token.isEmpty()) {
                cachedToken = token;
                System.setProperty("auth.token", token);
                saveTokenToFile(token);
                System.out.println("Авторизация успешна. Токен получен и сохранен.");
                return token;
            } else {
                System.err.println("Не удалось получить токен авторизации");
                System.err.println("Ответ сервера: " + response.getBody().asString());
                return null;
            }
            
        } catch (Exception e) {
            System.err.println("Ошибка при авторизации: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Извлекает токен из JSON ответа сервера
     */
    private static String extractTokenFromResponse(String responseBody) {
        try {
            JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
            
            // Различные варианты расположения токена в ответе
            if (jsonResponse.has("auth_token")) {
                return jsonResponse.get("auth_token").getAsString();
            } else if (jsonResponse.has("token")) {
                return jsonResponse.get("token").getAsString();
            } else if (jsonResponse.has("access_token")) {
                return jsonResponse.get("access_token").getAsString();
            } else if (jsonResponse.has("data")) {
                JsonObject data = jsonResponse.getAsJsonObject("data");
                if (data.has("auth_token")) {
                    return data.get("auth_token").getAsString();
                } else if (data.has("token")) {
                    return data.get("token").getAsString();
                } else if (data.has("access_token")) {
                    return data.get("access_token").getAsString();
                }
            }
            
            return null;
        } catch (Exception e) {
            System.err.println("Ошибка при парсинге ответа: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Получает кэшированный токен или загружает из файла
     */
    public static String getToken() {
        if (cachedToken != null && !cachedToken.isEmpty()) {
            return cachedToken;
        }
        
        // Попытка получить из системных свойств
        cachedToken = System.getProperty("auth.token");
        if (cachedToken != null && !cachedToken.isEmpty()) {
            return cachedToken;
        }
        
        // Попытка загрузить из файла
        try {
            Path tokenFile = Paths.get(TOKEN_FILE);
            if (Files.exists(tokenFile)) {
                cachedToken = new String(Files.readAllBytes(tokenFile)).trim();
                return cachedToken;
            }
        } catch (Exception e) {
            System.err.println("Ошибка при чтении токена из файла: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Проверяет, есть ли действительный токен
     */
    public static boolean hasValidToken() {
        String token = getToken();
        return token != null && !token.isEmpty();
    }
    
    /**
     * Сохраняет токен в файл
     */
    private static void saveTokenToFile(String token) {
        try {
            Path tokenFile = Paths.get(TOKEN_FILE);
            Files.write(tokenFile, token.getBytes());
            System.out.println("Токен сохранен в файл: " + tokenFile.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении токена: " + e.getMessage());
        }
    }
    
    /**
     * Очищает кэшированный токен и удаляет файл
     */
    public static void clearToken() {
        cachedToken = null;
        System.clearProperty("auth.token");
        
        try {
            Path tokenFile = Paths.get(TOKEN_FILE);
            if (Files.exists(tokenFile)) {
                Files.delete(tokenFile);
                System.out.println("Токен очищен и файл удален");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при удалении файла токена: " + e.getMessage());
        }
    }
    
    /**
     * Выполняет быструю авторизацию с предустановленными данными
     * (использует данные из вашего curl запроса)
     */
    public static String quickAuth() {
        return authorize("+79215711724", "1724");
    }
}