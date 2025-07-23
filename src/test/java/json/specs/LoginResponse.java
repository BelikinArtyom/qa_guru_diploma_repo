package json.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.URLENC;

public class LoginResponse {

    public static RequestSpecification loginRequestSpec = with()
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(URLENC.withCharset("UTF-8"))
            .baseUri("https://sso-api.trendagent.ru")
            .basePath("/v1");

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}