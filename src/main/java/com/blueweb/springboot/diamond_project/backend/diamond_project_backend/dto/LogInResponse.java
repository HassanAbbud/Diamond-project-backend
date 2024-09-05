package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.dto;


public class LogInResponse {
    private String user;
    private String token;

    public LogInResponse(String user, String token) {
        this.user = user;
        this.token = token;
    }

    // Getters and Setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
