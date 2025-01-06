package com.sapo.store_management.dto;

public class JwtResponse {

    private String accessToken;
    private String refreshToken;
private  int user_id;
private String fullname;
    private String username;

    public JwtResponse(String accessToken, String refreshToken, int user_id, String username , String fullname) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user_id = user_id;
        this.username = username;
        this.fullname = fullname;
    }

    // Getters and Setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
