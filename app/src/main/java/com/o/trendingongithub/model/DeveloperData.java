package com.o.trendingongithub.model;

public class DeveloperData {

    private String username;
    private String name;
    private String url;
    private String avatar;

    public DeveloperData(String username, String name, String url, String avatar) {
        this.username = username;
        this.name = name;
        this.url = url;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
