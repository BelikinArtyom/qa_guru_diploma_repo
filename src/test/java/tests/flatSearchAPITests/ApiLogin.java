package tests.flatSearchAPITests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class ApiLogin {


    @Test
    void loginApiTest() {
        given()
                .when()
                .post("https://sso-api.trendagent.ru/v1/login")
                .then()
                .statusCode(400); // Ожидаем 400 так как нет данных авторизации
    }



}
