package com.lostnfound.modal_classes;

import java.util.ArrayList;

public class ItemUploadModal {
    private String title,category, description,placeFound, id;
    private ArrayList<String>ImageUrls;

    public ItemUploadModal() {
    }

    public ItemUploadModal(String title, String category, String description, String placeFound, String id, ArrayList<String> imageUrls) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.placeFound = placeFound;
        this.id = id;
        ImageUrls = imageUrls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceFound() {
        return placeFound;
    }

    public void setPlaceFound(String placeFound) {
        this.placeFound = placeFound;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getImageUrls() {
        return ImageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        ImageUrls = imageUrls;
    }
}
