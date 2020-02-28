package com.o.trendingongithub.model;

public class RepoData {
    private String name;
    private String author;
    private String url;
    private String avatarUrl;
    private long stars;
    public RepoData(String name, String author, String url, String avatarUrl, long stars) {
        this.name = name;
        this.author = author;
        this.url = url;
        this.avatarUrl = avatarUrl;
        this.stars = stars;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public long getStars() {
        return stars;
    }

    public void setStars(long stars) {
        this.stars = stars;
    }
}
