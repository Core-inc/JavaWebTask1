package com.teamcore.site.domain.pojo;

/**
 * Pojo for authorization handling
 *
 * created by alterG (Igor_Shchipanov@epam.com)
 */

public class AuthPojo {
    private String email;
    private String password;

    public AuthPojo(String email) {
        this.email = email;
    }

    public AuthPojo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
