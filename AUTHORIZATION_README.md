# Автотесты для авторизации TrendAgent API

Этот проект содержит автотесты на Java для выполнения авторизации в TrendAgent API и получения auth_token.

## Структура проекта

- `src/test/java/tests/AuthorizationTest.java` - основной тест для авторизации
- `src/test/java/tests/utils/AuthUtils.java` - утилитарный класс для работы с авторизацией  
- `src/test/java/tests/ExampleApiTest.java` - примеры использования токена в других запросах

## Основные возможности

### AuthUtils - утилитарный класс

Предоставляет следующие методы:

#### `quickAuth()` 
Быстрая авторизация с предустановленными данными из curl запроса:
```java
String token = AuthUtils.quickAuth();
```

#### `authorize(phone, password)`
Авторизация с кастомными данными:
```java
String token = AuthUtils.authorize("+79215711724", "1724");
```

#### `getToken()`
Получение сохраненного токена (из кэша или файла):
```java
String token = AuthUtils.getToken();
```

#### `hasValidToken()`
Проверка наличия действительного токена:
```java
if (AuthUtils.hasValidToken()) {
    // токен есть и не пустой
}
```

#### `clearToken()`
Очистка токена и удаление файла:
```java
AuthUtils.clearToken();
```

## Как использовать

### 1. Запуск тестов авторизации

```bash
gradle test --tests AuthorizationTest
```

### 2. Использование в других тестах

```java
@BeforeEach
public void setUp() {
    // Получаем токен (автоматически выполнит авторизацию если токена нет)
    String authToken = AuthUtils.getToken();
    if (authToken == null) {
        authToken = AuthUtils.quickAuth();
    }
}

@Test
public void testSomeApiWithAuth() {
    String token = AuthUtils.getToken();
    
    Response response = given()
            .header("Authorization", "Bearer " + token)
            .when()
            .get("/api/some-endpoint")
            .then()
            .statusCode(200)
            .extract()
            .response();
}
```

### 3. Сохранение токена

Токен автоматически сохраняется в:
- Файл `auth_token.txt` в корне проекта
- Системное свойство `auth.token`
- Кэш в памяти (статическая переменная)

## Исходный curl запрос

Автотест воспроизводит следующий curl запрос:

```bash
curl 'https://sso-api.trendagent.ru/v1/login?app_id=66d84ffc4c0168b8ccd281c7&lang=ru' \
  -H 'accept: application/json, text/plain, */*' \
  -H 'content-type: application/x-www-form-urlencoded; charset=UTF-8' \
  -H 'origin: https://sso.trendagent.ru' \
  --data-raw 'password=1724&phone=%2B79215711724&client=web'
```

## Структура ответа

API возвращает JWT токен в формате:
```
eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2ODgwOTlmYjJkZjNmZTc0OTVkYTQ4OWU...
```

Токен содержит информацию о пользователе и имеет срок действия (обычно 5 минут).

## Примеры использования

Смотрите `ExampleApiTest.java` для примеров аутентифицированных запросов с полученным токеном.

## Требования

- Java 11+
- Gradle 8+
- Зависимости: REST Assured, JUnit 5, Gson (указаны в build.gradle)