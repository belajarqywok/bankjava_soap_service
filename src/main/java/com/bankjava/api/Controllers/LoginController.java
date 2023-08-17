package com.bankjava.api.Controllers;

import org.springframework.stereotype.Service;

import com.bankjava.contracts.LoginRequest;
import com.bankjava.contracts.LoginResponse;

import com.bankjava.api.Services.LoginService;

import com.bankjava.api.Helpers.Authentication;


@Service
public class LoginController extends Authentication {


    public LoginResponse loginResponse(LoginRequest loginRequest) {

        // Login Response
        LoginResponse loginResponse = new LoginResponse();

        // Login Service
        LoginService loginService = new LoginService();


        if(loginService.service(loginRequest)) {

            loginResponse.setMessage("success");
            loginResponse.setAccessToken(
                this.AccessTokenBuilder("JOWIEDie0ri3")
            );
            
            loginResponse.setRefreshToken(
                this.AccessTokenBuilder("JOWIEDie0ri3")
            );

        } else {
            loginResponse.setMessage("failed");
            loginResponse.setAccessToken(null);
        }

        return loginResponse;

    }

}