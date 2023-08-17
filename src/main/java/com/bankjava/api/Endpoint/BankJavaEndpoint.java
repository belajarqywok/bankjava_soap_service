package com.bankjava.api.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

// Login Contracts and Controller
import com.bankjava.contracts.LoginRequest;
import com.bankjava.contracts.LoginResponse;
import com.bankjava.api.Controllers.LoginController;


// Register Contracts and Controller
import com.bankjava.contracts.RegisterRequest;
import com.bankjava.contracts.RegisterResponse;
import com.bankjava.api.Controllers.RegisterController;

@Endpoint
public class BankJavaEndpoint {

        // Namespace
        private static final String NAMESPACE = "http://www.bankjava.com/contracts";

        // Controllers
        private LoginController loginController;
        private RegisterController registerController;

        @Autowired
        public BankJavaEndpoint() {

                // Controllers
                this.loginController = new LoginController();
                this.registerController = new RegisterController();
        }



        /**
         *  Login Endpoint
         */
        @PayloadRoot(namespace = NAMESPACE, localPart = "LoginRequest")
        @ResponsePayload
        public LoginResponse getLoginStatus(@RequestPayload LoginRequest loginRequest) {
                return loginController.loginResponse(loginRequest);
        }



        /**
         *  Register Endpoint
         */
        @PayloadRoot(namespace = NAMESPACE, localPart = "RegisterRequest")
        @ResponsePayload
        public RegisterResponse getRegisterStatus(@RequestPayload RegisterRequest registerRequest) {
                return registerController.registerResponse(registerRequest);
        }

}
