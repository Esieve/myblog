package com.esieve.user.bean;

import java.io.Serializable;

/**
 * Created by 77239 on 2017/3/28/0028.
 */
public class User implements Serializable {
    private int userId;
    private String username;
    private String password;
    private String image;
    private String background;
    private String biography;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String image, String background, String biography) {
        this.username = username;
        this.password = password;
        this.image = image;
        this.background = background;
        this.biography = biography;
    }

    public User(int userId, String username, String password, String image, String background, String biography) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.image = image;
        this.background = background;
        this.biography = biography;
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

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", background='" + background + '\'' +
                ", biography='" + biography + '\'' +
                '}';
    }
}
