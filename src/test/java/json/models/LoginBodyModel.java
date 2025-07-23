package json.models;

import lombok.Data;

@Data
public class LoginBodyModel {
    public String phone;
    public String password;
    public String client = "web";

    public static LoginBodyModel createTestData() {
        LoginBodyModel model = new LoginBodyModel();
        model.phone = "+79215711724";
        model.password = "1724";
        model.client = "web";
        return model;
    }

    // ✅ Метод для создания form-data
    public static io.restassured.specification.RequestSpecification createFormData(LoginBodyModel model) {
        return io.restassured.RestAssured.given()
                .formParam("phone", model.phone)
                .formParam("password", model.password)
                .formParam("client", model.client);
    }
}
