package com.example.newsports.Models;

public class LookingforModel {

    private int id;
    private int image;
    private int w_image;
    private String description;
    private String phone;
    private String location;


    public LookingforModel(int id, int image, String description, String location, String phone, int w_image) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.location = location;
        this.phone = phone;
        this.w_image = w_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getW_image() {
        return w_image;
    }

    public void setW_image(int w_image) {
        this.w_image = w_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}



