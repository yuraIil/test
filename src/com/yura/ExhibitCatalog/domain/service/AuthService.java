package com.yura.demo.domain.service;

public class AuthService {

    private static final String ADMIN_KEY = "0000";

    public boolean validateAdminKey(String adminKey) {
        return ADMIN_KEY.equals(adminKey);
    }
}