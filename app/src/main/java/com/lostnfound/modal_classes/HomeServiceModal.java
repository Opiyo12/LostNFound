package com.lostnfound.modal_classes;

public class HomeServiceModal{
    int image;
    String service, description;



    public HomeServiceModal(int image, String service, String description) {
        this.image = image;
        this.service = service;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
