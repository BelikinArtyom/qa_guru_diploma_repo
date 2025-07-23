package tests.flatSearchAPITests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiLogin {


    @Test
    void loginApiTest() {
        given()
                .log().uri()
                .post("https://sso-api.trendagent.ru/v1/login")
                .then()
                .log().all()
                .statusCode(200)
                .body(





    }



}
