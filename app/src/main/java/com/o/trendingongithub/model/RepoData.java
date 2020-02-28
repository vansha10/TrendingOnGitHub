package com.o.trendingongithub.model;

public class RepoData {

    private String name;
    private String author;
    private String url;
    private String avatar;
    private String description;
    private String language;
    private long stars;
    private long forks;

    public RepoData(String name, String author, String url, String avatarUrl, String description, String language, long stars, long forks) {
        this.name = name;
        this.author = author;
        this.url = url;
        this.avatar = avatarUrl;
        this.description = description;
        this.language = language;
        this.stars = stars;
        this.forks = forks;
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
        return avatar;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatar = avatarUrl;
    }

    public long getStars() {
        return stars;
    }

    public void setStars(long stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getForks() {
        return forks;
    }

    public void setForks(long forks) {
        this.forks = forks;
    }
}
