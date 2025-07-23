package json.models;

import lombok.Data;

@Data
public class LoginBodyModel {
    private String phone;
    private String password;

    public static LoginBodyModel createTestData() {
        LoginBodyModel model = new LoginBodyModel();
        model.setPhone("+79215711724");
        model.setPassword("1724");
        return model;
    }
}
