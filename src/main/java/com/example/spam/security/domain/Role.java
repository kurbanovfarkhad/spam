package com.example.spam.security.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;
    @Override
    public String getAuthority() {
        return name();
    }
}