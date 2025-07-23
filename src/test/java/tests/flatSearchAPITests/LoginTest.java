package tests.flatSearchAPITests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.qameta.allure.Allure.step;

public class LoginTest {

    @Test
    @Tag("Smoke")
    void loginApiTest() {

