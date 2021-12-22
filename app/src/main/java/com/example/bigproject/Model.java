package com.example.bigproject;

public class Model {

    String title;
    String description;
    String id;

    public Model(String title, String description, String id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    //Getter and setter methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}