package com.esprit.usermicroservice.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.core.Authentication;


public interface LogoutService extends LogoutHandler {

    void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    );
}
