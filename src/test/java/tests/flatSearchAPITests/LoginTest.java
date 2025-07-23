package tests.flatSearchAPITests;

import io.restassured.response.Response;
import json.models.LoginBodyModel;
import json.specs.LoginResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.qameta.allure.Allure.step;

public class LoginTest {

    @Test
    @Tag("Smoke execution")
    void loginApiTest() {
        LoginBodyModel testData = LoginBodyModel.createTestData();

        Response authResponse = step("API авторизация", () -> {
            return given()
                    .spec(LoginResponse.loginRequestSpec)
                    .formParam("phone", testData.phone)      // ✅ Прямой доступ к полям
                    .formParam("password", testData.password) // ✅ Прямой доступ к полям
                    .formParam("client", testData.client)     // ✅ Прямой доступ к полям
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