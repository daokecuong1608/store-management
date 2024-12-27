package com.sapo.store_management.dto;

public class JwtResponse {

    private final String jwt;

    public String getJwt() {
        return jwt;
    }

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }


}
