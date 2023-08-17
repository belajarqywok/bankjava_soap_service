package com.bankjava.api.Controllers;

import org.springframework.stereotype.Service;

import com.bankjava.contracts.RegisterRequest;
import com.bankjava.contracts.RegisterResponse;



@Service
public class RegisterController {

    public RegisterResponse registerResponse(RegisterRequest registerRequest) {

        // Register Response
        RegisterResponse registerResponse = new RegisterResponse();

        registerResponse.setMessage("REGISTER_SUCCESS");

        return registerResponse;

    }
    
}
