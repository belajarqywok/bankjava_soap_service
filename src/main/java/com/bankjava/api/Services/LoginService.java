package com.bankjava.api.Services;

import com.bankjava.contracts.LoginRequest;


public class LoginService {

    public boolean service(LoginRequest loginRequest) {

        // Get Username & Password
        boolean Email =  loginRequest.getEmail().equals("admin@admin.com");
        boolean Password = loginRequest.getPassword().equals("admin");

        return (boolean) (Email && Password);

    }
    
}
