package com.pg.dormy.model;

import lombok.Data;

@Data
public class LoginUser {
    private String userPhone;
    private String userPassword;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
