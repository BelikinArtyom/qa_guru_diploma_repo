package json.models;

import lombok.Data;

@Data
public class LoginBodyModel {
    public String phone;
    public String password;
    public String client = "web";

    // Явные геттеры
    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getClient() {
        return client;
    }

    // Явные сеттеры
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public static LoginBodyModel createTestData() {
        LoginBodyModel model = new LoginBodyModel();
        model.phone = "+79215711724";
        model.password = "1724";
        model.client = "web";
        return model;
    }
}

