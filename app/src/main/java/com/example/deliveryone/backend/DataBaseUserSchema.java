package com.example.deliveryone.backend;

public class DataBaseUserSchema {
    private Integer id;
    private String type;
    private String fullName;
    private String email;
    private String userName;
    private String password;

    public DataBaseUserSchema(Integer id, String type, String fullName, String email, String userName, String password) {
        this.id = id;
        this.type = type;
        this.fullName = fullName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}