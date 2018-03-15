package com.esieve.article.bean;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private int articleId;
    private int categoryId;
    private int userId;
    private String title;
    private String content;
    private Date publishTime;
    private Date updateTime;
    private int clicks;
    private String image;
    private boolean isAbout;

    public Article() {
    }

    public Article(int articleId, String title) {
        this.articleId = articleId;
        this.title = title;
    }

    public Article(int categoryId, String title, String content, String image) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAbout() {
        return isAbout;
    }

    public void setAbout(boolean about) {
        isAbout = about;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishTime=" + publishTime +
                ", updateTime=" + updateTime +
                ", clicks=" + clicks +
                ", image='" + image + '\'' +
                '}';
    }
}
