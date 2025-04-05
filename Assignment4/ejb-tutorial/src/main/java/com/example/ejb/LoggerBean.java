package com.example.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Stateless;

@Stateless
public class LoggerBean {

    @PostConstruct
    public void init() {
        System.out.println("LoggerBean initialized.");
    }

    public void logAction(String action) {
        System.out.println("Action logged: " + action);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("LoggerBean is about to be destroyed.");
    }
}
