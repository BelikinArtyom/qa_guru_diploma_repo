package tests.flatSearchAPITests;

import io.restassured.response.Response;
import models.LoginModel;
import specs.LoginResponse;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.qameta.allure.Allure.step;

public class LoginTest extends TestBase {

    @Test
    void loginApiTest() {
        LoginModel testData = LoginModel.createTestData();

        Response authResponse = step("API авторизация", () -> {
            return given()
                    .spec(LoginResponse.loginRequestSpec)
                    .body(testData)
                    .when()
                    .post("/login")
                    .then()
                    .spec(LoginResponse.loginResponseSpec)
                    .extract().response();
        });

        String authToken = authResponse.path("token");

        step("Проверяем получение токена авторизации", () -> {
            System.out.println("Auth token: " + authToken);
        });
    }
}