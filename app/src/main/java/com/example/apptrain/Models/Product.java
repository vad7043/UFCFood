package com.example.apptrain.Models;

public class Product {
    private String Name,Description,PathToImg,Count,Price;

    public Product(String name, String description, String pathToImg, String count, String price) {
        Name = name;
        Description = description;
        PathToImg = pathToImg;
        Count = count;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPathToImg() {
        return PathToImg;
    }

    public void setPathToImg(String pathToImg) {
        PathToImg = pathToImg;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
