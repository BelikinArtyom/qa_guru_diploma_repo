package tests.flatSearchAPITests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class ApiLogin {


    @Test
    void loginApiTest() {
        given()
                .post("https://sso-api.trendagent.ru/v1/login");
        then().


    }



}
