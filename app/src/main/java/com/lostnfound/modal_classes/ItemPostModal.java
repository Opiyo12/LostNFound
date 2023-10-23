package com.lostnfound.modal_classes;

public class ItemPostModal {
    int image;
    String title, location, date, description, address, category;

    public ItemPostModal() {
    }

    public ItemPostModal(int image, String title, String location, String date, String description, String address, String category) {
        this.image = image;
        this.title = title;
        this.location = location;
        this.date = date;
        this.description = description;
        this.address = address;
        this.category = category;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
