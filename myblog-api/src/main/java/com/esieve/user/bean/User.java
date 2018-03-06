package com.esieve.user.bean;

/**
 * Created by 77239 on 2017/3/28/0028.
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private String image;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String image) {
        this.username = username;
        this.password = password;
        this.image = image;
    }

    public User(int userId, String username, String password, String image) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.image = image;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
