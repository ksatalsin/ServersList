package com.xyrality.slist.model;

import java.io.Serializable;

public class Server implements Serializable{

  /*  country = ISO country code string
    id = a unique identifier
            language = ISO language code string
            mapURL = A URL to a map server
            name = Name string of the world
    url = A URL to the world server
    worldStatus

    */

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMapURL() {
        return mapURL;
    }

    public void setMapURL(String mapURL) {
        this.mapURL = mapURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WorldStatus getWorldStatus() {
        return worldStatus;
    }

    public void setWorldStatus(WorldStatus worldStatus) {
        this.worldStatus = worldStatus;
    }

    private String country;
    private String language;
    private String mapURL;
    private String name;
    private Long id;
    private String url;
    private WorldStatus worldStatus;




}
