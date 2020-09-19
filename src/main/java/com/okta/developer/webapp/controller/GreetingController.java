package com.okta.developer.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;

import java.net.InetAddress;

@Controller
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);


    @GetMapping(value = "/greeting")
    @ResponseBody
    public String getGreeting(@AuthenticationPrincipal OidcUser oidcUser) {
        String serverUsed = "unknown";
        try {
            InetAddress host = InetAddress.getLocalHost();
            serverUsed = host.getHostName();
        } catch (Exception e){
            logger.error("Could not get hostname", e);
        }
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        logger.info("Request responded by " + serverUsed);
        return "Hello " + oidcUser.getFullName() + ", your server is " + serverUsed + ", with sessionId " + sessionId;
    }
}